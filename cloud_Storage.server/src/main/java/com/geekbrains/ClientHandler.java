package com.geekbrains;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AbstractMessage abstractMessage = (AbstractMessage) msg;
        if(abstractMessage instanceof FileMessage){
            String filename = ((FileMessage) msg).getFilename();
            byte[] file = ((FileMessage) msg).getFile();
            Files.write(Paths.get("serverDir/" + filename), file);
        }
        if(abstractMessage instanceof RequestMessage){
            String filename = ((RequestMessage) abstractMessage).getFilename();

        }
        super.channelRead(ctx, msg);
    }
}

