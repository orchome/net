package com.system.net.netty.client;

import com.system.net.model.Command;
import org.jboss.netty.channel.*;

public class TimeClientHandler extends SimpleChannelUpstreamHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Command command = new Command();
        command.setActionName("Hello Server.");
        e.getChannel().write(command);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (e.getMessage() instanceof Command) {
            Command command = (Command) e.getMessage();
            System.out.println("对象:" + command.getActionName());
            return;
        }

        System.out.println(e.getMessage());
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}