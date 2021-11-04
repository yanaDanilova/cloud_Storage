package com.geekbrains;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8189)){
            System.out.println("server started..");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
