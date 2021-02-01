package com.life.application.io.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author tengyun
 * @date 2020/11/9 15:06
 **/
// 这里是需要有@Component的，因为push方法需要被spring容器中的service调用，所以需要有@Component，
//@Component
public class DriverHandler  extends ServerHandler {

//    Logger logger = LoggerFactory.getLogger(DriverHandler.class);


//    private static DriverHandler driverHandler;
@Override
protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("driver++++++++++++++++++++++++++++++++++");
    if (msg instanceof FullHttpRequest) {
        //以http请求形式接入，但是走的是websocket
        handleHttpRequest(ctx, (FullHttpRequest) msg);
    } else if (msg instanceof WebSocketFrame) {
        //处理websocket客户端的消息
        handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
    }
}

    /**
     * PostConstruct这个注解的方法是spring容器的bean初始化完之后执行，此时的this也就是容器中这个类的那个对象
     * 使用类静态对象指向他，那么netty直接new的对象，就有了容器中的对象引用
     * @author tengyun
     * @date 15:25 2021/1/22
     **/
//    @PostConstruct
//    public void init () {
//        driverHandler = this;
//    }


/*    @Override
    public void handlerReceivedMessage(ChannelHandlerContext ctx, String message) {
        // 处理接收到的消息
        Map addressMessage = JsonUtil.parseObject(message, Map.class);
        HTTP http = HTTP.builder().build();
        HttpResult resWx = http.sync(driverHandler.url).addJsonParam(addressMessage).post();
        HttpResult.Body body = resWx.getBody();
        JSONObject jsonObject = body.toJsonObject();
        if (resWx.isSuccessful() && "0".equals(String.valueOf(jsonObject.get("code")))) {
            TextWebSocketFrame tws = new TextWebSocketFrame("success");
            ctx.channel().writeAndFlush(tws);
        } else {
            TextWebSocketFrame tws = new TextWebSocketFrame("fail");
            ctx.channel().writeAndFlush(tws);
        }
    }

    @Override
    public void push(Long userId, String msg) throws Exception {
        super.push(userId, msg);
    }*/
}
