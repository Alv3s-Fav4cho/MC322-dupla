package pt.c40task.l05wumpus;

public class Wumpus extends Sala{
	Boolean vivo;
	
	public Wumpus(String situacao) {
		super(situacao);
		this.vivo = true;
	}
}
