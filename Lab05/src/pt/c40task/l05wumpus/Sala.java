package pt.c40task.l05wumpus;

public class Sala {

	private String situacao;
	private boolean visitada;
	private boolean heroi_na_sala;
	
	public Sala(String situacao){
		this.situacao = situacao;
		this.visitada = false;
		this.heroi_na_sala = false;
	}
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public boolean isVisitada() {
		return visitada;
	}

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}

	public boolean isHeroi_na_sala() {
		return heroi_na_sala;
	}

	public void setHeroi_na_sala(boolean heroi_na_sala) {
		this.heroi_na_sala = heroi_na_sala;
	}
}
