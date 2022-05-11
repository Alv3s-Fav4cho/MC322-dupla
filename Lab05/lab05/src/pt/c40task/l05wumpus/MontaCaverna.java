package pt.c40task.l05wumpus;

public class MontaCaverna {
	Sala s[][];
	
	public MontaCaverna() {
		s = new Sala[4][4];
	}
	
	public void insereSala(int linha, int coluna, String situacao){
		linha--;
		coluna--;
		switch (situacao) {
		case "P":{
			this.s[linha][coluna] = new Sala(situacao);
			break;
		}
		case "W":{
			this.s[linha][coluna] = new Wumpus(situacao);
			break;
		}
		case "B":{
			this.s[linha][coluna] = new Buraco(situacao);
			break;
		}
		case "O":{
			this.s[linha][coluna] = new Ouro(situacao);
			break;
		}
		}
	}
	
	public void insereSala(String entrada) {
		insereSala(Integer.parseInt(entrada.substring(0, 1)),
				   Integer.parseInt(entrada.substring(2, 3)),
			 						   entrada.substring(4));
	}
	
	public void incluiFedorBrisas() {
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
	}
	
	public void incluiVazios() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0 ; j < 4; j++) {
				if(this.s[i][j] == null) {
					this.s[i][j] = new Sala("_");
				}
			}
		}
	}
	
	public void imprimeCaverna() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(s[i][j].getSituacao());
			}
			System.out.println("");
		}
	}
}
