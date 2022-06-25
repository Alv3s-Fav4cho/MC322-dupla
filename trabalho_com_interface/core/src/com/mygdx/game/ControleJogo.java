package com.mygdx.game;

public class ControleJogo {
	Protagonista prot;
	private char movimento;
	private char acao;

	public void conectaProtagonista(Sala s[][]) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < s[i][j].atores.size(); k++) {
					if(s[i][j].atores.get(k).getType() == "prot") {
						this.prot = (Protagonista)s[i][j].atores.get(k);						
					}					
				}
			}
		}
	}
	
	public void conectaMainUI() {
		
	}

	/*
	 * public boolean comandoValido(char c) {
	 * 
	 * }
	 */

	public void comando(char c) {

	}

	public void atira() {
		
	}

	public void curaSiMesmo() {
		
	}
	
	public void curaOutro() {
		
	}
	
	public void RecolheMunicao() {
		
	}
	
	public void RecolheKit() {
		
	}
	
	public void investiga() {
		
	}
	
	public void executaMovimeto(char movimento) {
		
	}
	
	public void executaAcao(char acao) {
		
	}
}
