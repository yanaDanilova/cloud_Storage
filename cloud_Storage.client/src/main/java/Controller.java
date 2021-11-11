import com.geekbrains.FileMessage;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
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
    private Path clientDir;


    private FileMessage fileMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Network.start();

        Thread thread = new Thread(()->{
            try {
                clientDir = Paths.get("clientDir");

                if(!Files.exists(clientDir)){
                    Files.createDirectory(clientDir);
                }
                clientView.getItems().clear();
                clientView.getItems().addAll(getFile(clientDir));
                clientView.setOnMouseClicked(event -> {
                    if(event.getClickCount() == 2 ){
                        String selectedFile = clientView.getSelectionModel().getSelectedItem();
                        sendFile(selectedFile);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();

    }

    public List<String> getFile(Path path) throws IOException {
        return Files.list(clientDir).map(p->p.getFileName().toString()).collect(Collectors.toList());
    }
//вот этот метод не проверен
    public void readFile(){
    }


    public void sendFile(String selectedFile) {
        try {
            fileMessage = new FileMessage(selectedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Network.uploadFile(fileMessage);
    }
}
