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

import android.util.Log;

import com.aten.compiler.utils.LogUtils;
import com.aten.compiler.utils.netty.bean.NettyDataModel;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class NettyDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int len = in.readableBytes();
        if (len >= 12) {
            try {
                in.markReaderIndex();
                int id = in.readInt();
                int version = in.readInt();
                int contextLength = in.readInt();
                if (contextLength > 0 && len < 12 + contextLength) {
                    //没有足够的数据
                    in.resetReaderIndex();
                    return;
                }
                byte[] bytes = new byte[contextLength];
                in.readBytes(bytes);
                NettyDataModel body = new NettyDataModel();
                body.setId(id);
                body.setVersion(version);
                body.setContextLength(contextLength);
                body.setContext(bytes);
                out.add(body);
            } catch (Exception e) {
                LogUtils.e("RequestDecoder",e.getMessage());
            }
        }
    }
}
