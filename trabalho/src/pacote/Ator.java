package pacote;

public class Ator {
	private String type;
	private int linha;
	private int coluna;
	Nether neth;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public void conectaNether(Nether neth) {
		this.neth = neth;
	}
	
	public boolean atorSeMove(int linha_atual, int coluna_atual, int nova_linha,int nova_coluna, String tipo) {
		if(neth.movimentoValido(linha_atual, coluna_atual, nova_linha, nova_coluna, tipo))
			return true;
		return false;
	}
	
	public String toString() {
		return linha +" "+coluna;
	}
}
