package com.example.knowledge.io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-10-14 15:26
 */
public class NIOServer extends Thread {
    @Override
    public void run() {
        try(
                // 创建Selector和Channel
                Selector selector = Selector.open();
                ServerSocketChannel serverSocket = ServerSocketChannel.open()
        ){
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),8888));
            serverSocket.configureBlocking(false);
            //注册到Selector，并说明关注点
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true){
                //阻塞等待就绪的channel，这是关键点之一
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectionKeys.iterator();
                while (iter.hasNext()){
                    SelectionKey key = iter.next();
                    //生产一般会额外进行就绪状态检查
                    sayHelloWorld((ServerSocketChannel) key.channel());
                    iter.remove();
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void sayHelloWorld(ServerSocketChannel server) throws  IOException{
        SocketChannel client = server.accept();
        client.write(Charset.defaultCharset().encode("Hello World"));
    }
}
