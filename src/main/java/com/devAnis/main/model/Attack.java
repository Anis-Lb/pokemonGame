package com.devAnis.main.model;

public class Attack {

    final int normalAtkDamage;
    final String special;
    final int specialAtkDamage;





    public Attack(int normalAtkDamage, String special, int specialAtkDamage) {
        this.normalAtkDamage = normalAtkDamage;
        this.special = special;
        this.specialAtkDamage = specialAtkDamage;
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
}
