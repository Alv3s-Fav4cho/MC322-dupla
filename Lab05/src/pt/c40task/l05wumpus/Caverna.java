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
	
	public void incluiBrisas(int linha, int coluna) {
		if(linha-1 >= 0)
			this.s[linha-1][coluna].componentes[4] = new Brisa();
		if(linha+1 < 4)
			this.s[linha+1][coluna].componentes[4] = new Brisa();
		if(coluna-1 >= 0)
			this.s[linha][coluna-1].componentes[4] = new Brisa();
		if(coluna+1 < 4)
			this.s[linha][coluna+1].componentes[4] = new Brisa();
	}
	
	public void incluiFedor(int linha, int coluna) {
		if(linha-1 >= 0)
			s[linha-1][coluna].componentes[5] = new Fedor();
		if(linha+1 < 4)
			s[linha+1][coluna].componentes[5] = new Fedor();
		if(coluna-1 >= 0)
			s[linha][coluna-1].componentes[5] = new Fedor();
		if(coluna+1 < 4)
			s[linha][coluna+1].componentes[5] = new Fedor();
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