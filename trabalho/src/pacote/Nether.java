package pacote;

import pt.c40task.l05wumpus.Sala;

public class Nether {
	Sala s[][];

	public Nether() {
		s = new Sala[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				s[i][j] = new Sala();
			}
		}
	}

	public void insereSala(int linha, int coluna, Ator atores[]) {

	}

	public void MovimentoValido(int linha_atual, int coluna_atual, int nova_linha, int nova_coluna) {

	}

	public void alteraNether(int linha_atual, int coluna_atual, int nova_linha, int nova_coluna) {

	}

	/*
	 * public boolean existeKit(int linha_atual, int coluna_atual) {
	 * 
	 * }
	 */

	/*
	 * public boolean existeMunicao(int linha_atual, int coluna_atual) {
	 * 
	 * }
	 */

	/*
	 * public boolean existePessoaPerdida(int linha_atual, int coluna_atual) {
	 * 
	 * }
	 */

	/*
	 * public boolean existePorta(int linha_atual, int coluna_atual) {
	 * 
	 * }
	 */


	/*
	public char[][] montaSaida(){ 
		
	}
	 */

	/*
	 * imprimeCaverna(char[][] saida){
	 * 
	 * }
	 */
}
