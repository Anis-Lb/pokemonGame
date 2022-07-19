package com.devAnis.main.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.devAnis.main.model.Attack;
import com.devAnis.main.model.Players;
import com.devAnis.main.model.Temp;
import org.springframework.beans.factory.annotation.Autowired;
import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.resource.pokemon.Pokemon;


@org.springframework.stereotype.Service
public class Service {


	@Autowired
	Temp temp;



	@Autowired
	private PokeApiClient pokeApiClient;


	public Temp getQuestions(){
		UtilClass utilClass = new UtilClass();
		int[] listRandomID = utilClass.getRandom();
		List<Pokemon> ListRandomPokemon = new ArrayList<>();
		for (int i=0; i<listRandomID.length; i++){
			int number = listRandomID[i];
			 Pokemon pokemon = pokeApiClient.getResource(Pokemon.class, String.valueOf(number)).block();
			 ListRandomPokemon.add(pokemon);
		}



		temp.setListPokemon1(ListRandomPokemon.subList(0, listRandomID.length / 2));
		temp.setListPokemon2(ListRandomPokemon.subList(listRandomID.length/2,listRandomID.length));

		return temp;
	}


	public String battleSequence(Players players) {

		String log = "";


		Pokemon pokemonPlayer1 ;
		Pokemon pokemonPlayer2 ;

		if (players.getPlayer1().getChangePokemon() == 1){

			pokemonPlayer1 = players.getPlayer1().getPokemon1();
		}else{
			 pokemonPlayer1 = players.getPlayer1().getPokemon2();
		}

		if (players.getPlayer2().getChangePokemon() == 1){
			 pokemonPlayer2 = players.getPlayer2().getPokemon1();
		}else{
			 pokemonPlayer2 = players.getPlayer2().getPokemon2();

		}

		int normalAtkDmg = (int)(Math.random()*10)+1;
		int specialAtkDmg = (int)(Math.random()*15);
		Attack attackPokemonPlayer1 = new Attack(
				normalAtkDmg,
				Objects.requireNonNull(pokemonPlayer1.getAbilities().stream().findFirst().orElse(null)).getAbility().getName(),
				specialAtkDmg);


		normalAtkDmg = (int)(Math.random()*10)+1;
		specialAtkDmg = (int)(Math.random()*15);
		Attack attackPokemonPlayer2 = new Attack(
				normalAtkDmg,
				Objects.requireNonNull(pokemonPlayer2.getAbilities().stream().findFirst().orElse(null)).getAbility().getName(),
				specialAtkDmg
				);



		int hpPokemonPlayer1 = 20;
		int hpPokemonPlayer2 = 20;
		int normalAtk = 0;
		int specialAtk = 0;
		int nbTurnPlayer1 = 0;
		int nbTurnPlayer2 = 0;

		int turn = 1;

		while (true) {



			if (hpPokemonPlayer1 >= 0){
				if (hpPokemonPlayer2 >= 0){

					if (turn == 1){
						if (nbTurnPlayer1 == 2){
							hpPokemonPlayer2 = hpPokemonPlayer2 - attackPokemonPlayer1.getSpecialAtkDamage();
							turn = 2;
							log = log + "<br />"+"-le pokemon de player 1 ("+pokemonPlayer1.getName()+") attaque une special attaque("+attackPokemonPlayer1.getSpecial() +") ("+ attackPokemonPlayer1.getSpecialAtkDamage() +") le hp de pokemon du player 2 est devenu "+ hpPokemonPlayer2;
							nbTurnPlayer1=0;
						}
						else {
							hpPokemonPlayer2 = hpPokemonPlayer2 - attackPokemonPlayer1.getNormalAtkDamage();
							turn = 2;
							log = log + "<br />"+"-le pokemon de player 1 ("+pokemonPlayer1.getName()+") attaque une normal attaque ("+ attackPokemonPlayer1.getNormalAtkDamage() +") le hp de pokemon du player 2 est devenu "+ hpPokemonPlayer2;
							nbTurnPlayer1++;
						}

					}else{

						if (nbTurnPlayer2 == 2){
							hpPokemonPlayer1 = hpPokemonPlayer1 - attackPokemonPlayer2.getSpecialAtkDamage();
							turn = 1 ;
							log = log + "<br />"+"-le pokemon de player 2 ("+pokemonPlayer2.getName()+ ") attaque une special attaque("+ attackPokemonPlayer2.getSpecial()+") ("+ attackPokemonPlayer2.getSpecialAtkDamage() +") le hp de pokemon du player 1 est devenu "+ hpPokemonPlayer1;
							nbTurnPlayer2=0;
						}else{
							hpPokemonPlayer1 = hpPokemonPlayer1 - attackPokemonPlayer2.getNormalAtkDamage();
							turn = 1 ;
							log = log + "<br />"+"-le pokemon de player 2 ("+pokemonPlayer2.getName()+ ") attaque une normal attaque ("+ attackPokemonPlayer2.getNormalAtkDamage() +") le hp de pokemon du player 1 est devenue "+ hpPokemonPlayer1;
							nbTurnPlayer2++;
						}


					}
				}else{
					players.getPlayer1().addPoint();
					log = log + "<br />"+"-player 1 wins!";
					break;
				}
			}else{
				players.getPlayer2().addPoint();
				log = log + "<br />"+"-player 2 wins!";
				break;
			}
		}

		log = log+ "<br />"+ "<br />"+"-le player 1 a : "+players.getPlayer1().getPoints()+" points";
		log = log+ "<br />"+"-le player 2 a : "+players.getPlayer2().getPoints()+" points";


		return log;

	}




}
