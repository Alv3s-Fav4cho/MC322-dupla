package pt.c40task.l05wumpus;
import java.util.Random;

public class Heroi extends Componente{
	private String nome;
	private int qtde_flechas;
	private int score;
	private boolean flecha_equipada;
	private boolean ouro_capturado;
	private boolean vivo;
	
	public boolean mataWumpus() {
		int n;
		Random rand = new Random();
		n = rand.nextInt(101);
		if (n > 50)
			return true;
		return false;
	}
	
	public Heroi() {
		this.nome = "";
		qtde_flechas = 1;
		this.vivo = true;
		flecha_equipada = false;
		this.score = 0;
	}
	
	public boolean getOuro_capturado() {
		return ouro_capturado;
	}

	public void setOuro_capturado(boolean ouro_capturado) {
		this.ouro_capturado = ouro_capturado;
	}
	
	public boolean getFlecha_equipada() {
		return flecha_equipada;
	}

	public void setFlecha_equipada(boolean flecha_equipada) {
		this.flecha_equipada = flecha_equipada;
	}

	public int getQtde_flechas() {
		return qtde_flechas;
	}

	public void setQtde_flechas(int qtde_flechas) {
		this.qtde_flechas = qtde_flechas;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	
	public void conectaCaverna(Caverna cav) {
		this.cav = cav;
	}
	
	public boolean heroiSeMove(int linha_atual, int coluna_atual, int nova_linha, int nova_coluna) {
		if(cav.movimentoValido(linha_atual, coluna_atual, nova_linha, nova_coluna))
			return true;
		return false;
	}
	
	public boolean capturaOuro(int linha, int coluna) {
		if (cav.existeOuro(linha, coluna))
			return true;
		return false;
	}
	
	public boolean encontraWumpus(int linha, int coluna) {
		if (cav.existeWumpus(linha, coluna))
			return true;
		return false;
	}
	
	public boolean encontraBuraco(int linha, int coluna) {
		if (cav.existeBuraco(linha, coluna))
			return true;
		return false;
	}
}
