package com.life.application.io.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tengyun
 * @date 2020/11/9 10:20
 **/
// 这里是需要有@Component的，因为push方法需要被spring容器中的service调用，所以需要有@Component
@Component
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final int RESPONSE_CODE_SUCCESS = 200;

    private static final ConcurrentHashMap<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Long, ChannelId> USER_CHANNEL_MAP = new ConcurrentHashMap<>();

    private static ServerHandler serverHandler;

    private WebSocketServerHandshaker handShaker;

    private String websocketUrl;

    private String url;

    Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    /**
     * PostConstruct这个注解的方法是spring容器的bean初始化完之后执行，此时的this也就是容器中这个类的那个对象
     * 使用类静态对象指向他，那么netty直接new的对象，就有了容器中的对象引用
     * @author tengyun
     * @date 15:25 2021/1/22
     **/
    @PostConstruct
    public void init() {
        serverHandler = this;
    }



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    /**
     * 唯一的一次http请求，用于创建websocket
     */
    protected void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        //要求Upgrade为websocket，过滤掉get/Post
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            //若不是websocket方式，则创建BAD_REQUEST的req，返回给客户端
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        // 将userId和channelId对应起来
        Long userId = getUserId(req.uri());
        ChannelId id = ctx.channel().id();
        USER_CHANNEL_MAP.put(userId, id);
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080" + "websocket", null, false);
        handShaker = wsFactory.newHandshaker(req);
        if (handShaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handShaker.handshake(ctx.channel(), req);
        }
        logger.info("create websocket finished, userId is {}, channel id is {}", userId, id);
    }

    protected Long getUserId(String uri) throws Exception {
        int idx = uri.indexOf("?");
        if (idx == -1) {
            throw new Exception("url错误，没有参数");
        }
        String[] paramsArr = uri.substring(idx + 1).split("&");
        for (String param : paramsArr) {
            String[] params = param.split("=");
            if ("userId".equals(params[0])) {
                return Long.valueOf(params[1]);
            }
        }
        throw new Exception("url错误，没有参数userId");
    }

    protected void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 关闭链路的指令
        Channel channel = ctx.channel();
        if (frame instanceof CloseWebSocketFrame) {
            handShaker.close(channel, (CloseWebSocketFrame) frame.retain());
            return;
        }
        // ping消息
        if (frame instanceof PingWebSocketFrame) {
//            logger.info("ping msg, the channelId is {}", channel.id());
            channel.write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 非文本消息
        if (!(frame instanceof TextWebSocketFrame)) {
            logger.error("WebSocketFrame type is not supported");
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        String message = ((TextWebSocketFrame) frame).text();
//        logger.info("the channel id is {}, server received ：{}", ctx.channel().id(), message);
        if ("ping".equals(message)) {
            TextWebSocketFrame tws = new TextWebSocketFrame("pong");
            ctx.channel().writeAndFlush(tws);
        } else {
            handlerReceivedMessage(ctx, message);
        }
    }

    /**
     * 每个handler处理接收消息的业务逻辑放到自己的handler中实现
     *
     * @param ctx, frame
     * @author tengyun
     * @date 15:44 2020/11/10
     * TODO 分类消息
     **/
    protected void handlerReceivedMessage(ChannelHandlerContext ctx, String message) {
        // 处理接收到的消息
    }


    /**
     * 拒绝不合法的请求，并返回错误信息
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        HttpResponseStatus responseStatus = res.status();
        // 返回应答给客户端
        if (responseStatus.code() != RESPONSE_CODE_SUCCESS) {
            ByteBuf buf = Unpooled.copiedBuffer(responseStatus.toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        // 如果是非Keep-Alive，关闭连接
        if (!HttpUtil.isKeepAlive(req) || responseStatus.code() != RESPONSE_CODE_SUCCESS) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 推送消息
     * @param userId 用户id
     * @return void
     * @author tengyun
     * @date 15:57 2020/11/10
     **/
    public void push(Long userId, String msg) throws Exception {
        ChannelId channelId  = USER_CHANNEL_MAP.get(userId);
        if (channelId == null) {
            logger.info("this netty server doesn't have the user's channel, the userId is {}", userId);
            return;
        }
        TextWebSocketFrame tws = new TextWebSocketFrame(msg);
        Channel channel = CHANNEL_MAP.get(channelId.asShortText());
        if (channel == null) {
            logger.error("错误userId is {}", userId);
            logger.error("错误USER_CHANNEL_MAP is {}", USER_CHANNEL_MAP.toString());
            logger.error("错误CHANNEL_MAP is {}", CHANNEL_MAP.toString());
        }
        logger.info("pushing msg to {}, the msg is {}", userId, msg);
        channel.writeAndFlush(tws);
    }

    /**
     * 加入连接
     *
     * @author tengyun
     * @date 16:02 2020/11/11
     **/
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("client join, id is {}", ctx.channel().id());
        CHANNEL_MAP.put(ctx.channel().id().asShortText(), ctx.channel());
    }

    /**
     * 断开连接
     *
     * @return void
     * @author tengyun
     * @date 15:47 2020/11/11
     **/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ChannelId id = ctx.channel().id();
        removeChannelInfo(id);
        logger.info("client close, id is {}", id);
    }

    public void removeChannelInfo(ChannelId id) {
        CHANNEL_MAP.remove(id.asShortText());
        for (Map.Entry<Long, ChannelId> entry : USER_CHANNEL_MAP.entrySet()) {
            if (entry.getValue().equals(id)) {
                USER_CHANNEL_MAP.remove(entry.getKey());
                break;
            }
        }
    }

    /**
     * read之后调用
     *
     * @return void
     * @author tengyun
     * @date 16:05 2020/11/11
     **/
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * 抛异常的方法
     *
     * @param ctx, cause
     * @return void
     * @author tengyun
     * @date 16:03 2020/11/11
     **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("the error occur, the cause is {} ", cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        logger.info("userEventTriggered is used");
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            // 如果读通道处于空闲状态，说明没有接收到心跳命令
            if (IdleState.READER_IDLE.equals(event.state())) {
                Channel channel = ctx.channel();
                logger.info("there is a channel closing, channel id is {}", channel.id());
                removeChannelInfo(channel.id());
                channel.close();
            }
        } else {
            super.userEventTriggered(ctx, obj);
        }
    }

}
