package pacote;

import java.util.ArrayList;

public class Nether {
	Sala s[][];
	
	public Nether() {
		s = new Sala[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				s[i][j] = new Sala(i,j);
			}
		}
	}

	public void insereSala(int linha, int coluna, Ator ator) {
		this.s[linha][coluna].atores.add(ator);		
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

	
	public void imprimeNether1(){
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(s[i][j].atores.size() > 0) {
					for(int k = 0; k < s[i][j].atores.size(); k++) {
						System.out.print("["+i+"]["+j+"]: ");
						System.out.print(s[i][j].atores.get(k).getType()+" ");
						System.out.println();
					}					
				}
				else {
					System.out.print("["+i+"]["+j+"]: ");
					System.out.print("Posicao vazia ");
					System.out.println();
				}
			}
		}
	}
	
	public void imprimeNether2(){
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(s[i][j].atoresNaSala());
			}
			System.out.println();
		}
	}
	 
}
