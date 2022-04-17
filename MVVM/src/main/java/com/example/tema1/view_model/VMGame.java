package com.example.tema1.view_model;

import com.example.tema1.model.Game;
import com.example.tema1.model.board.Point;
import com.example.tema1.model.minimax.Node;
import com.example.tema1.model.player.UserPlayer;
import com.example.tema1.view_model.commands.CommandaAdd;
import com.example.tema1.view_model.commands.CommandaView;
import com.example.tema1.view_model.commands.ICommanda;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
public class VMGame {

    public Game game = new Game();
    public CommandaAdd addC;
    public CommandaView viewC;
    public static String username = "Username";

    public VMGame() {
        this.addC = new CommandaAdd(this);
        this.viewC = new CommandaView(this);
    }

    public void startGame(int arrowNumber) {
        System.out.println(username);
        game.startGame(arrowNumber, username);
    }

    public Point computerMove() {
        Node move = game.nextMoveComputer();
        return move.getPoint();
    }

    public void playerMove(Point point) {
        game.getBoard().getSquares()[point.getX()][point.getY()] = point.getArrow();
    }

    public int finishGame(boolean player) {
        int winner = game.checkWinner(player);
        int score = player ? winner : -winner;
        if(winner != 0) {
            game.endGame(score);
            addC.execute();
        }
        return score;
    }

    public UserPlayer getPlayer() {
        return game.getUserPlayer();
    }
}
