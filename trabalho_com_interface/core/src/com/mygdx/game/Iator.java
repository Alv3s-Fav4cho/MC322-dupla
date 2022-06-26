package com.mygdx.game;

import java.util.ArrayList;

public interface Iator {
	
	public void conectaNether(Nether neth);
	public boolean atorSeMove(int linha_atual, int coluna_atual, int nova_linha,int nova_coluna, String tipo);
	public void alteraStatusSala();
	public boolean verificaPessoaPerdida(int linha, int coluna);
	public boolean pegaKit(int linha, int coluna);
	public boolean pegaMunicao(int linha, int coluna);
	public void mataMonstro(int linha, int coluna);
	public boolean verificaMonstro(int linha, int coluna); 
	public boolean verificaPorta(int linha, int coluna);	
	public ArrayList<String> tateiaSala(int linha, int coluna);
	public int getSanidade();
	public void setSanidade(int sanidade);
	public int getVida();
	public void setVida(int vida);
	public int getMunicao();
	public void setMunicao(int municao);
	public int getKitMedico();
	public void setKitMedico(int kitMedico);
	public boolean isConcluiuMeta();
	public void setConcluiuMeta(boolean concluiuMeta);
	public boolean isAchada();
	public void setAchada(boolean achada);
	public boolean isSangrando();
	public void setSangrando(boolean sangrando)	;
}
