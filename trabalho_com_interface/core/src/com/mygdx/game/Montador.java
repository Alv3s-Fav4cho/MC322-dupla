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
		// matriz 5x5, ordem n=5
		int n = 5;
		// String classe = "teste";
		Protagonista prot;
		PessoaPerdida pessoap;
		Porta porta;
		Random rand = new Random();

		for (int i = 0; i <= 2; i++) {
			/*
			 * if (i % n == 0) { linha = i/n - 1; coluna = n - 1; } else if(i/n == 0){ linha
			 * = i/n; coluna = i%n - 1; } else{ linha = i/n; coluna = i%n - 1; }
			 */

			if (i == 0) {
				// classe = "Protagonista";
				prot = new Protagonista();
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
				prot.setLinha(linha);
				prot.setColuna(coluna);
				prot.setType("prot");
				this.neth.insereSala(linha, coluna, prot);

			} else if (i == 1) {
				// classe = "PessoaPerdida";
				pessoap = new PessoaPerdida();
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
				pessoap.setLinha(linha);
				pessoap.setColuna(coluna);
				pessoap.setType("pessoap");
				this.neth.insereSala(linha, coluna, pessoap);

			} else {
				// classe = "Porta";
				porta = new Porta();
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
				porta.setLinha(linha);
				porta.setColuna(coluna);
				porta.setType("porta");
				this.neth.insereSala(linha, coluna, porta);
			}
		}
	}
}