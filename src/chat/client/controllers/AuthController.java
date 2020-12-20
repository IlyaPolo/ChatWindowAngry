package chat.client.controllers;

import chat.client.ClientChat;
import chat.client.models.Network;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

public class AuthController {

    private static final String AUTH_CMD = "/auth"; // "/auth login password"

    @FXML
    public TextField loginField;

    @FXML
    public PasswordField passwordField;


    private Network network;

    @FXML
    public void executeAuth(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            ClientChat.showNetworkError("Логин и пароль обязательны!", "Валидация", null);
            return;
        }

        try {
            network.sendAuthMessage(login, password);
        } catch (IOException e) {
            ClientChat.showNetworkError(e.getMessage(), "Auth error!", null);
            e.printStackTrace();
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
