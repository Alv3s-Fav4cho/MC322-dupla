package pt.c40task.l05wumpus;

public class Sala {
	Componente comp;
	private String situacao;
	private boolean visitada;
	
	public Sala(Componente comp){
		this.comp = comp;
		this.visitada = false;
	}

	public void conecta(Componente comp) {
		this.comp = comp;
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
}
