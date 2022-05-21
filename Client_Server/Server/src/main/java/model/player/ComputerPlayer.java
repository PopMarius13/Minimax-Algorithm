package model.player;

import model.board.Board;
import model.minimax.MiniMax;
import model.minimax.Node;

public class ComputerPlayer extends Player{

    private MiniMax minimax;

    public ComputerPlayer(int idPlayer, String username) {
        super(idPlayer, username);
    }

    public Node nextMove(Board board){
        minimax = new MiniMax(board);
        return minimax.bestMove();
    }

    @Override
    public Player copy() {
        return new ComputerPlayer(this.getIdPlayer(), this.getUsername());
    }
}
