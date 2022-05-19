package pt.c40task.l05wumpus;

public class Sala {
	Componente componentes[];
	private boolean visitada;
	
	public Sala() {
		componentes = new Componente[6];
	}
	
	public boolean isVisitada() {
		return visitada;
	}
	

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}
	
	public boolean consistencia(char c) {
		if (c == 'W' && componentes[2] == null && componentes[3] == null)
			return true;
		if (c == 'B' && componentes[1] == null && componentes[3] == null)
			return true;
		if (c == 'O' && componentes[1] == null && componentes[2] == null)
			return true;
		return false;
	}
}
