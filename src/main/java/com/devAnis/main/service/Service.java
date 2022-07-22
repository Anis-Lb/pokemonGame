package com.devAnis.main.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.devAnis.main.Logger;
import com.devAnis.main.model.Attack;
import com.devAnis.main.model.Players;
import org.springframework.beans.factory.annotation.Autowired;
import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.resource.pokemon.Pokemon;


@org.springframework.stereotype.Service
public class Service {



	@Autowired
	private PokeApiClient pokeApiClient;


	public List<Pokemon> getRandomListPokemon(){

		int[] listRandomID = new Random().ints(25,1 , 500).toArray();
		List<Pokemon> ListRandomPokemon = new ArrayList<>();

		for (int number : listRandomID) {
			Pokemon pokemon = pokeApiClient.getResource(Pokemon.class, String.valueOf(number)).block();
			ListRandomPokemon.add(pokemon);
		}

		return ListRandomPokemon;
	}


	public String battleSequence(Players players) {


		Logger log = Logger.getInstance();
		log.init();
		int[] arr;
		Pokemon pokemonPlayer1 = players.getPlayer1().getPokemon() ;
		Pokemon pokemonPlayer2 = players.getPlayer2().getPokemon() ;
		int hpPokemonPlayer1 = 20;
		int hpPokemonPlayer2 = 20;
		int nbTurnPlayer1 = 0;
		int nbTurnPlayer2 = 0;
		int winner = 0;
		int turn = 1;

		Attack attackPokemonPlayer1 = new Attack(
				Objects.requireNonNull(pokemonPlayer1.getAbilities().stream().findFirst().orElse(null)).getAbility().getName());

		Attack attackPokemonPlayer2 = new Attack(
				Objects.requireNonNull(pokemonPlayer2.getAbilities().stream().findFirst().orElse(null)).getAbility().getName()
				);


		while (true) {

			if (hpPokemonPlayer1 > 0){
				if (hpPokemonPlayer2 > 0){

					if (turn == 1){

						arr = attackPokemonPlayer1.attack(pokemonPlayer1.getName() , turn , nbTurnPlayer1, hpPokemonPlayer2 );
						turn =  2;
						hpPokemonPlayer2= arr[1];
						nbTurnPlayer1 = arr[0];

					}else{

						arr = attackPokemonPlayer2.attack(pokemonPlayer2.getName() , turn , nbTurnPlayer2, hpPokemonPlayer1 );
						turn =  1;
						hpPokemonPlayer1= arr[1];
						nbTurnPlayer2 = arr[0];
					}
				}else{
					players.getPlayer1().addPoint();
					break;
				}
			}else{
				players.getPlayer2().addPoint();
				break;
			}
		}

		winner = 2 - (1 % turn);
		log.setLog("<br />"+"-player "+winner+" wins!"+"<br />"+
				"<br />"+"-le player 1 a : "+players.getPlayer1().getPoints()+" points" +
				"<br />"+"-le player 2 a : "+players.getPlayer2().getPoints()+" points");


		return log.getLog();

	}




}
