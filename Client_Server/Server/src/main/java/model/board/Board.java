package model.board;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Board {

    private int arrowsNumber;
    private Integer[][] squares;

    public Board(int arrowsNumber) {
        this.arrowsNumber = arrowsNumber;
        squares = new Integer[arrowsNumber + 1][arrowsNumber + 1];
        for(int i = 0 ; i < arrowsNumber; i++) {
            for(int j = 0 ; j < arrowsNumber; j++){
                squares[i][j] = 0;
            }
        }
    }

    public int checkWinner(boolean playerMove) {
        for (int i = 0; i < arrowsNumber; i++) {
            Set<Integer> set = new HashSet<>();
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            Set<Integer> set3 = new HashSet<>();

            for (int j = 0; j < arrowsNumber; j++) {
                if (squares[i][j] != 0 && set.contains(squares[i][j])) {
                    return playerMove ? 1 : -1;
                }
                set.add(squares[i][j]);
                if (squares[j][i] != 0 && set1.contains(squares[j][i])) {
                    return playerMove ? 1 : -1;
                }
                set1.add(squares[j][i]);
                if (i - j >= 0) {
                    if (squares[i - j][j] != 0 && set2.contains(squares[i - j][j])) {
                        return playerMove ? 1 : -1;
                    }
                    set2.add(squares[i - j][j]);
                }
                if (i + j < arrowsNumber) {
                    if (squares[i + j][j] != 0 && set3.contains(squares[i + j][j])) {
                        return playerMove ? 1 : -1;
                    }
                    set3.add(squares[i + j][j]);
                }
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < arrowsNumber; i++) {
            for(int j = 0 ; j < arrowsNumber; j++){
                sb.append(" ").append(squares[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
