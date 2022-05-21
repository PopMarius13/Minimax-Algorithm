package com.example.client;

import com.example.client.language.Language;
import com.example.client.language.Languages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class StartViewController {

    @FXML
    public RadioButton level1;
    @FXML
    public RadioButton level2;
    @FXML
    public ListView<String> lastGames;

    public ToggleGroup toggleButton;
    public Label welcome_username;
    public Button start_button;
    public Label select_text;
    public Label level1_text;
    public Label level2_text;
    public Button logout_button;
    public Label last_game_text;
    private Map<String, String> words;

    public void initialize() {
        toggleButton = new ToggleGroup();
        level1.setToggleGroup(toggleButton);
        level2.setToggleGroup(toggleButton);
        toggleButton.selectToggle(level1);

        ObservableList<String> playerList = FXCollections.observableArrayList();
        String players = Client.getINSTANCE().sendMessage("Players").replace("[", "");
        players = players.replace("]", "");
        playerList.addAll(players.split(","));
        lastGames.setItems(playerList);
        welcome_username.setText(Client.getINSTANCE().sendMessage("Get Username"));

        words = Language.getInstance().getWords().get(Language.getInstance().getLanguage());
        updateWords();
    }

    @FXML
    protected void onStartButtonClick() throws IOException {
        Stage stage = (Stage) level1.getScene().getWindow();
        stage.close();
        String toggle = toggleButton.getSelectedToggle().toString();
        String level = "1";
        if(toggle.contains("2")){
            level = "2";
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game-level" + level + ".fxml")));
        stage.setTitle("Arrow Game");
        stage.setScene(new Scene(root, 900, 800));
        stage.setMaxHeight(800);
        stage.setMaxWidth(900);
        stage.setMinWidth(900);
        stage.setMinHeight(800);
        stage.show();
    }

    @FXML
    protected void onLogOutButtonClick() throws IOException {
        Stage stage = (Stage) level1.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
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
        start_button.setText(words.get("start"));
        select_text.setText(words.get("select_level"));
        level1_text.setText(words.get("level1"));
        level2_text.setText(words.get("level2"));
        logout_button.setText(words.get("log_out"));
        last_game_text.setText(words.get("last_games"));
    }

}