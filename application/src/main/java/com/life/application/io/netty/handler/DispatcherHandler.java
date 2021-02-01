package com.life.application.io.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author tengyun
 * @date 2020/11/9 15:06
 **/
//@Component
public class DispatcherHandler extends ServerHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("dispatcher++++++++++++++++++++++++++++++++++");
        if (msg instanceof FullHttpRequest) {
            //以http请求形式接入，但是走的是websocket
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            //处理websocket客户端的消息
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

/*    Logger logger = LoggerFactory.getLogger(DispatcherHandler.class);

    @Override
    protected void handlerReceivedMessage(ChannelHandlerContext ctx, String message) {
        logger.info("dispatcher netty server received {}", message);
        TextWebSocketFrame tws = new TextWebSocketFrame("success");
        ctx.channel().writeAndFlush(tws);
    }

    @Override
    public void push(Long userId, String msg) throws Exception {
        super.push(userId, msg);
    }*/
}
