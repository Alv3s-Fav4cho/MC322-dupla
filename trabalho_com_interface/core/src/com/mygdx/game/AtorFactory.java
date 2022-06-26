package com.mygdx.game;

public class AtorFactory {

	public static Ator getAtor(String type, int linha, int coluna, Nether neth) {
		if ("prot".equalsIgnoreCase(type))
			return new Protagonista(type, linha, coluna, neth);
		else if ("pessoap".equalsIgnoreCase(type))
			return new PessoaPerdida(type, linha, coluna, neth);
		else if ("porta".equalsIgnoreCase(type))
			return new Porta(type, linha, coluna, neth);
		else if ("monstro".equalsIgnoreCase(type))
			return new Monstro(type, linha, coluna);
		else if ("kit".equalsIgnoreCase(type))
			return new KitMedico(type, linha, coluna);
		else if ("municao".equalsIgnoreCase(type))
			return new Municao(type, linha, coluna);
		
		return null;
	}
	
}