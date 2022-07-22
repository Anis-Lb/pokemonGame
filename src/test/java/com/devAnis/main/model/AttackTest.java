package com.devAnis.main.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class AttackTest {

    @Test
    void attack() {

        //test normal attack
        Attack attack = new Attack("Mouse");

        int[] result = attack.attack( "Mouse" , 1 , 1, 20 );

        Assertions.assertEquals(2, result[0]);
        Assertions.assertEquals(attack.normalAttack(20) , result[1]);


        //test special attack
        attack = new Attack("Mouse");

        result = attack.attack( "Mouse" , 1 , 2, 20 );

        Assertions.assertEquals(0, result[0]);
        Assertions.assertEquals(attack.specialAttack(20) , result[1]);
    }

    @Test
    void normalAttack() {
        Attack attack = new Attack("Mouse");
        Assertions.assertTrue(attack.getNormalAtkDamage() <= 10);
    }

    @Test
    void specialAttack() {
        Attack attack = new Attack("Mouse");
        Assertions.assertTrue(attack.getSpecialAtkDamage() <= 15);
    }

}