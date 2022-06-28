package com.mygdx.game;

import java.util.ArrayList;

public interface IProtPessoap {
	public String getType();
	public void setType(String type);
	public int getLinha();
	public void setLinha(int linha);
	public int getColuna();
	public void setColuna(int coluna);
	public void conectaNether(Nether neth);
	public boolean atorSeMove(int linha_atual, int coluna_atual, int nova_linha,int nova_coluna, String tipo);
}
