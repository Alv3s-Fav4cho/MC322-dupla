package pt.c40task.l05wumpus;

public class Componente {
	private int linha;
	private int coluna;
	Caverna caverna;
	
	public void conecta(Caverna cav) {
		this.caverna = cav;
	}
}
