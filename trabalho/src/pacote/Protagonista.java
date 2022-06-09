package pacote;

public class Protagonista extends Ator{
	private String nome;
	private int vida;
	private int municao;
	private int sanidade;
	private int kitMedico;
	private boolean pessoaPerdida;
	
	public Protagonista() {
		this.nome = "";
		this.vida = 10;
		this.municao = 7;
		this.sanidade = 5;
		this.kitMedico = 0;
		this.pessoaPerdida = false;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getMunicao() {
		return municao;
	}
	public void setMunicao(int municao) {
		this.municao = municao;
	}
	public int getSanidade() {
		return sanidade;
	}
	public void setSanidade(int sanidade) {
		this.sanidade = sanidade;
	}
	public int getKitMedico() {
		return kitMedico;
	}
	public void setKitMedico(int kitMedico) {
		this.kitMedico = kitMedico;
	}
	public boolean isPessoaPerdida() {
		return pessoaPerdida;
	}
	public void setPessoaPerdida(boolean pessoaPerdida) {
		this.pessoaPerdida = pessoaPerdida;
	}
	
	
	/*
	 * public boolean protSeMove(int linha_atual, int coluna_atual, int nova_linha,
	 * int nova_coluna) {
	 * 
	 * }
	 */	 
	
	/*
	 * public boolean capturaPessoaPerdida(int linha, int coluna) {
	 * 
	 * }
	 */
	
	/*
	 * public boolean pegaKit(int linha, int coluna) {
	 * 
	 * }
	 */
	 
	/*
	 * public boolean pegaMunicao(int linha, int coluna){
	 * 
	 * }
	 */
	
}
