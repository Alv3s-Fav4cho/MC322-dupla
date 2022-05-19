package pt.c40task.l05wumpus;

public class Sala {
	Componente componentes[];
	private boolean visitada;
	
	public void conectaComponente(Componente comps[]) {
		comps = new Componente[6];
		this.componentes = comps;
	}
	
	public boolean isVisitada() {
		return visitada;
	}
	

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}
}
