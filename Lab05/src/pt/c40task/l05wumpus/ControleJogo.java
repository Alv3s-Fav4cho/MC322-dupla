package pt.c40task.l05wumpus;

public class ControleJogo {
	Heroi heroi;
	private char movimento;
	private char acao;
	
	public void conectaHeroi(Heroi h) {
		this.heroi = h;
	}
	
	public boolean comandoValido(char c) {
		if(c == 'w' || c == 'a' || c == 's' || c == 'd' || c == 'k' || c == 'c') {
			return true;
		}
		return false;
	}
	
	public void comando(char c) {
		if(c == 'w' || c == 'a' || c == 's' || c == 'd') {
			this.movimento = c;
			executaMovimeto(movimento);
		}
		else if(c == 'k' || c == 'c') {
			this.acao = c;
			executaAcao(acao);
		}
	}
	
	public void flecha_wumpus() {
		if (heroi.getFlecha_equipada()) {
			heroi.setScore(heroi.getScore() - 100);
			heroi.setFlecha_equipada(false);
			if (heroi.encontraWumpus(heroi.getLinha(), heroi.getColuna())) {
				if (heroi.mataWumpus()) {
					heroi.cav.s[heroi.getLinha()][heroi.getColuna()].w = null;
					heroi.setScore(heroi.getScore() + 500);
				}
				else {
					heroi.setVivo(false);
					heroi.setScore(heroi.getScore() - 1000);
					heroi.setFlecha_equipada(false);
				}							
			}
		}
		else {
			if (heroi.encontraWumpus(heroi.getLinha(), heroi.getColuna())) {
				heroi.setVivo(false);
				heroi.setScore(heroi.getScore() - 1000);
			}
		}
	}
	
	public void caiBuraco() {
		if (heroi.encontraBuraco(heroi.getLinha(), heroi.getColuna())) {
			heroi.setVivo(false);
			heroi.setScore(heroi.getScore() - 1000);
		}
	}
	
	private void executaMovimeto(char movimento) {
		if (movimento == 'w' && heroi.heroiSeMove(heroi.getLinha(), heroi.getColuna(), heroi.getLinha() - 1, heroi.getColuna())) {
			heroi.setLinha(heroi.getLinha() - 1);
			heroi.setScore(heroi.getScore() - 15);
			flecha_wumpus();
			caiBuraco();
		}
		else if (movimento == 's' && heroi.heroiSeMove(heroi.getLinha(), heroi.getColuna(), heroi.getLinha() + 1, heroi.getColuna())) {
			heroi.setLinha(heroi.getLinha() + 1);
			heroi.setScore(heroi.getScore() - 15);
			flecha_wumpus();
			caiBuraco();
		}
		else if (movimento == 'a' && heroi.heroiSeMove(heroi.getLinha(), heroi.getColuna(), heroi.getLinha(), heroi.getColuna() - 1)) {
			heroi.setColuna(heroi.getColuna() - 1);
			heroi.setScore(heroi.getScore() - 15);
			flecha_wumpus();
			caiBuraco();
		}
		else if (movimento == 'd' && heroi.heroiSeMove(heroi.getLinha(), heroi.getColuna(), heroi.getLinha() , heroi.getColuna() + 1)) {
			heroi.setColuna(heroi.getColuna() + 1);
			heroi.setScore(heroi.getScore() - 15);
			flecha_wumpus();
			caiBuraco();
		}
		else {
			System.out.println("Movimento "+movimento+" invalido");
		}
	}
	
	private void executaAcao(char acao) {
		if (acao == 'k') {
			if (heroi.getQtde_flechas() > 0) {
				heroi.setFlecha_equipada(true);
				heroi.setQtde_flechas(0);
			}
		}
		else {
			if (heroi.capturaOuro(heroi.getLinha(), heroi.getColuna())) {
				heroi.setOuro_capturado(true);
				heroi.setScore(heroi.getScore() + 1000);
				
			}
			else {
				System.out.println("Ouro n�o est� na sala");
			}
		}
	}
}
