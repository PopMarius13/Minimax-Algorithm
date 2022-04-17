package com.example.tema1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartView {

    @FXML
    public RadioButton level1;
    @FXML
    public RadioButton level2;

    public ToggleGroup toggleButton;

    public void initialize() {
        toggleButton = new ToggleGroup();
        level1.setToggleGroup(toggleButton);
        level2.setToggleGroup(toggleButton);
        toggleButton.selectToggle(level1);
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
}