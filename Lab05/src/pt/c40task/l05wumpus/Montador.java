package pt.c40task.l05wumpus;

public class Montador{
	Caverna caverna;
	Heroi h;
	Wumpus w;
	Ouro o;
	Buraco B;
	Brisa b;
	Fedor f;
	private int nHeroi = 0;
	private int nWumpus = 0;
	private int nBuracos = 0;
	private int nOuro = 0;
	private char cav[][];
	
	public void Montar(String entrada[]){
		for(int i = 0; i < entrada.length; i++) {
			this.Montar(Integer.parseInt(entrada[i].substring(0,1)),
						Integer.parseInt(entrada[i].substring(3,4)),
										  entrada[i].charAt(6));			
		}
	}
	
	public void Montar(int linha, int coluna, char entrada){
		linha--;
		coluna--;
		switch (entrada) {
		case 'P':{
			h = new Heroi();
			h.setLinha(linha);
			h.setColuna(coluna);
			caverna.insereSala(linha, coluna, h);
			break;
		}
		case 'W':{
			w = new Wumpus();
			w.setLinha(linha);
			w.setColuna(coluna);
			caverna.insereSala(linha, coluna, w);
			break;
		}
		case 'B':{
			B = new Buraco();
			B.setLinha(linha);
			B.setColuna(coluna);
			this.caverna.insereSala(linha, coluna, B);
			break;
		}
		case 'O':{
			o = new Ouro();
			o.setLinha(linha);
			o.setColuna(coluna);
			this.caverna.insereSala(linha, coluna, o);
			break;
		}
		}
	}
	
	public void Conexao(Sala s){
		s.conectaBrisa(null);
	}
	
	public void conectaCaverna(Caverna cav) {
		this.caverna = cav;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				//cav.s[i][j] = new Sala();
				caverna.s[i][j].conectaHeroi(h);
				caverna.s[i][j].conectaWumpus(w);
				caverna.s[i][j].conectaOuro(o);
				caverna.s[i][j].conectaBuraco(B);
				caverna.s[i][j].conectaBrisa(b);
				caverna.s[i][j].conectaFedor(f);
			}
		}
	}
	
	public boolean cavernaValida(String entrada[]) {
		for(int i = 0; i < entrada.length; i++) {
			if(entrada[i].substring(6).compareTo("P") == 0) {
				this.nHeroi++;
			}
			if(entrada[i].substring(6).compareTo("W") == 0) {
				this.nWumpus++;
			}
			if(entrada[i].substring(6).compareTo("B")  == 0) {
				this.nBuracos++;
			}
			if(entrada[i].substring(6).compareTo("O") == 0) {
				this.nOuro++;
			}
		}
		if(this.nHeroi == 1 && this.nOuro == 1 && this.nWumpus == 1 && this.nBuracos >=2 && this.nBuracos <=3){
			return true;
		}
		return false;
	}
	
	public void conectaHeroi(Heroi h) {
		this.h = h;
	}
	
	public void conectaWumpus(Wumpus w) {
		this.w = w;
	}
	
	public void conectaBuraco(Buraco B) {
		this.B = B;
	}
	
	public void conectaOuro(Ouro o) {
		this.o = o;
	}
	
	public void conectaBrisa(Brisa b) {
		this.b = b;
	}
	
	public void conectaFedor(Fedor f) {
		this.f = f;
	}
	
	public char[][] estadoAtual(){
		for(int i = 0; i < 4; i++ ) {
			for(int j = 0; j < 4; j++ ) {
				if(caverna.s[i][j].isVisitada()) {
					cav[i][j] = caverna.s[i][j].getSituacao().charAt(0);
				}
				else if(!caverna.s[i][j].isVisitada()) {
					cav[i][j] = '-';
				}
				else {
					cav[i][j] = 'P';
				}
			}
		}
		return cav;
	}
		
	public void imprime() {
		char estado[][] = estadoAtual();
		for(int i = 0; i < 4; i++ ) {
			for(int j = 0; j < 4; j++ ) {
				System.out.print(estado[i][j]);
			}
			System.out.println();
		}
		/*System.out.println("Nome: " + componente.getNome());
		System.out.println("Score: " + heroi.getScore());
		System.out.println("Flechas: " + heroi.getQtde_flechas());
		if(!heroi.isVivo()) {
			System.out.println("Voce perdeu :(...");
		}*/
		
	}
}