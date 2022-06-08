package pacote;

import java.util.ArrayList;
import java.util.Random;

public class Sala {
	 ArrayList<Ator> atores;
	
	public Sala() {
		atores = new ArrayList<Ator>();
		int x, y, z;
		Random rand = new Random();
		x = rand.nextInt(101)+1;
		if(x > 90){ // 10% de chance de criar um monstro junto com a sala
			Monstro monstro = new Monstro();
			atores.add(monstro);
		}
		y = rand.nextInt(101)+1;
		if(y > 90){ // 10% de chance de criar um kitMedico junto com a sala
			KitMedico kit = new KitMedico();
			atores.add(kit);
		}
		z = rand.nextInt(101)+1;
		if(y > 90){ // 10% de chance de criar uma municao junto com a sala
			Municao municao = new Municao();
			atores.add(municao);
		}
	}

	public void atoresNaSala() {
		
	}
}
