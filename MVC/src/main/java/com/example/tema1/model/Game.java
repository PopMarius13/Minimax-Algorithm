package com.example.tema1.model;

import com.example.tema1.model.board.Board;
import com.example.tema1.model.board.Point;
import com.example.tema1.model.minimax.MiniMax;
import com.example.tema1.model.minimax.Node;
import com.example.tema1.model.player.ComputerPlayer;
import com.example.tema1.model.player.UserPlayer;
import com.example.tema1.model.player.persistenta.PlayerPersistenta;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter @Setter
public class Game extends Games {

    public static final int COMPUTER_ID = 1;
    public static final String COMPUTER_USERNAME = "Computer";
    public static final int USER_ID = 2;

    private Board board;
    private ComputerPlayer computerPlayer;
    private UserPlayer userPlayer;
    public static String username = "Username";

    public PlayerPersistenta playerPersistenta = new PlayerPersistenta();

    public void startGame(int arrowNumber) {
        this.listObs = new LinkedList<>();
        board = new Board(arrowNumber);
        computerPlayer = new ComputerPlayer(COMPUTER_ID, COMPUTER_USERNAME);
        userPlayer = new UserPlayer(USER_ID, username);
    }

    public Node nextMoveComputer() {
        Node move = computerPlayer.nextMove(board);
        Point movePoint = move.getPoint();
        board.getSquares()[movePoint.getX()][movePoint.getY()] = movePoint.getArrow();
        return move;
    }

    public int checkWinner(boolean player) {
        return board.checkWinner(player);
    }

    public void endGame(int scoreUser) {
        userPlayer.setScore(scoreUser);
    }

    public Board getBoard() {
        return board;
    }

    public Point computerMove() {
        Node move = nextMoveComputer();
        return move.getPoint();
    }

    public void playerMove(Point point) {
        getBoard().getSquares()[point.getX()][point.getY()] = point.getArrow();
    }

    public int finishGame(boolean player) {
        int winner = checkWinner(player);
        int score = player ? winner : -winner;
        if(winner != 0) {
            endGame(score);
            playerPersistenta.addPlayer(getUserPlayer());
        }
        this.notification();
        return score;
    }

    public List<UserPlayer> getPlayers() {
        PlayerPersistenta playerPersistenta = new PlayerPersistenta();
        return playerPersistenta.viewPlayers();
    }

}
