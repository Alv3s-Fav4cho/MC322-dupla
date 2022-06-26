package com.mygdx.game;

import java.util.ArrayList;

public class PessoaPerdida extends Ator{
	private boolean sangrando;
	private boolean achada;
	
	public PessoaPerdida(String type, int linha, int coluna, Nether neth) {
		setType(type);
		setLinha(linha);
		setColuna(coluna);
		conectaNether(neth);
	}

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

	@Override
	public void alteraStatusSala() {
		//do nothing
	}

	@Override
	public boolean verificaPessoaPerdida(int linha, int coluna) {
		//do nothing
		return false;
	}

	@Override
	public boolean pegaKit(int linha, int coluna) {
		//do nothing
		return false;
	}

	@Override
	public boolean pegaMunicao(int linha, int coluna) {
		//do nothing
		return false;
	}

	@Override
	public void mataMonstro(int linha, int coluna) {
		//do nothing
	}

	@Override
	public boolean verificaMonstro(int linha, int coluna) {
		//do nothing
		return false;
	}

	@Override
	public boolean verificaPorta(int linha, int coluna) {
		//do nothing
		return false;
	}

	@Override
	public ArrayList<String> tateiaSala(int linha, int coluna) {
		//do nothing
		return null;
	}
	
	@Override
	public int getSanidade() {
		//do nothing
		return 0;
	}

	@Override
	public void setSanidade(int sanidade) {
		//do nothing
	}

	@Override
	public int getVida() {
		//do nothing
		return 0;
	}

	@Override
	public void setVida(int vida) {
		//do nothing
	}

	@Override
	public int getMunicao() {
		//do nothing
		return 0;
	}

	@Override
	public void setMunicao(int municao) {
		//do nothing
	}

	@Override
	public int getKitMedico() {
		//do nothing
		return 0;
	}

	@Override
	public void setKitMedico(int kitMedico) {
		//do nothing
	}

	@Override
	public boolean isConcluiuMeta() {
		//do nothing
		return false;
	}

	@Override
	public void setConcluiuMeta(boolean concluiuMeta) {
		//do nothing
	}
}
