package model.player.persistenta;

import database.Repository;
import model.player.UserPlayer;

import java.util.List;

public class PlayerPersistenta {

    public PlayerPersistenta() {
    }

    public boolean addPlayer(UserPlayer player) {
        return Repository.getInstance().addPlayer(player);
    }

    public List<UserPlayer> viewPlayers(){
        return Repository.getInstance().getPlayers();
    }

    public UserPlayer signIn(String username, String password) {
        return Repository.getInstance().getUser(username, password);
    }

    public boolean register(String username, String password) {
        return Repository.getInstance().addUser(username, password);
    }
}
