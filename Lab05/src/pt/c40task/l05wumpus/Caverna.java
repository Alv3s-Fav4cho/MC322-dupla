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
	
	/*public void insereSala(int linha, int coluna, Brisa b){		
		this.s[linha][coluna].b = b; 
	}

	public void insereSala(int linha, int coluna, Heroi h){		
		this.s[linha][coluna].h = h;
		this.s[linha][coluna].setVisitada(true);			
		
	}
	
	public void insereSala(int linha, int coluna, Wumpus w){		
		this.s[linha][coluna].w = w; 
	}
	
	public void insereSala(int linha, int coluna, Buraco B){		
		this.s[linha][coluna].B = B; 
	}
	
	public void insereSala(int linha, int coluna, Ouro o){		
		this.s[linha][coluna].o = o; 
	}
	
	public void insereSala(int linha, int coluna, Fedor f){		
		this.s[linha][coluna].f = f; 
	}*/
	
	public void insereSala(int linha, int coluna, Componente comps[]){		
		this.s[linha][coluna].componentes = comps;		
	}
	
	public void incluiBrisas(int linha, int coluna, Componente c1[], Componente c2[], Componente c3[], Componente c4[]) {
		if(linha-1 >= 0) {
			c1[4] = new Brisa();
			c1[4].setLinha(linha-1);
			c1[4].setColuna(coluna);
			insereSala(linha-1, coluna, c1);
			//this.s[linha-1][coluna].componentes[4] = new Brisa();
			//insereSala(linha-1, coluna, new Brisa());
		}
		if(linha+1 < 4) {
			c2[4] = new Brisa();
			c2[4].setLinha(linha+1);
			c2[4].setColuna(coluna);
			insereSala(linha+1, coluna, c2);
			//this.s[linha+1][coluna].componentes[4] = new Brisa();
			//insereSala(linha+1, coluna, new Brisa()); 
		}
		if(coluna-1 >= 0) {
			c3[4] = new Brisa();
			c3[4].setLinha(linha);
			c3[4].setColuna(coluna-1);
			insereSala(linha, coluna-1, c3);
			//this.s[linha][coluna-1].componentes[4] = new Brisa();
			//insereSala(linha, coluna-1, new Brisa()); 
		}
		if(coluna+1 < 4) {
			c4[4] = new Brisa();
			c4[4].setLinha(linha);
			c4[4].setColuna(coluna+1);
			insereSala(linha, coluna+1, c4);
			//this.s[linha][coluna+1].componentes[4] = new Brisa();
			//insereSala(linha, coluna+1, new Brisa());
		}
	}
	
	public void incluiFedor(int linha, int coluna) {
		if(linha-1 >= 0)
			s[linha-1][coluna].componentes[5] = new Fedor();
			//insereSala(linha-1, coluna, new Fedor());
		if(linha+1 < 4)
			s[linha+1][coluna].componentes[5] = new Fedor();
			//insereSala(linha+1, coluna, new Fedor()); 
		if(coluna-1 >= 0)
			s[linha][coluna-1].componentes[5] = new Fedor();
			//insereSala(linha, coluna-1, new Fedor()); 
		if(coluna+1 < 4)
			s[linha][coluna+1].componentes[5] = new Fedor();
			//insereSala(linha, coluna+1, new Fedor());
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
		if (s[linha_atual][coluna_atual].componentes[1] != null) {
			return true;
		}
		return false;
	}
	
	public boolean existeBuraco(int linha_atual, int coluna_atual) {
		if (s[linha_atual][coluna_atual].componentes[2] != null) {
			return true;
		}
		return false;
	}
	
	/*public void insereSala(String entrada) {
		insereSala(Integer.parseInt(entrada.substring(0, 1)),
				   Integer.parseInt(entrada.substring(2, 3)),
			 						   entrada.substring(4));
	}*/
	
	/*public void insereSala(String entrada[]) {
		for(int i = 0; i < entrada.length; i++) {
			insereSala(Integer.parseInt(entrada[i].substring(0, 1)),
					Integer.parseInt(entrada[i].substring(3, 4)),
					entrada[i].substring(6));			
		}
	}*/
	
	/*public void incluiFedorBrisas() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0 ; j < 4; j++) {
				if(this.s[i][j] != null) {
					if(this.s[i][j].getSituacao().compareTo("B") == 0) {
						if(i-1 >= 0 && this.s[i - 1][j] == null)
							this.s[i - 1][j] = new Brisa("b");
						if(i+1 < 4 && this.s[i + 1][j] == null)
							this.s[i + 1][j] = new Brisa("b");
						if(j-1 >= 0 && this.s[i][j - 1] == null)
							this.s[i][j - 1] = new Brisa("b");
						if(j+1 < 4 && this.s[i][j + 1] == null)
							this.s[i][j + 1] = new Brisa("b");
					}
					else if(this.s[i][j].getSituacao().compareTo("W") == 0) {
						if(i-1 >= 0 && (this.s[i - 1][j] == null || this.s[i - 1][j].getSituacao().compareTo("b") == 0))
							this.s[i - 1][j] = new Fedor("f");
						if(i+1 < 4 && (this.s[i + 1][j] == null || this.s[i + 1][j].getSituacao().compareTo("b") == 0))
							this.s[i + 1][j] = new Fedor("f");
						if(j-1 >= 0 && (this.s[i][j - 1] == null || this.s[i][j - 1].getSituacao().compareTo("b") == 0))
							this.s[i][j - 1] = new Fedor("f");
						if(j+1 < 4 && (this.s[i][j + 1] == null || this.s[i][j + 1].getSituacao().compareTo("b") == 0))
							this.s[i][j + 1] = new Fedor("f");
					}
				}
			}
		}
	}*/
	
	/*public void incluiVazios() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0 ; j < 4; j++) {
				if(this.s[i][j] == null) {
					this.s[i][j] = new Sala("-");
				}
			}
		}
	}*/
	
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
	
	public void imprimeCaverna() {
		for(int i = 0; i < 4; i++) {
	    	  for(int j = 0; j < 4; j++) {
	    		  //System.out.print("sala["+i+"]["+j+"]:");
	    		  s[i][j].componentesNaSala();
	    	  }
	    	  System.out.println();
	      }
	}
}
