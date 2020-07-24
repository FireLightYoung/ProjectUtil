package com.util.ming.projectutil.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ming on 17/8/21.
 */

public class JavaTest2 {


    public static void main(String[] args) {
        //路径:    /Users/ming/Documents/cao/aaa.txt

        test4();
//        test2();

    }

    private static void test4() {
        String host = "mimscmp.icbc.com.cn";
        int port = 80;
        InetSocketAddress _host = new InetSocketAddress(host, port);
        _host.toString();
         int a = 1;
    }

    private static void test1() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("/Users/ming/Documents/cao/aaa.txt", "rw");

            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test2() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("/Users/ming/Documents/cao/aaa.txt", "rw");

            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(48);
            String str = "hello world";
            buf.put(str.getBytes());
            int bytesRead = inChannel.write(buf);
            System.out.println("Write " + bytesRead);

            aFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test3() {
        try {
            File file = new File("/Users/ming/Documents/cao/aaa.txt");
            FileInputStream inFile = new FileInputStream(file);


            FileChannel inChannel = inFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(48);
            String str = "hello world";
            buf.clear();
            buf.put(str.getBytes());
            buf.flip();
            while (buf.hasRemaining()) {
                int bytesRead = inChannel.write(buf);
                System.out.println("Write " + bytesRead);
            }
            inChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void test4() {
//        try {
//            Selector selector = Selector.open();
//
//            channel.configureBlocking(false);
//            SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
//            while (true) {
//                int readyChannels = selector.select();
//                if (readyChannels == 0) continue;
//                Set selectedKeys = selector.selectedKeys();
//                Iterator keyIterator = selectedKeys.iterator();
//                while (keyIterator.hasNext()) {
//                    SelectionKey key = keyIterator.next();
//                    if (key.isAcceptable()) {
//                        // a connection was accepted by a ServerSocketChannel.
//                    } else if (key.isConnectable()) {
//                        // a connection was established with a remote server.
//                    } else if (key.isReadable()) {
//                        // a channel is ready for reading
//                    } else if (key.isWritable()) {
//                        // a channel is ready for writing
//                    }
//                    keyIterator.remove();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

}

