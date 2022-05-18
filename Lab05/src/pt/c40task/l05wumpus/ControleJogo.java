package pt.c40task.l05wumpus;

public class ControleJogo {
	Componente heroi;
	private char movimento;
	private char acao;
	
	public void conectaHeroi(Componente h) {
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
		if (((Heroi)heroi).getFlecha_equipada()) {
			((Heroi)heroi).setScore(((Heroi)heroi).getScore() - 100);
			((Heroi)heroi).setFlecha_equipada(false);
			if (((Heroi)heroi).encontraWumpus(((Heroi)heroi).getLinha(), ((Heroi)heroi).getColuna())) {
				if (((Heroi)heroi).mataWumpus()) {
					((Heroi)heroi).cav.s[((Heroi)heroi).getLinha()][((Heroi)heroi).getColuna()].componentes[1] = null;
					((Heroi)heroi).setScore(((Heroi)heroi).getScore() + 500);
				}
				else {
					((Heroi)heroi).setVivo(false);
					((Heroi)heroi).setScore(((Heroi)heroi).getScore() - 1000);
					((Heroi)heroi).setFlecha_equipada(false);
				}							
			}
		}
		else {
			if (((Heroi)heroi).encontraWumpus(((Heroi)heroi).getLinha(), ((Heroi)heroi).getColuna())) {
				((Heroi)heroi).setVivo(false);
				((Heroi)heroi).setScore(((Heroi)heroi).getScore() - 1000);
			}
		}
	}
	
	public void caiBuraco() {
		if (((Heroi)heroi).encontraBuraco(((Heroi)heroi).getLinha(), ((Heroi)heroi).getColuna())) {
			((Heroi)heroi).setVivo(false);
			((Heroi)heroi).setScore(((Heroi)heroi).getScore() - 1000);
		}
	}
	
	private void executaMovimeto(char movimento) {
		if (movimento == 'w' && ((Heroi)heroi).heroiSeMove(((Heroi)heroi).getLinha(), ((Heroi)heroi).getColuna(), ((Heroi)heroi).getLinha() - 1, ((Heroi)heroi).getColuna())) {
			((Heroi)heroi).setLinha(((Heroi)heroi).getLinha() - 1);
			((Heroi)heroi).setScore(((Heroi)heroi).getScore() - 15);
			flecha_wumpus();
			caiBuraco();
		}
		else if (movimento == 's' && ((Heroi)heroi).heroiSeMove(((Heroi)heroi).getLinha(), ((Heroi)heroi).getColuna(), ((Heroi)heroi).getLinha() + 1, ((Heroi)heroi).getColuna())) {
			((Heroi)heroi).setLinha(((Heroi)heroi).getLinha() + 1);
			((Heroi)heroi).setScore(((Heroi)heroi).getScore() - 15);
			flecha_wumpus();
			caiBuraco();
		}
		else if (movimento == 'a' && ((Heroi)heroi).heroiSeMove(((Heroi)heroi).getLinha(), ((Heroi)heroi).getColuna(), ((Heroi)heroi).getLinha(), ((Heroi)heroi).getColuna() - 1)) {
			((Heroi)heroi).setColuna(((Heroi)heroi).getColuna() - 1);
			((Heroi)heroi).setScore(((Heroi)heroi).getScore() - 15);
			flecha_wumpus();
			caiBuraco();
		}
		else if (movimento == 'd' && ((Heroi)heroi).heroiSeMove(((Heroi)heroi).getLinha(), ((Heroi)heroi).getColuna(), ((Heroi)heroi).getLinha() , ((Heroi)heroi).getColuna() + 1)) {
			((Heroi)heroi).setColuna(((Heroi)heroi).getColuna() + 1);
			((Heroi)heroi).setScore(((Heroi)heroi).getScore() - 15);
			flecha_wumpus();
			caiBuraco();
		}
		else {
			System.out.println("Movimento "+movimento+" invalido");
		}
	}
	
	private void executaAcao(char acao) {
		if (acao == 'k') {
			if (((Heroi)heroi).getQtde_flechas() > 0) {
				((Heroi)heroi).setFlecha_equipada(true);
				((Heroi)heroi).setQtde_flechas(0);
			}
		}
		else {
			if (((Heroi)heroi).capturaOuro(((Heroi)heroi).getLinha(), ((Heroi)heroi).getColuna())) {
				((Heroi)heroi).setOuro_capturado(true);
				((Heroi)heroi).setScore(((Heroi)heroi).getScore() + 1000);
				
			}
			else {
				System.out.println("Ouro nao estao na sala");
			}
		}
	}
}
