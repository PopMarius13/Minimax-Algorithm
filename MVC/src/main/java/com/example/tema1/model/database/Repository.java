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

    private static final String QUERY_VIEW_ALL_PLAYERS = "SELECT * FROM score";
    private static final String QUERY_INSERT_PLAYER = "INSERT INTO score( username, score ) VALUES (?, ?)";
    private static final String QUERY_INSERT_USER = "INSERT INTO account( username, password ) VALUES (?, ?)";
    private static final String QUERY_GET_USER = "SELECT * FROM account WHERE username = ? AND password = ? ";

    private PreparedStatement insertPlayer;
    private PreparedStatement insertUser;
    private PreparedStatement getUser;
    private PreparedStatement viewPlayers;
    private Repository() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

            insertPlayer = connection.prepareStatement(QUERY_INSERT_PLAYER, Statement.RETURN_GENERATED_KEYS);
            insertUser = connection.prepareStatement(QUERY_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            getUser = connection.prepareStatement(QUERY_GET_USER);
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

    public boolean addUser(String username, String password) {

        try {
            insertUser.setString(1, username);
            insertUser.setString(2, password);

            insertUser.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public UserPlayer getUser(String username, String password){
        UserPlayer user;
        try {
            getUser.setString(1, username);
            getUser.setString(2, password);
            ResultSet result = getUser.executeQuery();

            result.next();
            user = new UserPlayer(result.getInt(1), result.getString(2));
            user.setPassword(result.getString(3));

            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
