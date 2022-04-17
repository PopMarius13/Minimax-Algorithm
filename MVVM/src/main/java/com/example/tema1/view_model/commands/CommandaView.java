package com.example.tema1.view_model.commands;

import com.example.tema1.model.player.UserPlayer;
import com.example.tema1.model.player.persistenta.PlayerPersistenta;
import com.example.tema1.view_model.VMGame;

import java.util.List;

public class CommandaView {
    private VMGame vmGame;

    public CommandaView(VMGame vmGame) {
        this.vmGame = vmGame;
    }

    public List<UserPlayer> execute() {
        PlayerPersistenta playerPersistenta = new PlayerPersistenta();
        return playerPersistenta.viewPlayers();
    }
}
