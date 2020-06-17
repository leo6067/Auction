/*
 * Copyright 2019 Jenly Yu
 * <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <a href="https://github.com/jenly1314">jenly1314</a>
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.aten.compiler.utils.netty;

import com.aten.compiler.utils.netty.bean.NettyDataModel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class NettyEncoder extends MessageToByteEncoder {

    /**
     * 消息结束分隔符
     */
    public byte[] mDelimiter;

    public NettyEncoder(){

    }

    public NettyEncoder(byte[] delimiter){
        this.mDelimiter = delimiter;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (msg instanceof NettyDataModel) {
            NettyDataModel request = (NettyDataModel) msg;
            out.writeInt(request.getId());
            out.writeInt(request.getVersion());
            byte[] context = request.getContext();
            if (context == null || context.length < 1) {
                out.writeInt(0);
                return;
            }
            out.writeInt(context.length);
            out.writeBytes(context);
        }
    }
}
