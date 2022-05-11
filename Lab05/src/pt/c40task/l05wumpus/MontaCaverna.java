package pt.c40task.l05wumpus;

public class MontaCaverna{
	Caverna caverna;
	Heroi heroi;
	private char cav[][];
	
	public MontaCaverna(){
		cav = new char[4][4];
		for(int i = 0; i < 4; i++ ) {
			for(int j = 0; j < 4; j++ ) {
				cav[i][j] = '-';
			}
		}
	}
	
	public void conectaCaverna(Caverna cav) {
		this.caverna = cav;
	}
	
	public void conectaHeroi(Heroi h) {
		this.heroi = h;
	}
	
	public char[][] estadoAtual(){
		for(int i = 0; i < 4; i++ ) {
			for(int j = 0; j < 4; j++ ) {
				if(caverna.s[i][j].isVisitada() && !caverna.s[i][j].isHeroi_na_sala()) {
					cav[i][j] = caverna.s[i][j].getSituacao().charAt(0);
				}
				else if(!caverna.s[i][j].isVisitada() && !caverna.s[i][j].isHeroi_na_sala()) {
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