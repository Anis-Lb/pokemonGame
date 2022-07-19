package com.devAnis.main.model;

import org.springframework.stereotype.Component;
import skaro.pokeapi.resource.pokemon.Pokemon;

@Component
public class Player {

    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private int points;
    private int changePokemon = 1;


    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(Pokemon pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getChangePokemon() {
        return changePokemon;
    }

    public void setChangePokemon(int changePokemon) {
        this.changePokemon = changePokemon;
    }

    public void addPoint(){
        this.points++;
    }

    public boolean isWinner(){
        return this.points == 2;
    }
}
