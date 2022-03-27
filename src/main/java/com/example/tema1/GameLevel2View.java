package com.example.tema1;

import com.example.tema1.model.board.Point;
import com.example.tema1.presenter.PresenterGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameLevel2View {

    @FXML
    public GridPane gridPane;
    @FXML
    public GridPane arrowPane;
    @FXML
    public Label labelWin;
    @FXML
    public Label gameOver;
    public Button restart;
    public Button menu;

    public Button[] buttons = new Button[100];
    public RadioButton[] arrows = new RadioButton[10];

    public ToggleGroup arrowGroup = new ToggleGroup();
    public int arrowNumber = 8;
    public int[] buttonsCheck = new int[100];

    PresenterGame presenterGame = new PresenterGame();

    public void initialize() {
        presenterGame.startGame(arrowNumber);

        for (int i = 1; i <= arrowNumber; i++) {
            addArrow(i);
            for (int j = 1; j <= arrowNumber; j++) {
                addButton(i, j);
            }
        }
        arrowGroup.selectToggle(arrows[1]);

        for (int i = 1; i <= arrowNumber * arrowNumber; i++) {
            Button button = buttons[i];
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String idString = button.getId();
                    int x = idString.charAt(1) - '0';
                    int y = idString.charAt(2) - '0';
                    if (buttonsCheck[(x - 1) * 8 + y] != 1) {
                        int arrow = 0;
                        for (int i = 1; i <= 8; i++) {
                            if (arrows[i].isSelected()) {
                                button.setBackground(arrows[i].getBackground());
                                arrow = i;
                            }
                        }
                        presenterGame.playerMove(new Point(x - 1, y - 1, arrow));
                        int finishGame = presenterGame.finishGame(true);
                        if(finishGame != 0){
                            finishGame(finishGame);
                        } else {
                            Point point = presenterGame.computerMove();
                            String urlImage = "image/arrow" + point.getArrow() + "c.png";
                            BackgroundImage bimg = new BackgroundImage(new Image(urlImage),
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.CENTER,
                                    new BackgroundSize(52, 52, false, false, false, false));
                            buttons[point.getX() * 8 + point.getY() + 1].setBackground(new Background(bimg));
                            finishGame = presenterGame.finishGame(false);
                            if(finishGame != 0) {
                                finishGame(finishGame);
                            }
                        }
                        buttonsCheck[(x - 1) * 8 + y] = 1;
                    }
                }
            });
        }
    }

    public void addButton(int i, int j) {
        Button newButton = new Button("");
        newButton.setId("B" + i + j);
        newButton.setMinWidth(50);
        newButton.setMinHeight(50);
        gridPane.add(newButton, i, j);
        buttons[(i - 1) * 8 + j] = newButton;
    }

    public void addArrow(int i) {
        RadioButton newArrow = new RadioButton("");
        newArrow.setId("A" + i);
        newArrow.setMinWidth(50);
        newArrow.setMinHeight(50);
        String urlImage = "image/arrow" + i + ".png";
        BackgroundImage bimg = new BackgroundImage(new Image(urlImage),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(52, 52, false, false, false, false));
        newArrow.setBackground(new Background(bimg));
        arrowPane.add(newArrow, i, 0);
        newArrow.setToggleGroup(arrowGroup);
        arrows[i] = newArrow;
    }

    public void finishGame(int score) {
        String win = (score == 1) ? "You lose!" : "You win!";
        labelWin.setText(win);
        gameOver.setText("Game Over");
        for (int i = 1; i <= arrowNumber * arrowNumber; i++) {
            buttons[i].setDisable(true);
        }
        restart.setVisible(true);
        menu.setVisible(true);
    }

    @FXML
    protected void onRestartButtonClick() throws IOException {
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game-level2.fxml")));
        stage.setTitle("Arrow Game");
        stage.setScene(new Scene(root, 900, 800));
        stage.setMaxHeight(800);
        stage.setMaxWidth(900);
        stage.setMinWidth(900);
        stage.setMinHeight(800);
        stage.show();
    }

    @FXML
    protected void onMenuButtonClick() throws IOException {
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage.setTitle("Menu");
        stage.setScene(new Scene(root, 600, 500));
        stage.setMaxHeight(500);
        stage.setMaxWidth(600);
        stage.setMinWidth(600);
        stage.setMinHeight(500);
        stage.show();
    }
}
