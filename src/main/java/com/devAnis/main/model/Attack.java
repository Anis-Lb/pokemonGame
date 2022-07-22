package com.devAnis.main.model;

import com.devAnis.main.Logger;

public class Attack {

    final private int normalAtkDamage;
    final private String special;
    final private int specialAtkDamage;


    public Attack(String special) {
        this.normalAtkDamage = (int)(Math.random()*10)+1;
        this.special = special;
        this.specialAtkDamage = (int)(Math.random()*15);
    }

    public int getNormalAtkDamage() {
        return normalAtkDamage;
    }

    public String getSpecial() {
        return special;
    }

    public int getSpecialAtkDamage() {
        return specialAtkDamage;
    }

    public int normalAttack(int hpPokemonAdversary ){
        return hpPokemonAdversary - this.getNormalAtkDamage();
    }

    public int specialAttack(int hpPokemonAdversary ){
        return hpPokemonAdversary - this.getSpecialAtkDamage();
    }

    public int[] attack(String pokemonName , int turn , int nbTurnPlayer, int hpPokemonAdversary ){
        Logger log1 = Logger.getInstance();

        if (nbTurnPlayer == 2){
            hpPokemonAdversary = this.specialAttack(hpPokemonAdversary);
            log1.setLog("<br />"+"-le pokemon de player"+turn+" ("+pokemonName+") attaque une special attaque("+this.getSpecial() +") ("+ this.getSpecialAtkDamage() +") le hp de pokemon du player 2 est devenu "+ hpPokemonAdversary);
            nbTurnPlayer=0;
        }
        else {
            hpPokemonAdversary = this.normalAttack(hpPokemonAdversary);
            log1.setLog("<br />"+"-le pokemon de player "+turn+" ("+pokemonName+") attaque une normal attaque ("+ this.getNormalAtkDamage() +") le hp de pokemon du player 2 est devenu "+ hpPokemonAdversary);
            nbTurnPlayer++;
        }
        return new int[]{nbTurnPlayer, hpPokemonAdversary};
    }
}
