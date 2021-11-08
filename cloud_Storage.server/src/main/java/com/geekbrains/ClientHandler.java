package com.geekbrains;

import sun.util.calendar.BaseCalendar;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ClientHandler implements Runnable {

    private  int counter = 0;
    private String name;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        counter++;
        name = "User#" + counter;

        System.out.println("client accepted..");
    }


    @Override
    public void run() {
        try {
            while (true){
                String msg = in.readUTF();
                String fileName =msg;
                System.out.println("server received fileName: " + fileName);
                 File userDir = new File("cloud_Storage.server/serverDir/" + name );
                 userDir.mkdirs();
                FileOutputStream fos = new FileOutputStream(userDir+ "/"+ fileName);
                int x;
                byte[] buf = new byte[128];
                while (in.read(buf) != -1){
                    fos.write(buf);
                }
     //не могу понять почему не переходит на следующую строчку и не выводит единицу. Ведь когда получает -1 должен выйти из цикла!? из-за этого дальше не смогла проверить код на получение ответа в контроллере
                System.out.println("1");
                out.writeUTF("downloaded "+ fileName);
                System.out.println("downloaded "+ fileName);
                out.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
//почему то не получилось добавлять дату и так же с логированием как на уроке что то не получалось и я пока оставила этот вопрос
    public String getDate(){
        return    dateTimeFormatter.format(LocalDate.now());

    }
}
