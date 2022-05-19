package pt.c40task.l05wumpus;

public class Wumpus extends Componente{
	public void solicitaFedor() {
		cav.incluiFedor(getLinha(), getColuna());
	}
}
