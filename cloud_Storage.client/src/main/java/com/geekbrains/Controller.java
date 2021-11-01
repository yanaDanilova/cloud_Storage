package com.geekbrains;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ListView clientView;
    public ListView serverView;
    public TextField input;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sendMessage(ActionEvent actionEvent) {
    }
}
