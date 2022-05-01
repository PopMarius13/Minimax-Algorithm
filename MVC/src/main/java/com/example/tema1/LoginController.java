package com.example.tema1;

import com.example.tema1.model.Game;
import com.example.tema1.model.language.Language;
import com.example.tema1.model.language.Languages;
import com.example.tema1.model.player.UserPlayer;
import com.example.tema1.model.player.persistenta.PlayerPersistenta;
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

    private final PlayerPersistenta playerPersistenta = new PlayerPersistenta();
    private Map<String, String> words;

    public void initialize() {
        words = Language.getInstance().getWords().get(Language.getInstance().getLanguage());
        updateWords();
    }

    public void onSignInButton() throws IOException {
        UserPlayer userPlayer = playerPersistenta.signIn(username.getText(), password.getText());
        if(userPlayer != null) {
            nextScene();
        }
    }

    public void onRegisterButton() throws IOException {
        if(playerPersistenta.register(username.getText(), password.getText())) {
            nextScene();
        }
    }

    public void nextScene() throws IOException {
        Game.username = username.getText();
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
