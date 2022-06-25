package pacote;

public class PessoaPerdida extends Ator{
	private boolean sangrando;
	private boolean achada;
	//
	public boolean isAchada() {
		return achada;
	}

	public void setAchada(boolean achada) {
		this.achada = achada;
	}

	public boolean isSangrando() {
		return sangrando;
	}

	public void setSangrando(boolean sangrando) {
		this.sangrando = sangrando;
	}
}
