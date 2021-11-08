package com.geekbrains;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    public ListView<String> clientView;
    public ListView<String > serverView;
    public TextField textFieldSend;
    private DataInputStream in;
    private DataOutputStream out;
    private Path clientDir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            clientDir = Paths.get("cloud_Storage.client","clientDir");

            if(!Files.exists(clientDir)){
                Files.createDirectory(clientDir);
            }
            clientView.getItems().clear();
            clientView.getItems().addAll(getFile(clientDir));
            clientView.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 ){
                    String item = clientView.getSelectionModel().getSelectedItem();
                    textFieldSend.setText(item);
                }
            });
            Socket socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread readThread = new Thread(this::read);
            readThread.setDaemon(true);
            readThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public List<String> getFile(Path path) throws IOException {
        return Files.list(clientDir).map(p->p.getFileName().toString()).collect(Collectors.toList());
    }
//вот этот метод не проверен
    public void read(){
        try{
            while (true){
                String response = in.readUTF();
                System.out.println("received msg "+ response);
                String[] tokens = response.split(" ", 2);
                ;
                Platform.runLater(()->{
                    serverView.getItems().add(tokens[1]);
                });
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void sendMessage(ActionEvent actionEvent) throws IOException {


    }

    public void sendFile(ActionEvent actionEvent) {
        try {

            String fileName = textFieldSend.getText();
            out.writeUTF(fileName);
            out.flush();
            textFieldSend.clear();

            FileInputStream  fis = new FileInputStream("cloud_Storage.client/clientDir/" + fileName);

            int x;
            byte[] buf = new byte[128];
            while ((x = fis.read(buf)) != -1){
                out.write(buf,0, buf.length);
                System.out.println((char) buf[0]);
            }
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
