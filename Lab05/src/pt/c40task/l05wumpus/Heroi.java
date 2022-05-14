package pt.c40task.l05wumpus;

public class Heroi extends Componente{
	private String nome;
	private int qtde_flechas;
	private int score;
	private boolean vivo;
	Caverna cav;
	
	public Heroi(){
		this.nome = "";
		this.vivo = true;
		this.score = 0;
	}

	public int getQtde_flechas() {
		return qtde_flechas;
	}

	public void setQtde_flechas(int qtde_flechas) {
		this.qtde_flechas = qtde_flechas;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	public void conectaCaverna(Caverna cav) {
		this.cav = cav;
	}
	
	public boolean heroiSeMove(int linha_atual, int coluna_atual, int nova_linha, int nova_coluna) {
		if(cav.movimentoValido(linha_atual, coluna_atual, nova_linha, nova_coluna)) {
			return true;
		}
		return false;
	}
}
