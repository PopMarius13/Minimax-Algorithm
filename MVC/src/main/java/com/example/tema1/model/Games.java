package com.example.tema1.model;

import java.util.List;

public class Games {

    protected List<Observer> listObs;


    public void add(Observer obs) {
        this.listObs.add(obs);
    }

    public void remove(Observer obs) {
        this.listObs.remove(obs);
    }

    public void notification() {
        for(Observer obs : this.listObs){
            obs.update();
        }
    }

}
