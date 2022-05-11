package pt.c40task.l05wumpus;

public class Ouro extends Sala{
	boolean capturado;

	public Ouro(String situacao) {
		super(situacao);
		capturado = false;
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
}
