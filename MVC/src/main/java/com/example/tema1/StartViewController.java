package com.example.tema1;

import com.example.tema1.model.Game;
import com.example.tema1.model.Observer;
import com.example.tema1.model.language.Language;
import com.example.tema1.model.language.Languages;
import com.example.tema1.model.player.Player;
import com.example.tema1.model.player.UserPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StartViewController implements Observer {

    @FXML
    public RadioButton level1;
    @FXML
    public RadioButton level2;
    @FXML
    public ListView<String> lastGames;

    public ToggleGroup toggleButton;
    private final Game game = new Game();
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

        List<UserPlayer> players = game.getPlayers();
        ObservableList<String> playerList = FXCollections.observableArrayList();
        playerList.addAll(players.stream().map(Player::toString).collect(Collectors.toList()));
        lastGames.setItems(playerList);
        welcome_username.setText(Game.username);

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

    @Override
    public void update() {
    }
}