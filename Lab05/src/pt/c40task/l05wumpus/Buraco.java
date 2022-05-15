package pt.c40task.l05wumpus;

public class Buraco extends Componente {
	public void solicitaBrisas() {
		cav.incluiBrisas(getLinha(), getColuna());
	}
}
