package pt.c40task.l05wumpus;

public class ControleJogo {
	Heroi heroi;
	private char movimento;
	private char acao;
	
	public void conectaHeroi(Heroi h) {
		this.heroi = h;
	}
	
	public void comando(char c) {
		if(c == 'w' || c == 'a' || c == 's' || c == 'd') {
			this.movimento = c;
			executaMovimeto(movimento);
		}
		else if(c == 'k' || c == 'c' || c == 'q') {
			this.acao = c;
			executaAcao(acao);
		}
	}

	private void executaMovimeto(char movimento) {
		if(movimento == 'w') {
			if(heroi.heroiSeMove(heroi.getLinha(), heroi.getColuna(), heroi.getLinha() - 1, heroi.getColuna())) {
				heroi.setLinha(heroi.getLinha() - 1);
				heroi.setScore(heroi.getScore() - 15);
			}
			else{
				System.out.println("Movimento "+movimento+" invalido");
			}
		}
		if(movimento == 's') {
			if(heroi.heroiSeMove(heroi.getLinha(), heroi.getColuna(), heroi.getLinha() + 1, heroi.getColuna())) {
				heroi.setLinha(heroi.getLinha() + 1);
				heroi.setScore(heroi.getScore() - 15);
			}
			else{
				System.out.println("Movimento "+movimento+" invalido");
			}
		}
		if(movimento == 'a') {
			if(heroi.heroiSeMove(heroi.getLinha(), heroi.getColuna(), heroi.getLinha(), heroi.getColuna() - 1)) {
				heroi.setColuna(heroi.getColuna() - 1);
				heroi.setScore(heroi.getScore() - 15);
			}
			else{
				System.out.println("Movimento "+movimento+" invalido");
			}
		}
		if(movimento == 'd') {
			if(heroi.heroiSeMove(heroi.getLinha(), heroi.getColuna(), heroi.getLinha() , heroi.getColuna() + 1)) {
				heroi.setColuna(heroi.getColuna() + 1);
				heroi.setScore(heroi.getScore() - 15);
			}
			else{
				System.out.println("Movimento "+movimento+" invalido");
			}
		}
		
	}
	
	private void executaAcao(char acao2) {
		// TODO Auto-generated method stub
		
	}
}
