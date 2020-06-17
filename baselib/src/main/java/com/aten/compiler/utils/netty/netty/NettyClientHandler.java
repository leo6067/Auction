package com.aten.compiler.utils.netty.netty;

import android.util.Log;

import com.aten.compiler.utils.LogUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.netty.bean.NettyDataModel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import okhttp3.RequestBody;

/**
 * Created by 张俨 on 2018/1/10.
 */

public class NettyClientHandler extends SimpleChannelInboundHandler<NettyDataModel> {
    private NettyListener listener;
    public NettyClientHandler(NettyListener listener) {
        super();
        this.listener = listener;
    }

    //netty连接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyClient.getInstance().setConnectStatus(true);
        listener.onServiceStatusConnectChanged(NettyListener.STATUS_CONNECT_SUCCESS);
    }

    //netty断开 以及重连
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyClient.getInstance().setConnectStatus(false);
        listener.onServiceStatusConnectChanged(NettyListener.STATUS_CONNECT_CLOSED);
        NettyClient.getInstance().reconnect();
    }

    //客户端收到消息
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NettyDataModel byteBuf) throws Exception {
        listener.onMessageResponse(byteBuf);
    }

    //接收消息的一些事件处理
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {   //发送心跳
                LogUtils.e("netty", "来自客户端心跳包 ====》");
                NettyDataModel requestBody=new NettyDataModel();
                requestBody.setId(0);
                requestBody.setVersion(RxTool.getVersionCode(RxTool.getContext()));
                requestBody.setContext("".getBytes());
                requestBody.setContextLength("".length());
                ctx.channel().writeAndFlush(requestBody);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
