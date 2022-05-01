package com.example.tema1.model.player;

import com.example.tema1.model.board.Board;
import com.example.tema1.model.minimax.MiniMax;
import com.example.tema1.model.minimax.Node;

public class ComputerPlayer extends Player{

    private MiniMax minimax;

    public ComputerPlayer(int idPlayer, String username) {
        super(idPlayer, username);
    }

    public Node nextMove(Board board){
        minimax = new MiniMax(board);
        return minimax.bestMove();
    }
}
