package com.example.tema1.model.player;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserPlayer extends Player {
    private int score;
    public UserPlayer(int idPlayer, String username) {
        super(idPlayer, username);
    }


    @Override
    public String toString() {
        String playerScore = score == -1 ? "Lose" : "Win";
        return this.getIdPlayer() + ") " + this.getUsername() + " -> " + playerScore;
    }
}
