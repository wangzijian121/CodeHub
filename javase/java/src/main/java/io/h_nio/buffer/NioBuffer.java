package io.h_nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @author zijian Wang
 */
public class NioBuffer {
    public static void main(String[] args) {

        String str = "wang";
        //非直接内存
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println("byteBuffer1是否是直接缓冲区：" + byteBuffer.isDirect());
        //直接内存
        ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(10);
        System.out.println("byteBuffer2是否是直接缓冲区：" + byteBuffer2.isDirect());

        //put
        byteBuffer.put(str.getBytes());
        getBufferInfo(byteBuffer);
        System.out.print((char) byteBuffer.get(0));
        System.out.print((char) byteBuffer.get(1));
        System.out.print((char) byteBuffer.get(2));
        System.out.print((char) byteBuffer.get(3));
        System.out.println();
        //读模式，将有数据这段单独提取出来，其他的存储空间不可见。
        byteBuffer.flip();
        getBufferInfo(byteBuffer);
        //clear 后只是将position置为0，并不是清理buffer
        byteBuffer.clear();
        getBufferInfo(byteBuffer);
        //还是能取到
        System.out.println((char) byteBuffer.get(0));
    }

    public static void getBufferInfo(Buffer buffer) {
        System.out.println("----------------------------------");
        System.out.println("缓冲区总量：capacity:" + buffer.capacity());
        System.out.println("当前位置：position:" + buffer.position());
        System.out.println("缓冲区限制大小limit:" + buffer.limit());
        System.out.println("是否有剩余空间:" + buffer.hasRemaining());
        System.out.println("----------------------------------");

    }
}
