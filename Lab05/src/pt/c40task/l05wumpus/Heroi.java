package pt.c40task.l05wumpus;

public class Heroi{
	private String nome;
	private int qtde_flechas;
	private int score;
	
	public Heroi(String nome){
		this.nome = nome;
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
	
}
