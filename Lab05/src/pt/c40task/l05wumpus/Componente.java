package pt.c40task.l05wumpus;

public class Componente {
	private int linha;
	private int coluna;
	Caverna caverna;
	
	public void conectaCaverna(Caverna cav) {
		this.caverna = cav;
	}
	
	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public String toString() {
		return linha +" "+coluna;
	}

}
