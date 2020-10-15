package com.example.knowledge.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: knowledge
 * @description: 服务器应用，同时服务多个客户端请求
 * @author: zhangjialin
 * @create: 2020-10-14 14:31
 */
public class DemoServer extends Thread{
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    public int getPort(){
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(0);
            executorService = Executors.newFixedThreadPool(8);
            while (true){
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                executorService.execute(requestHandler);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (serverSocket!=null){
                try {
                    serverSocket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DemoServer server = new DemoServer();
        server.start();
        try(Socket client = new Socket(InetAddress.getLocalHost(),server.getPort())){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(s-> System.out.println(s));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class RequestHandler extends Thread{
    private Socket socket;
    RequestHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try(PrintWriter out = new PrintWriter(socket.getOutputStream())){
            out.println("Hello world");
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
