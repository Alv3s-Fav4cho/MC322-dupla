package com.mygdx.game;

import java.util.ArrayList;

public abstract class Ator implements IProtPessoap{
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
	
	//metodos especificos do protagonista
	public int getVida() {
		return 0;
	}
	public void setVida(int vida) {
		
	}
	public int getMunicao() {
		return 0;
	}
	public void setMunicao(int municao) {

	}
	public int getSanidade() {
		return 0;
	}
	public void setSanidade(int sanidade) {

	}
	public int getKitMedico() {
		return 0;

	}
	public void setKitMedico(int kitMedico) {

	}
	
	public boolean isConcluiuMeta() {
		return false;
	}

	public void setConcluiuMeta(boolean concluiuMeta) {

	}

	public void alteraStatusSala() {

	}
	
	public boolean verificaPessoaPerdida(int linha, int coluna) {
		return false;
	}
	
	public boolean pegaKit(int linha, int coluna) {
		return false;
	}
	 	
	public boolean pegaMunicao(int linha, int coluna){
		return false;	
	}
	
	public void mataMonstro(int linha, int coluna) {

	}
	
	public boolean verificaMonstro(int linha, int coluna) {
		return false;
	}
	 
	public boolean verificaPorta(int linha, int coluna) {
		return false;
	}
	
	public ArrayList<String> tateiaSala(int linha, int coluna){
		return null;
	}
	
	//metodos especificos da PessoaPerdida
	public boolean isAchada() {
		return false;
	}

	public void setAchada(boolean achada) {

	}

	public boolean isSangrando() {
		return false;
	}

	public void setSangrando(boolean sangrando) {
		
	}
}