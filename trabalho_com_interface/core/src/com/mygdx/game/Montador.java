package com.mygdx.game;

import java.util.Random;

public class Montador {
	Nether neth;
	
	public void conectaNether(Nether neth) {
		this.neth = neth;
	}

	public void montar() {
		int x;
		int linha = 0;
		int coluna = 0;
		int n = 5;
		
		Ator prot;
		Ator pessoap;
		Ator porta;
		Random rand = new Random();

		for (int i = 0; i <= 2; i++) {

			if (i == 0) {
				x = rand.nextInt(25) + 1;
				if (x % n == 0) {
					linha = x / n - 1;
					coluna = n - 1;
				} else if (x / n == 0) {
					linha = x / n;
					coluna = x % n - 1;
				}else{
					linha = x / n;
					coluna = x % n - 1;
				}
				prot = AtorFactory.getAtor("prot", linha, coluna, neth);
				this.neth.insereSala(linha, coluna, prot);
				
			} else if (i == 1) {
				x = rand.nextInt(25) + 1;
				if (x % n == 0) {
					linha = x / n - 1;
					coluna = n - 1;
				} else if (x / n == 0) {
					linha = x / n;
					coluna = x % n - 1;
				} else {
					linha = x / n;
					coluna = x % n - 1;
				}
				pessoap = AtorFactory.getAtor("pessoap", linha, coluna, neth);
				this.neth.insereSala(linha, coluna, pessoap);
				
			} else {
				x = rand.nextInt(25) + 1;
				if (x % n == 0) {
					linha = x / n - 1;
					coluna = n - 1;
				} else if (x / n == 0) {
					linha = x / n;
					coluna = x % n - 1;
				} else {
					linha = x / n;
					coluna = x % n - 1;
				}
				porta = AtorFactory.getAtor("porta", linha, coluna, neth);
				this.neth.insereSala(linha, coluna, porta);			
			}
		}
	}
}