package pt.c40task.l05wumpus;

public class Montador{
	Caverna caverna;
	Componente componente;
	Heroi heroi;
	private int nHeroi = 0;
	private int nWumpus = 0;
	private int nBuracos = 0;
	private int nOuro = 0;
	private char cav[][];
	
	public void Montar(String entrada){
		switch (entrada) {
		case "P":{
			this.componente = new Heroi();
			break;
		}
		case "W":{
			this.componente = new Wumpus();
			break;
		}
		case "B":{
			this.componente = new Buraco();
			break;
		}
		case "O":{
			this.componente  = new Ouro();
			break;
		}
		}
	}
	public Montador(String entrada[]){
		for(int i = 0; i < entrada.length; i++) {
			this.Montar(entrada[i].substring(6));			
		}
	}
	
	public void conectaCaverna(Caverna cav) {
		this.caverna = cav;
	}
	
	public void conectaComponente(Componente comp) {
		this.componente = comp;
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
		System.out.println("Nome: " + heroi.getNome());
		System.out.println("Score: " + heroi.getScore());
		System.out.println("Flechas: " + heroi.getQtde_flechas());
		if(!heroi.isVivo()) {
			System.out.println("Voce perdeu :(...");
		}
		
	}
}