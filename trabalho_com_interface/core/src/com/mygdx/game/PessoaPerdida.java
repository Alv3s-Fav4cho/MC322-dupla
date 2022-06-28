package com.mygdx.game;

import java.util.ArrayList;

public class PessoaPerdida extends Ator{
	private boolean sangrando;
	private boolean achada;
	
	public PessoaPerdida(String type, int linha, int coluna, Nether neth) {
		setType(type);
		setLinha(linha);
		setColuna(coluna);
		conectaNether(neth);
	}
	
	public boolean isAchada() {
		return achada;
	}

	public void setAchada(boolean achada) {
		this.achada = achada;
	}

	public boolean isSangrando() {
		return sangrando;
	}

	public void setSangrando(boolean sangrando) {
		this.sangrando = sangrando;
	}
}
