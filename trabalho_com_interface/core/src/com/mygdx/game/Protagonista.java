package com.mygdx.game;

import java.util.ArrayList;

public class Protagonista extends Ator{
	private int vida;
	private int municao;
	private int sanidade;
	private int kitMedico;
	private boolean concluiuMeta;

	/*public Protagonista() {
		this.nome = "";
		this.vida = 10;
		this.municao = 7;
		this.sanidade = 10;
		this.kitMedico = 0;
		this.pessoaPerdida = false;
	}*/

	public Protagonista(String type, int linha, int coluna, Nether neth) {
		setType(type);
		setLinha(linha);
		setColuna(coluna);
		conectaNether(neth);
		this.vida = 10;
		this.municao = 7;
		this.sanidade = 10;
		this.kitMedico = 0;
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
	
	public boolean isConcluiuMeta() {
		return concluiuMeta;
	}

	public void setConcluiuMeta(boolean concluiuMeta) {
		this.concluiuMeta = concluiuMeta;
	}
	
	/*public boolean atorSeMove(int linha_atual, int coluna_atual, int nova_linha,int nova_coluna) { 
		if(neth.movimentoValido(linha_atual,coluna_atual, nova_linha, nova_coluna))
			return true;
		return false; 
	}*/
	
	
	
	public void alteraStatusSala() {
		neth.s[getLinha()][getColuna()].setSala_investigada(false);
	}
	
	public boolean verificaPessoaPerdida(int linha, int coluna) {
		int valor = neth.existePessoaPerdida(linha, coluna);
		if(valor >= 0) {
			//neth.s[linha][coluna].atores.remove(valor);
			return true;
		}
		return false;
	}
	
	public boolean pegaKit(int linha, int coluna) {
		int valor = neth.existeKit(linha, coluna);
		if(valor >= 0) {
			neth.s[linha][coluna].atores.remove(valor);
			return true;
		}
		return false;
	}
	 	
	public boolean pegaMunicao(int linha, int coluna){
		int valor = neth.existeMunicao(linha, coluna);
		if(valor >= 0) {
			neth.s[linha][coluna].atores.remove(valor);
			return true;
		}
		return false;	
	}
	
	public void mataMonstro(int linha, int coluna) {
		int valor = neth.existeMonstro(linha, coluna);
		if(valor >= 0) {
			neth.s[linha][coluna].atores.remove(valor);
		}
	}
	
	public boolean verificaMonstro(int linha, int coluna) {
		int valor = neth.existeMonstro(linha, coluna);
		if(valor >= 0) {
			//neth.s[linha][coluna].atores.remove(valor);
			return true;
		}
		return false;
	}
	 
	public boolean verificaPorta(int linha, int coluna) {
		int valor = neth.existePorta(linha, coluna);
		if(valor >= 0) {
			//neth.s[linha][coluna].atores.remove(valor);
			return true;
		}
		return false;
	}
	
	public ArrayList<String> tateiaSala(int linha, int coluna){
		ArrayList<String> atores = new ArrayList<String>();
		atores = neth.atores_na_sala(linha, coluna);
		return atores;
	}

	@Override
	public boolean isAchada() {
		//do nothing
		return false;
	}

	@Override
	public void setAchada(boolean achada) {
		//do nothing
	}

	@Override
	public boolean isSangrando() {
		//do nothing
		return false;
	}

	@Override
	public void setSangrando(boolean sangrando) {
		//do nothing
	}
	
}