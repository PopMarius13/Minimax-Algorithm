package com.example.tema1.model.minimax;

import com.example.tema1.model.board.Point;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Node {

    private Point point;
    private int score;
    private boolean isMaxPlayer;

    public Node(Point point, boolean isMaxPlayer) {
        this.point = point;
        this.isMaxPlayer = isMaxPlayer;
    }

    public Node(Point point, int score, boolean isMaxPlayer) {
        this.point = point;
        this.score = score;
        this.isMaxPlayer = isMaxPlayer;
    }

    @Override
    public String toString() {
        return "Node{" +
                "point=" + point +
                ", score=" + score +
                ", isMaxPlayer=" + isMaxPlayer;
    }
}
