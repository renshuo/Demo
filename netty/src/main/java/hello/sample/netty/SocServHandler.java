package hello.sample.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
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


    public static final int BUFFER_SIZE = 1048576;
    private FileOutputStream fos;
    static long sumx = 0;
    static long sumc = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("#### connect active....");
        File f = new File("/home/work/test."+System.currentTimeMillis());
        fos = new FileOutputStream(f);
        ctx.write(new Date());
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        int i = 0;
        byte[] buf = new byte[BUFFER_SIZE];
        try {
            while (in.isReadable()) { // (1)
                i = in.readableBytes();
                if (i>BUFFER_SIZE){
                    i = BUFFER_SIZE;
                }
                in.readBytes(buf, 0, i);
                sumx += i;
                sumc ++;
                fos.write(buf, 0, i);
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("#### channel inactive -----");
        fos.flush();
        fos.close();
        System.out.println(""+(sumx)+": "+(sumx/sumc));
        ctx.write(System.currentTimeMillis());
        ctx.flush();
    }
}
