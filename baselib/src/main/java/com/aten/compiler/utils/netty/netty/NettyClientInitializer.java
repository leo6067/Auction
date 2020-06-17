package com.aten.compiler.utils.netty.netty;

import com.aten.compiler.utils.netty.NettyDecoder;
import com.aten.compiler.utils.netty.NettyEncoder;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Created by 张俨 on 2017/10/9.
 */

public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {

    private NettyListener listener;

    public NettyClientInitializer(NettyListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener == null ");
        }
        this.listener = listener;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//        SslContext sslCtx = SSLContext.getDefault()
//                .createSSLEngine(InsecureTrustManagerFactory.INSTANCE).build();
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(sslCtx.newHandler(ch.alloc()));    // 开启SSL
        pipeline.addLast(new LoggingHandler(LogLevel.ERROR));    // 开启日志，可以设置日志等级
        pipeline.addLast("IdleStateHandler", new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));//3s未发送数据，回调userEventTriggered
        pipeline.addLast("NettyDecoder", new NettyDecoder());//解码器 这里要与服务器保持一致
        pipeline.addLast("NettyEncoder", new NettyEncoder());//编码器 这里要与服务器保持一致
        pipeline.addLast(new NettyClientHandler(listener));
    }
}