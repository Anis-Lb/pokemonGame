package com.devAnis.main.model;
import java.util.List;
import org.springframework.stereotype.Component;
import skaro.pokeapi.resource.pokemon.Pokemon;

@Component
public class Temp {

	private List<Pokemon> listPokemon1;
	private List<Pokemon> listPokemon2;
	private String idPokemon1;
	private String idPokemon2;



	public List<Pokemon> getListPokemon1() {
		return listPokemon1;
	}

	public void setListPokemon1(List<Pokemon> listPokemon1) {
		this.listPokemon1 = listPokemon1;
	}

	public List<Pokemon> getListPokemon2() {
		return listPokemon2;
	}

	public void setListPokemon2(List<Pokemon> listPokemon2) {
		this.listPokemon2 = listPokemon2;
	}

	public String getIdPokemon1() {
		return idPokemon1;
	}

	public void setIdPokemon1(String idPokemon1) {
		this.idPokemon1 = idPokemon1;
	}

	public String getIdPokemon2() {
		return idPokemon2;
	}

	public void setIdPokemon2(String idPokemon2) {
		this.idPokemon2 = idPokemon2;
	}


}
