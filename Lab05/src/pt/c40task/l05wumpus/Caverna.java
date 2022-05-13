package pt.c40task.l05wumpus;

public class Caverna {
	Sala s[][];
	private int nBuracos;
	private int nWumpus;
	private int nOuro;
	private int nHeroi;
	
	public Caverna() {
		s = new Sala[4][4];
		/*for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				s[i][j] = new Sala(' ');
			}
		}*/
	}
	//teste
	
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
	
	public void insereSala(int linha, int coluna, Brisa b){		
		this.s[linha][coluna].b = b; 
	}
	
	public void insereSala(int linha, int coluna, Fedor f){		
		this.s[linha][coluna].f = f; 
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
	
	
	public void imprimeCaverna() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(s[i][j].getSituacao());
			}
			System.out.println("");
		}
	}
}
