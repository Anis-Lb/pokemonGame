package com.devAnis.main.model;
import org.springframework.stereotype.Component;

@Component
public class Util {

	private String idPokemon1;
	private String idPokemon2;


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
