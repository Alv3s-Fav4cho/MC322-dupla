package pt.c40task.l05wumpus;

public class Buraco extends Componente {
	
	public void solicitaBrisas() {
		Brisa brisa = new Brisa();
		cav.incluiBrisas(getLinha(), getColuna(), brisa);
	}
}
