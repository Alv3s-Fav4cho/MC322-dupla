package pt.c40task.l05wumpus;

public class Montador{
	Caverna caverna;
	Componente comps[];
	private int nHeroi = 0;
	private int nWumpus = 0;
	private int nBuracos = 0;
	private int nOuro = 0;
	
	public void Montar(String entrada[]){
		for(int i = 0; i < entrada.length; i++) {
			this.Montar(Integer.parseInt(entrada[i].substring(0,1)),
						Integer.parseInt(entrada[i].substring(3,4)),
										  entrada[i].charAt(6));			
		}
	}
	
	public void Montar(int linha, int coluna, char entrada){
		int i = 0;
		Sala s;
		linha--;
		coluna--;
		s = caverna.procura_sala(linha, coluna);
		Componente comps[] = new Componente[6];
		comps = s.componentes;
		
		switch (entrada) {
		case 'P':{
			comps[i] = new Heroi();
			comps[i].conectaCaverna(caverna);
			s.setVisitada(true);
			break;
		}
		case 'W':{
			i = 1;
			s.componentes[i] = new Wumpus();
			s.componentes[i].conectaCaverna(caverna);
			break;
		}
		case 'B':{
			i = 2;
			s.componentes[i] = new Buraco();
			s.componentes[i].conectaCaverna(caverna);
			break;
		}
		case 'O':{
			i = 3;
			s.componentes[i] = new Ouro();
			s.componentes[i].conectaCaverna(caverna);
		}
		}
		comps[i].setLinha(linha);
		comps[i].setColuna(coluna);
		caverna.insereSala(linha, coluna, comps);
		
		if (i == 2) 
			((Buraco)s.componentes[i]).solicitaBrisas();
		else if (i == 1)
			((Wumpus)s.componentes[i]).solicitaFedor();
	}
	
	public void conectaCaverna(Caverna cav) {
		this.caverna = cav;
	}
	
	public void conectaSala_componentes(Caverna cav) {
		Sala s;
		this.caverna = cav;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				comps = new Componente[6];
				s = caverna.procura_sala(i, j);
				s.conectaComponente(comps);
			}
		}
	}
	
	public boolean cavernaValida(String entrada[]) {
		for(int i = 0; i < entrada.length; i++) {
			if(entrada[i].substring(6).compareTo("P") == 0)
				this.nHeroi++;
			if(entrada[i].substring(6).compareTo("W") == 0)
				this.nWumpus++;
			if(entrada[i].substring(6).compareTo("B")  == 0)
				this.nBuracos++;
			if(entrada[i].substring(6).compareTo("O") == 0)
				this.nOuro++;
		}
		if(this.nHeroi == 1 && this.nOuro == 1 && this.nWumpus == 1 && this.nBuracos >=2 && this.nBuracos <=3)
			return true;
		return false;
	}
}