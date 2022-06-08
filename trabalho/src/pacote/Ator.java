package pacote;

public class Ator {
	private int linha;
	private int coluna;
	// Nether neth;

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

	/*
	public void conectaNether(Nether neth) {
		this.neth = neth;
	}
	 */
	
	public String toString() {
		return linha +" "+coluna;
	}
}
