package pt.c40task.l05wumpus;

public class Heroi extends Componente{
	private String nome;
	private int qtde_flechas;
	private int score;
	private boolean vivo;
	
	public Heroi(){
		this.nome = "";
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

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	
}
