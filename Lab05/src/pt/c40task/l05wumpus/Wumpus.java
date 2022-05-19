package pt.c40task.l05wumpus;

public class Wumpus extends Componente{
	public void solicitaFedor() {
		Fedor fedor = new Fedor();
		cav.incluiFedor(getLinha(), getColuna(), fedor);
	}
}
