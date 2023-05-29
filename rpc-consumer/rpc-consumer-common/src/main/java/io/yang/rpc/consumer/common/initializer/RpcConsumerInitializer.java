package io.yang.rpc.consumer.common.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.yang.rpc.codec.RpcDecoder;
import io.yang.rpc.codec.RpcEncoder;
import io.yang.rpc.consumer.common.handler.RpcConsumerHandler;

/**
 * @author zhangyang
 * @date: 2023/5/30 0:08
 * @description:
 */
public class RpcConsumerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline cp = channel.pipeline();
        cp.addLast(new RpcEncoder());
        cp.addLast(new RpcDecoder());
        cp.addLast(new RpcConsumerHandler());

    }
}
