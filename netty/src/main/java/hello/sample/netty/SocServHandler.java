package hello.sample.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by sren on 17-1-14.
 */
public class SocServHandler extends ChannelInboundHandlerAdapter {


    private FileOutputStream fos;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("#### connect active....");
        File f = new File("/home/work/test."+System.currentTimeMillis());
        fos = new FileOutputStream(f);
        ctx.write(new Date());
        ctx.flush();
    }

    static long sumx = 0;
    static long sumc = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            int i = in.readableBytes();
            sumx += i;
            sumc ++;
            byte[] buf = new byte[i];
            while (in.isReadable()) { // (1)
                in.readBytes(buf);
                fos.write(buf);
                fos.flush();
//                if (sumc%100==0){
//                    System.out.println(sumc+": "+i+": "+sumx+": "+(sumx/sumc));
//                }
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("#### channel inactive -----");
        ctx.write(System.currentTimeMillis());
        ctx.flush();
    }
}
