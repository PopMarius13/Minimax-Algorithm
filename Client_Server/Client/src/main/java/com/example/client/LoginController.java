package com.example.client;

import com.example.client.language.Language;
import com.example.client.language.Languages;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class LoginController {
    public TextField username;
    public TextField password;
    public Label username_text;
    public Label password_text;
    public Button button_sigin;
    public Button button_register;

    private Map<String, String> words;

    public void initialize() {
        words = Language.getInstance().getWords().get(Language.getInstance().getLanguage());
        updateWords();
    }

    public void onSignInButton() throws IOException {
        String userPlayer = Client.getINSTANCE().sendMessage("Sign:" + username.getText() + "," + password.getText());
        System.out.println(userPlayer);
        if(userPlayer != null) {
            nextScene();
        }
    }

    public void onRegisterButton() throws IOException {
        String userPlayer = Client.getINSTANCE().sendMessage("Register:" + username.getText() + "," + password.getText());
        if(userPlayer != null) {
            nextScene();
        }
    }

    public void nextScene() throws IOException {
        Client.getINSTANCE().sendMessage("Set Username:" + username.getText());
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage.setTitle("Arrow Game");
        stage.setScene(new Scene(root, 600, 500));
        stage.setMaxHeight(500);
        stage.setMaxWidth(600);
        stage.setMinWidth(600);
        stage.setMinHeight(500);
        stage.show();
    }

    public void onRomanaButton() {
        Language.INSTANCE.setLanguage(Languages.ROMANA);
        words = Language.INSTANCE.getWords().get(Language.INSTANCE.getLanguage());
        updateWords();
    }
    public void onSpanishButton() {
        Language.INSTANCE.setLanguage(Languages.SPANISH);
        words = Language.INSTANCE.getWords().get(Language.INSTANCE.getLanguage());
        updateWords();
    }
    public void onEnglishButton() {
        Language.getInstance().setLanguage(Languages.ENGLISH);
        words = Language.getInstance().getWords().get(Language.getInstance().getLanguage());
        updateWords();
    }

    public void updateWords() {
        username_text.setText(words.get("username"));
        password_text.setText(words.get("password"));
        button_sigin.setText(words.get("sign_in"));
        button_register.setText(words.get("register"));
    }
}
