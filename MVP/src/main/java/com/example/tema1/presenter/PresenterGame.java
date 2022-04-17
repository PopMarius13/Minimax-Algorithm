package com.example.tema1.presenter;

import com.example.tema1.model.Game;
import com.example.tema1.model.board.Point;
import com.example.tema1.model.minimax.Node;

public class PresenterGame {

    public Game game = new Game();

    public void startGame(int arrowNumber) {
        game.startGame(arrowNumber, "User");
    }

    public Point computerMove() {
        Node move = game.nextMoveComputer();
        System.out.println("Computer");
        System.out.println(game.getBoard());
        return move.getPoint();
    }

    public void playerMove(Point point) {
        game.getBoard().getSquares()[point.getX()][point.getY()] = point.getArrow();
        System.out.println("Player");
        System.out.println(game.getBoard());
    }

    public int finishGame(boolean player) {
        int winner = game.checkWinner(player);
        int score = player ? winner : -winner;
        if(winner != 0) {
            game.endGame(score);
        }
        return score;
    }

}
