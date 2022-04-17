package com.example.tema1;

import com.example.tema1.model.player.Player;
import com.example.tema1.model.player.UserPlayer;
import com.example.tema1.view_model.VMGame;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StartView {

    @FXML
    public RadioButton level1;
    @FXML
    public RadioButton level2;
    @FXML
    public ListView<String> lastGames;

    public ToggleGroup toggleButton;
    public TextField username;

    VMGame vmGame = new VMGame();

    public void initialize() {
        toggleButton = new ToggleGroup();
        level1.setToggleGroup(toggleButton);
        level2.setToggleGroup(toggleButton);
        toggleButton.selectToggle(level1);

        List<UserPlayer> players = vmGame.viewC.execute();
        ObservableList<String> playerList = FXCollections.observableArrayList();
        playerList.addAll(players.stream().map(Player::toString).collect(Collectors.toList()));
        lastGames.setItems(playerList);
    }

    @FXML
    protected void onStartButtonClick() throws IOException {
        VMGame.username = username.getText();
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
}