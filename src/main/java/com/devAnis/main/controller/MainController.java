package com.devAnis.main.controller;

import com.devAnis.main.model.Player;
import com.devAnis.main.model.Players;
import com.devAnis.main.model.Temp;
import com.devAnis.main.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.devAnis.main.model.Result;
import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.resource.pokemon.Pokemon;

@Controller
public class MainController {
	@Autowired
	private PokeApiClient pokeApiClient;

	@Autowired
	Result result;
	@Autowired
	Service qService;

	@Autowired
	Players players;



	Boolean submitted = false;
	
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}
	
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	@PostMapping("/player1")
	public String player1Turn( Model m, RedirectAttributes ra) {

		submitted = false;
		Temp temp1 = qService.getQuestions();
		m.addAttribute("temp1", temp1);


		return "player1.html";
	}

	@PostMapping("/player2")
	public String player2Turn(@ModelAttribute("temp1") Temp temp1 , Model m , RedirectAttributes ra) {

		if(temp1.getIdPokemon1() == null || temp1.getIdPokemon2() == null) {
			ra.addFlashAttribute("warning", "You must chose 2 pokemon");
			return "redirect:/";
		}

		Player player1 = new Player();
		player1.setPokemon1(pokeApiClient.getResource(Pokemon.class, String.valueOf(temp1.getIdPokemon1())).block());
		player1.setPokemon2(pokeApiClient.getResource(Pokemon.class, String.valueOf(temp1.getIdPokemon2())).block());

		players.setPlayer1(player1);


		Temp temp2 = qService.getQuestions();
		m.addAttribute("temp2",temp2);

		return "player2.html";
	}

	@PostMapping("/resultRound1")
	public String resultRound1(@ModelAttribute("temp2") Temp temp2  , Model m, RedirectAttributes ra) {

		if(temp2.getIdPokemon1() == null ||temp2.getIdPokemon2() == null  ) {
			ra.addFlashAttribute("warning", "You must chose 2 pokemon");
			return "redirect:/";
		}

		Player player2 = new Player();

		player2.setPokemon1(pokeApiClient.getResource(Pokemon.class, String.valueOf(temp2.getIdPokemon1())).block());
		player2.setPokemon2(pokeApiClient.getResource(Pokemon.class, String.valueOf(temp2.getIdPokemon2())).block());
		players.setPlayer2(player2);
		m.addAttribute("players",players);
		result.setResult(qService.battleSequence(players));

		return "resultRound1.html";
	}

	@PostMapping("/resultRound2")
	public String resultRound2(@RequestParam int changePokemonPlayer1 , @RequestParam int changePokemonPlayer2 , Model m , RedirectAttributes ra) {

		players.getPlayer1().setChangePokemon(changePokemonPlayer1);
		players.getPlayer2().setChangePokemon(changePokemonPlayer2);

		m.addAttribute("players",players);

		result.setResult(qService.battleSequence(players ));

		return "resultRound2.html";
	}

	@PostMapping("/resultRound3")
	public String resultRound3( @RequestParam int changePokemonPlayer1 , @RequestParam int changePokemonPlayer2 , Model m , RedirectAttributes ra) {


		if (players.getPlayer1().isWinner() || players.getPlayer2().isWinner()){
			return "redirect:/submit";
		}else{
			players.getPlayer1().setChangePokemon(changePokemonPlayer1);
			players.getPlayer2().setChangePokemon(changePokemonPlayer2);
			result.setResult(qService.battleSequence(players));
			return "resultRound3.html";
		}

	}

	@GetMapping("/submit")
	public String result(@ModelAttribute("temp2") Temp temp2 , Model m) {
		if(!submitted) {

			if (players.getPlayer1().isWinner()){
				result.setResult("player 1 Wins!");
			}else{
				result.setResult("player 2 Wins!");
			}
			submitted = true;
		}

		return "result.html";
	}


	


}
