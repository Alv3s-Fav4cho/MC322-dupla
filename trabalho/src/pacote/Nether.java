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

	public boolean movimentoValido(int linha_atual, int coluna_atual, int nova_linha, int nova_coluna, String tipo) {
		if(nova_linha >= 0 && nova_linha < 5 && nova_coluna >= 0 && nova_coluna < 5) {
			alteraNether(linha_atual, coluna_atual, nova_linha, nova_coluna, tipo);
			return true;
		}
		return false;
	}

	public void alteraNether(int linha_atual, int coluna_atual, int nova_linha, int nova_coluna, String tipo) {
		int indice = achaIndice(linha_atual, coluna_atual, tipo);
		s[nova_linha][nova_coluna].atores.add(s[linha_atual][coluna_atual].atores.get(indice));
		s[linha_atual][coluna_atual].atores.remove(indice);
	}
	
	public int achaIndice(int linha_atual, int coluna_atual, String tipo) {
		int i;
		int indice_desejado = -1;
		for(i = 0; i < s[linha_atual][coluna_atual].atores.size(); i++){
			if(s[linha_atual][coluna_atual].atores.get(i).getType() == tipo) {
				indice_desejado = i;
				return indice_desejado;
			}
		}
		return indice_desejado;
	}

	public int existeKit(int linha_atual, int coluna_atual) {
		int valor = -1;
		if(s[linha_atual][coluna_atual].atores.size() == 1) {
			return valor;
		}
		valor = achaIndice(linha_atual, coluna_atual, "kit");
		return valor;
	}
	
	public int existeMunicao(int linha_atual, int coluna_atual) {
		int valor = -1;
		if(s[linha_atual][coluna_atual].atores.size() == 1) {
			return valor;
		}
		valor = achaIndice(linha_atual, coluna_atual, "municao");
		return valor;
	}
	
	public int existePessoaPerdida(int linha_atual, int coluna_atual) {
		int valor = -1;
		if(s[linha_atual][coluna_atual].atores.size() == 1) {
			return valor;
		}
		valor = achaIndice(linha_atual, coluna_atual, "pessoap");
		return valor;
	}	 
	
	public int existeMonstro(int linha_atual, int coluna_atual) {
		int valor = -1;
		if(s[linha_atual][coluna_atual].atores.size() == 1) {
			return valor;
		}
		valor = achaIndice(linha_atual, coluna_atual, "monstro");
		return valor;
	}

	public int existePorta(int linha_atual, int coluna_atual) {
		int valor = -1;
		if(s[linha_atual][coluna_atual].atores.size() == 1) {
			return valor;
		}
		valor = achaIndice(linha_atual, coluna_atual, "porta");
		return valor;
	}
	
	public ArrayList<String> atores_na_sala(int linha, int coluna){
		ArrayList<String> atores = new ArrayList<String>();
		/*
		 * if(s[linha][coluna].atores.size() > 1){ }
		 */
		s[linha][coluna].setSala_investigada(true);
		for(int i = 0; i < s[linha][coluna].atores.size(); i++) {
			atores.add(s[linha][coluna].atores.get(i).getType());				
		}
		return atores;
	}
	
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
