package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

public class Sala {	
	ArrayList<Ator> atores = new ArrayList<Ator>();
	private boolean sala_investigada;
	
	public Sala(int i, int j) {
		atores = new ArrayList<Ator>();
		int x, y, z;
		Random rand = new Random();
		
		x = rand.nextInt(100)+1;
		if(x > 90){ // 10% de chance de criar um monstro junto com a sala
			Ator monstro = new Monstro();
			monstro.setLinha(i);
			monstro.setColuna(j);
			monstro.setType("monstro");
			atores.add(monstro);
		}
		
		y = rand.nextInt(100)+1;
		if(y > 0){ // 10% de chance de criar um kitMedico junto com a sala
			Ator kit = new KitMedico();
			kit.setLinha(i);
			kit.setColuna(j);
			kit.setType("kit");
			atores.add(kit);
		}
		
		z = rand.nextInt(100)+1;
		if(y > 90){ // 10% de chance de criar uma municao junto com a sala
			Ator municao = new Municao();
			municao.setLinha(i);
			municao.setColuna(j);
			municao.setType("municao");
			atores.add(municao);
		}
		this.sala_investigada = false;
	}

	public boolean isSala_investigada() {
		return sala_investigada;
	}

	public void setSala_investigada(boolean sala_investigada) {
		this.sala_investigada = sala_investigada;
	}

	public String atoresNaSala() {
		String saida = "";
		if(atores.size() !=0) {
			for(int i = 0; i < atores.size(); i++) {
				if(atores.get(i).getType() == "prot")
					saida += "p"; //p protagonista
				else if(atores.get(i).getType() == "pessoap")
					saida += "l"; //l: lost
				else if(atores.get(i).getType() == "porta")
					saida += "d"; //d: door
				else if(atores.get(i).getType() == "kit")
					saida += "k";
				else if(atores.get(i).getType() == "municao")
					saida += "a"; //a:ammo
				else if(atores.get(i).getType() == "monstro")
					saida += "m"; //m: monstro		
			}
		}
		else {
			saida = "-";
			return saida;
		}
		return saida;
	}
}
