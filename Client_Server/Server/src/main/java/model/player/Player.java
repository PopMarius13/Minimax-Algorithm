package model.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public abstract class Player {

    private int idPlayer;
    private String username;

    public abstract Player copy();

}
