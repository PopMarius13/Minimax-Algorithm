package com.example.tema1.model.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor
public abstract class Player {

    private int idPlayer;
    private String username;

}
