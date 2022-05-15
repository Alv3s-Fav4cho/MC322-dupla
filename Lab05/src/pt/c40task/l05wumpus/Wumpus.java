package pt.c40task.l05wumpus;

public class Wumpus extends Componente{
	private Boolean vivo;

	public Wumpus() {
		this.vivo = true;
	}
	public Boolean getVivo() {
		return vivo;
	}

	public void setVivo(Boolean vivo) {
		this.vivo = vivo;
	}
	
	public void solicitaFedor() {
		cav.incluiFedor(getLinha(), getColuna());
	}
}
