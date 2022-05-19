package pt.c40task.l05wumpus;

public class Caverna {
	Sala s[][];
	
	public Caverna() {
		s = new Sala[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				s[i][j] = new Sala();
			}
		}
	}
	
	public Sala procura_sala(int linha, int coluna) {
		return s[linha][coluna];
	}
	
	public void insereSala(int linha, int coluna, Componente comps[]){		
		this.s[linha][coluna].componentes = comps;		
	}
	
	public void incluiBrisas(int linha, int coluna, Brisa brisa) {
		if(linha-1 >= 0) {
			brisa.setLinha(linha-1);
			brisa.setColuna(coluna);
			s[linha-1][coluna].componentes[4] = brisa;
		}
		if(linha+1 < 4) {
			brisa.setLinha(linha+1);
			brisa.setColuna(coluna);
			s[linha+1][coluna].componentes[4] = brisa;
		}
		if(coluna-1 >= 0) {
			brisa.setLinha(linha);
			brisa.setColuna(coluna-1);
			s[linha][coluna-1].componentes[4] = brisa;
		}
		if(coluna+1 < 4) {
			brisa.setLinha(linha);
			brisa.setColuna(coluna+1);
			s[linha][coluna+1].componentes[4] = brisa;
		}
	}
	
	public void incluiFedor(int linha, int coluna, Fedor fedor) {
		if(linha-1 >= 0) {
			fedor.setLinha(linha-1);
			fedor.setColuna(coluna);
			s[linha-1][coluna].componentes[5] = fedor;
		}
		if(linha+1 < 4) {
			fedor.setLinha(linha+1);
			fedor.setColuna(coluna);
			s[linha+1][coluna].componentes[5] = fedor;
		}
		if(coluna-1 >= 0) {
			fedor.setLinha(linha);
			fedor.setColuna(coluna-1);
			s[linha][coluna-1].componentes[5] = fedor;
		}
		if(coluna+1 < 4) {
			fedor.setLinha(linha);
			fedor.setColuna(coluna+1);
			s[linha][coluna+1].componentes[5] = fedor;
		}
	}
	
	public boolean verifica_consistencia(int linha, int coluna, char c) {
		if (s[linha][coluna].consistencia(c))
			return true;
		return false;
	}
	
	public boolean movimentoValido(int linha_atual, int coluna_atual,  int nova_linha, int nova_coluna) {
		if(nova_linha >= 0 && nova_linha < 4 && nova_coluna >= 0 && nova_coluna < 4) {
			alteraCaverna(linha_atual, coluna_atual, nova_linha, nova_coluna);
			return true;
		}
		return false;
	}
	
	public void alteraCaverna(int linha_atual, int coluna_atual, int nova_linha, int nova_coluna){
			s[nova_linha][nova_coluna].componentes[0] = s[linha_atual][coluna_atual].componentes[0];
			s[nova_linha][nova_coluna].setVisitada(true);
			
			s[linha_atual][coluna_atual].componentes[0] = null;
	}
	
	public boolean existeOuro(int linha_atual, int coluna_atual) {
		if (s[linha_atual][coluna_atual].componentes[3] != null) {
			s[linha_atual][coluna_atual].componentes[3] = null;
			return true;
		}
		return false;
	}

	public boolean existeWumpus(int linha_atual, int coluna_atual) {
		if (s[linha_atual][coluna_atual].componentes[1] != null)
			return true;
		return false;
	}
	
	public boolean existeBuraco(int linha_atual, int coluna_atual) {
		if (s[linha_atual][coluna_atual].componentes[2] != null)
			return true;
		return false;
	}
	
	public char[][] montaSaida(){
		char[][] saida = new char[4][4]; 
		for(int i = 0; i < 4; i++) {
	    	  for(int j = 0; j < 4; j++) {
	    		  if (s[i][j].componentes[3] != null && s[i][j].isVisitada()) 
	    			  saida[i][j] = 'o'; 	    					    		 
	    		  else if (s[i][j].componentes[2] != null && s[i][j].isVisitada()) 
	    			  saida[i][j] = 'B';
	    		  else if (s[i][j].componentes[1] != null && s[i][j].isVisitada())
	    			  saida[i][j] = 'w';
	    		  else if (s[i][j].componentes[0] != null && s[i][j].isVisitada())
	    			  saida[i][j] = 'p';
	    		  else if (s[i][j].componentes[5] != null && s[i][j].isVisitada())
	    			  saida[i][j] = 'f';
	    		  else if (s[i][j].componentes[4] != null && s[i][j].isVisitada())
	    			  saida[i][j] = 'b';
	    		  else if(s[i][j].isVisitada())
	    			  saida[i][j] = '#';
	    		  else
	    			  saida[i][j] = '-';
	    	  }
	      }
		return saida;
	}
	
	public void imprimeCaverna(char[][] saida) {
		for(int i = 0; i < 4; i++) {
	    	  for(int j = 0; j < 4; j++) {
	    		  System.out.print(saida[i][j] + " ");
	    	  }
	    	  System.out.println();
		}
		
	}
}