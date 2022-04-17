package com.example.tema1.view_model.commands;

import com.example.tema1.model.player.persistenta.PlayerPersistenta;
import com.example.tema1.view_model.VMGame;

public class CommandaAdd {

    private VMGame vmGame;

    public CommandaAdd(VMGame vmGame) {
        this.vmGame = vmGame;
    }

    public void execute() {
        PlayerPersistenta playerPersistenta = new PlayerPersistenta();
        playerPersistenta.addPlayer(vmGame.getPlayer());
    }
}
