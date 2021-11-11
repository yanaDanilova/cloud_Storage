import com.geekbrains.AbstractMessage;
import com.geekbrains.FileMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Network {


    private static Socket socket;
    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;

    public static void start(){
        try {

            socket = new Socket("localhost", 8189);
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public static void close(){
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean uploadFile(AbstractMessage ms){
        try {
            outputStream.writeObject(ms);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public AbstractMessage downloadFile() throws IOException, ClassNotFoundException {
           FileMessage ms = (FileMessage) inputStream.readObject();
           return ms;
    }



}
