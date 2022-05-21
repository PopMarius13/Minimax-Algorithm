import model.Game;
import model.board.Point;
import model.player.Player;
import model.player.UserPlayer;
import model.player.persistenta.PlayerPersistenta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private final Game game = new Game();

    private final PlayerPersistenta playerPersistenta = new PlayerPersistenta();

    public void start(int port) throws IOException {
        System.out.println("Server Start");

        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while (!Objects.equals(inputLine = in.readLine(), "End Game")) {
            if (inputLine.contains("Arrow Number:")) {
                game.startGame(Integer.parseInt(inputLine.replaceAll("Arrow Number:", "")));
                out.println("ok");

            } else if (inputLine.contains("Player Move:")) {
                inputLine = inputLine.replaceAll("Player Move:", "");
                String[] point = inputLine.split(",");
                game.playerMove(new Point(Integer.parseInt(point[0]), Integer.parseInt(point[1]), Integer.parseInt(point[2])));
                out.println("ok");

            } else if (inputLine.contains("Computer Move")) {
                Point point = game.computerMove();
                out.println(point.getX() + "," + point.getY() + "," + point.getArrow());

            } else if (inputLine.contains("Finish Game:")) {
                inputLine = inputLine.replaceAll("Finish Game:", "");
                boolean finish = inputLine.equals("true");
                out.println("" + game.finishGame(finish));


            } else if (inputLine.contains("Set Username:")) {
                inputLine = inputLine.replaceAll("Set Username:", "");
                Game.username = inputLine;
                out.println("Ok");

            } else if (inputLine.contains("Get Username")) {
                out.println(Game.username);

            } else if (inputLine.contains("Players")) {
                List<UserPlayer> players = game.getPlayers();
                out.println(players.stream().map(Player::toString).collect(Collectors.toList()));

            } else if (inputLine.contains("Sign:")) {
                inputLine = inputLine.replaceAll("Sign:", "");
                String[] elems = inputLine.split(",");
                UserPlayer userPlayer = playerPersistenta.signIn(elems[0], elems[1]);
                if(userPlayer != null) {
                    out.println("ok");
                } else {
                    out.println();
                }

            } else if (inputLine.contains("Register:")) {
                inputLine = inputLine.replaceAll("Register:", "");
                String[] elems = inputLine.split(",");
                boolean register = playerPersistenta.register(elems[0], elems[1]);
                if(register) {
                    out.println("ok");
                }

            }
        }

        game.endGame(1);
        stop();
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        System.out.println("Server Close");
    }

}
