package com.devAnis.main.service;

public class UtilClass {

    int[] random = new int[25];
    int i = 0;
    boolean equals;
    public UtilClass() {

        while(i != 25) {
            random[i] = (int)(Math.random()*500)+1;
            equals = false;
            for(int j = 0; j != i; j++) {
                if(random[j] == random[i]) {
                    equals = true;
                }
            }
            if(!equals) {
                i++;
            }
        }

        //read\\
    }
    public int[] getRandom() {
        return random;
    }
}
