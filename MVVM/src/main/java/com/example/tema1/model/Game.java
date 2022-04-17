package com.example.tema1.model;

import com.example.tema1.model.board.Board;
import com.example.tema1.model.board.Point;
import com.example.tema1.model.minimax.MiniMax;
import com.example.tema1.model.minimax.Node;
import com.example.tema1.model.player.ComputerPlayer;
import com.example.tema1.model.player.UserPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Game {

    public static final int COMPUTER_ID = 1;
    public static final String COMPUTER_USERNAME = "Computer";
    public static final int USER_ID = 2;

    private Board board;
    private ComputerPlayer computerPlayer;
    private UserPlayer userPlayer;

    public void startGame(int arrowNumber, String username) {
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

    //    public static void main(String[] args) {
//        Board board = new Board(4);
//        for(int i = 0; i < 4; i++){
//            for(int j = 0 ; j < 4; j++){
//                board.getSquares()[i][j] = 0;
//            }
//        }
//
//        board.getSquares()[0][0] = 1;
//        board.getSquares()[0][2] = 2;
//        MiniMax minimax = new MiniMax(board);
//
//        System.out.println("Best move:");
//        System.out.println(board);
//        System.out.println(minimax.bestMove());
//    }

}
