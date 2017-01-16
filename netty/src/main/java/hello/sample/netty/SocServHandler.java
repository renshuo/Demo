package hello.sample.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

/**
 * Created by sren on 17-1-14.
 */
public class SocServHandler extends ChannelInboundHandlerAdapter {


    public static final int BUFFER_SIZE = 1048576;
    static long sumx = 0;
    static long sumc = 0;

    static long start;
    static long end;

    private FileOutputStream fos;
    private FileChannel fc;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("#### connect active....");
        File f = new File("/home/work/test."+System.currentTimeMillis());
        fos = new FileOutputStream(f);
        fc = fos.getChannel();
        start = System.currentTimeMillis();
        ctx.write(new Date());
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            ByteBuffer src = in.nioBuffer();
            fc.write(src);
            sumx = fc.position();
            sumc ++;

//            int i ;
//            while( (i = in.readableBytes())>0) {
//                in.readBytes(fos, i);
//                sumx += i;
//                sumc++;
//            }
            end = System.currentTimeMillis();
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("#### channel inactive -----");
        fos.flush();
        fos.close();
        fc.close();
        System.out.println(""+(sumx)+":" + (sumx/sumc) +": "+(sumx/(end-start)));
        ctx.write(System.currentTimeMillis());
        ctx.flush();
    }
}
