package com.example.tema1.model.minimax;

import com.example.tema1.model.board.Board;
import com.example.tema1.model.board.Point;

public class MiniMax {

    private final Board board;

    public MiniMax(Board board) {
        this.board = board;
    }

    public Node bestMove() {
        Node bestMove = new Node(new Point(-1, -1), Integer.MIN_VALUE, false);
        for (int i = 0; i < board.getArrowsNumber(); i++) {
            for (int j = 0; j < board.getArrowsNumber(); j++) {
                if(board.getSquares()[i][j] == 0) {
                    for (int k = 1; k <= board.getArrowsNumber(); k++) {
                        Point point = new Point(i, j, k);
                        board.getSquares()[i][j] = k;
                        Node newNode = new Node(point, false);
                        minimax(newNode);
                        if (newNode.getScore() > bestMove.getScore()) {
                            bestMove = newNode;
                        }
                    }
                    board.getSquares()[i][j] = 0;
                }
            }
        }
        return bestMove;
    }

    public void minimax(Node parentNode) {
        int result = board.checkWinner(parentNode.isMaxPlayer());
        if(result != 0) {
            parentNode.setScore(result);
        } else {
            Node bestMove = new Node(new Point(-1, -1), Integer.MIN_VALUE, !parentNode.isMaxPlayer());
            for(int i = 0; i < board.getArrowsNumber(); i++){
                for(int j = 0; j < board.getArrowsNumber(); j++){
                    if(board.getSquares()[i][j] == 0) {
                        for (int k = 1; k <= board.getArrowsNumber(); k++) {
                            Point point = new Point(i, j, k);
                            board.getSquares()[i][j] = k;
                            Node newNode = new Node(point, !parentNode.isMaxPlayer());
                            minimax(newNode);
                            if((parentNode.isMaxPlayer() && newNode.getScore() < bestMove.getScore()) ||
                                (!parentNode.isMaxPlayer() && newNode.getScore() > bestMove.getScore())){
                                bestMove = newNode;
                                parentNode.setScore(bestMove.getScore());
                                board.getSquares()[i][j] = 0;
                                return;
                            }
                        }
                        board.getSquares()[i][j] = 0;
                    }
                }
            }
        }
    }
}
