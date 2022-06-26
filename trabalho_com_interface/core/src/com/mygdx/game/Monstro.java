package com.mygdx.game;

import java.util.ArrayList;

public class Monstro extends Ator{

	public Monstro(String type, int linha, int coluna) {
		setType(type);
		setLinha(linha);
		setColuna(coluna);
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
