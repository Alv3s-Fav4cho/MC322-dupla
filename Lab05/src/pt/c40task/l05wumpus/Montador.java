package pt.c40task.l05wumpus;

public class Montador{
	Caverna caverna;
	Componente comps[] = new Componente[6];
	/*Heroi h;
	Wumpus w;
	Ouro o;
	Buraco B;
	Brisa b;
	Fedor f;*/
	private int nHeroi = 0;
	private int nWumpus = 0;
	private int nBuracos = 0;
	private int nOuro = 0;
	
	public void Montar(String entrada[]){
		for(int i = 0; i < entrada.length; i++) {
			this.Montar(Integer.parseInt(entrada[i].substring(0,1)),
						Integer.parseInt(entrada[i].substring(3,4)),
										  entrada[i].charAt(6));			
		}
	}
	
	public void Montar(int linha, int coluna, char entrada){
		int i = 0;
		linha--;
		coluna--;
		comps = caverna.s[linha][coluna].componentes;
		
		switch (entrada) {
		case 'P':{
			comps[i] = new Heroi();
			comps[i].conectaCaverna(caverna);
			caverna.s[linha][coluna].setVisitada(true);
//			h = new Heroi();
//			h.setLinha(linha);
//			h.setColuna(coluna);
//			h.conectaCaverna(caverna);
//			caverna.insereSala(linha, coluna, h);
			break;
		}
		case 'W':{
			i = 1;
			comps[i] = new Wumpus();
			comps[i].conectaCaverna(caverna);
			//((Wumpus)comps[i]).solicitaFedor();
//			w = new Wumpus();
//			w.setLinha(linha);
//			w.setColuna(coluna);
//			w.conectaCaverna(caverna);
//			w.solicitaFedor();
//			caverna.insereSala(linha, coluna, w);
			break;
		}
		case 'B':{
			i = 2;
			comps[i] = new Buraco();
			comps[i].conectaCaverna(caverna);
			//((Buraco)comps[i]).solicitaBrisas();
//			B = new Buraco();
//			B.setLinha(linha);
//			B.setColuna(coluna);
//			B.conectaCaverna(caverna);
//			B.solicitaBrisas();
//			this.caverna.insereSala(linha, coluna, B);
			break;
		}
		case 'O':{
			i = 3;
			comps[i] = new Ouro();
			comps[i].conectaCaverna(caverna);
			
//			o = new Ouro();
//			o.setLinha(linha);
//			o.setColuna(coluna);
//			o.conectaCaverna(caverna);
//			this.caverna.insereSala(linha, coluna, o);
//			break;
		}
		}
		comps[i].setLinha(linha);
		comps[i].setColuna(coluna);
		caverna.insereSala(linha, coluna, comps);
		
		if (i == 2) {
			((Buraco)comps[i]).solicitaBrisas();
		}
			
		else if (i == 1)
			((Wumpus)comps[i]).solicitaFedor();
		
		caverna.imprimeCaverna();
		System.out.println();
	}
	
	/*public void Conexao(Sala s){
		s.conectaBrisa(null);
	}*/
	
	public void conectaSala_componentes(Caverna cav) {
		this.caverna = cav;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				caverna.s[i][j].conectaComponente(comps);
//				caverna.s[i][j].conectaHeroi(h);
//				caverna.s[i][j].conectaWumpus(w);
//				caverna.s[i][j].conectaOuro(o);
//				caverna.s[i][j].conectaBuraco(B);
//				caverna.s[i][j].conectaBrisa(b);
//				caverna.s[i][j].conectaFedor(f);
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
	
	/*public void conectaHeroi(Heroi h) {
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
	}*/
	
	/*public char[][] estadoAtual(){
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
	}*/
		
	/*public void imprime() {
		char estado[][] = estadoAtual();
		for(int i = 0; i < 4; i++ ) {
			for(int j = 0; j < 4; j++ ) {
				System.out.print(estado[i][j]);
			}
			System.out.println();
		}*/
		/*System.out.println("Nome: " + componente.getNome());
		System.out.println("Score: " + heroi.getScore());
		System.out.println("Flechas: " + heroi.getQtde_flechas());
		if(!heroi.isVivo()) {
			System.out.println("Voce perdeu :(...");
		}
		
	}*/
}