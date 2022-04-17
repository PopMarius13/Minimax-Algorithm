package com.example.tema1.model.player.persistenta;

import com.example.tema1.model.database.Repository;
import com.example.tema1.model.player.UserPlayer;

import java.util.List;

public class PlayerPersistenta {


    public boolean addPlayer(UserPlayer player) {
        return Repository.getInstance().addPlayer(player);
    }

    public List<UserPlayer> viewPlayers(){
        return Repository.getInstance().getPlayers();
    }

}
