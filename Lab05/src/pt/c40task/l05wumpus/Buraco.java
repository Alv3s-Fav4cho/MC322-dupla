package pt.c40task.l05wumpus;

public class Buraco extends Componente {
	
	/*public Buraco() {
		cav.incluiBrisas(getLinha(), getColuna());
	}*/
	
	public void solicitaBrisas() {
		Componente c1[] = new Componente[6], c2[] = new Componente[6], c3[] = new Componente[6], c4[] = new Componente[6];
		if(getLinha()-1 >= 0)
			c1 = cav.s[getLinha()-1][getColuna()].componentes;
		if(getLinha()+1 < 4)
			c2 = cav.s[getLinha()+1][getColuna()].componentes;
		if(getColuna()-1 >= 0)
			c3 = cav.s[getLinha()][getColuna()-1].componentes;
		if(getColuna()+1 < 4)
			c4 = cav.s[getLinha()][getColuna()+1].componentes;
		cav.incluiBrisas(getLinha(), getColuna(), c1, c2, c3, c4);
	}
}
