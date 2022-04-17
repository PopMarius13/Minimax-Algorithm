package com.example.tema1.model.database;

import com.example.tema1.model.player.Player;
import com.example.tema1.model.player.UserPlayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository INSTANCE;

    private final String url = "jdbc:postgresql://localhost:5432/PlayersDB";
    private final String user = "postgres";
    private final String password = "postgres";
    private Connection connection;

    private static final String QUERY_VIEW_ALL_PLAYERS = "SELECT * FROM player";
    private static final String QUERY_INSERT_PLAYER = "INSERT INTO player( username, score ) VALUES (?, ?)";

    private PreparedStatement insertPlayer;
    private PreparedStatement viewPlayers;
    private Repository() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

            insertPlayer = connection.prepareStatement(QUERY_INSERT_PLAYER, Statement.RETURN_GENERATED_KEYS);
            viewPlayers = connection.prepareStatement(QUERY_VIEW_ALL_PLAYERS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Repository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }


    public List<UserPlayer> getPlayers(){
        List<UserPlayer> players = new ArrayList<>();
        try {
            ResultSet result = viewPlayers.executeQuery();

            while(result.next()) {
                UserPlayer player = new UserPlayer(result.getInt(1), result.getString(2));
                player.setScore(result.getInt(3));
                players.add(player);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return players;
    }

    public boolean addPlayer(UserPlayer player) {

        try {
            insertPlayer.setString(1, player.getUsername());
            insertPlayer.setInt(2, player.getScore());

            insertPlayer.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
