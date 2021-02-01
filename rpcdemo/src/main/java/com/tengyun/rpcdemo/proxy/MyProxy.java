package com.tengyun.rpcdemo.proxy;

import com.tengyun.rpcdemo.rpc.Dispatcher;
import com.tengyun.rpcdemo.rpc.protocal.MyContent;
import com.tengyun.rpcdemo.rpc.protocal.MyHeader;
import com.tengyun.rpcdemo.rpctest.ClientFactory;
import com.tengyun.rpcdemo.rpctest.ResponseMappingCallback;
import com.tengyun.rpcdemo.rpctest.SerDerUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

/**
 * @author tengyun
 * @date 2021/1/27 19:57
 **/
public class MyProxy {
    public static <T>T proxyGet(Class<T>  interfaceInfo){
        //实现各个版本的动态代理。。。。

        ClassLoader loader = interfaceInfo.getClassLoader();
        Class<?>[] methodInfo = {interfaceInfo};

        Dispatcher dis = Dispatcher.getDis();

        return (T) Proxy.newProxyInstance(loader, methodInfo, (proxy, method, args) -> {
            //如何设计我们的consumer对于provider的调用过程
            Object res = null;
            Object o = dis.get(interfaceInfo.getName());
            if (o == null) {
                // rpc
                //1，调用 服务，方法，参数  ==》 封装成message  [content]
                String name = interfaceInfo.getName();
                String methodName = method.getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                // TODO rpc
                MyContent content = new MyContent();
                content.setArgs(args);
                content.setName(name);
                content.setMethodName(methodName);
                content.setParameterTypes(parameterTypes);

                byte[] msgBody = SerDerUtil.ser(content);



                //2，requestID+message  ，本地要缓存
                //协议：【header<>】【msgBody】
                MyHeader header = MyHeader.createHeader(msgBody);
                byte[] headerMsg = SerDerUtil.ser(header);
                System.out.println("main:::" + headerMsg.length);




                //3，连接池：：取得连接
                ClientFactory factory = ClientFactory.getFactory();
                NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress("localhost", 9090));
                //获取连接过程中： 开始-创建，过程-直接取






                //4，发送--> 走IO  out -->走Netty（event 驱动）

                ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length + msgBody.length);

//                CountDownLatch countDownLatch = new CountDownLatch(1);
                long id = header.getRequestID();
                CompletableFuture<String> res = new CompletableFuture<>();
                ResponseMappingCallback.addCallBack(id, res);
                byteBuf.writeBytes(msgHeader);
                byteBuf.writeBytes(msgBody);
                ChannelFuture channelFuture = clientChannel.writeAndFlush(byteBuf);
                channelFuture.sync();  //io是双向的，你看似有个sync，她仅代表out

//                countDownLatch.await();

                //5，？，如果从IO ，未来回来了，怎么将代码执行到这里
                //（睡眠/回调，如何让线程停下来？你还能让他继续。。。）


                return res.get();//阻塞的
            } else {
                // local
                Class<?> clazz = o.getClass();
                try {
                    Method m = clazz.getMethod(method.getName(), method.getParameterTypes());
                    res = m.invoke(o, args);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            return res;
        });


    }
}
