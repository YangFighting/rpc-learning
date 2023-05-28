package io.yang.rpc.test.consumer.codec.init;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.yang.rpc.codec.RpcDecoder;
import io.yang.rpc.codec.RpcEncoder;
import io.yang.rpc.test.consumer.codec.handler.RpcTestConsumerHandler;

/**
 * Description:
 * date: 2023/5/28 20:05
 *
 * @author zhangyang
 */
public class RpcTestConsumerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast(new RpcEncoder());
        cp.addLast(new RpcDecoder());
        cp.addLast(new RpcTestConsumerHandler());
    }
}
