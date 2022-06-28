package com.mygdx.game;

import java.util.ArrayList;

public class Porta extends Ator{

	public Porta(String type, int linha, int coluna, Nether neth) {
		setType(type);
		setLinha(linha);
		setColuna(coluna);
		conectaNether(neth);
	}
}