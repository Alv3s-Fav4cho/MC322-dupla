package pt.c40task.l05wumpus;

public class Montador{
	Caverna caverna;
	Componente comps[];
	private int nHeroi = 0;
	private int nWumpus = 0;
	private int nBuracos = 0;
	private int nOuro = 0;
	
	public void conectaCaverna(Caverna cav) {
		this.caverna = cav;
	}
	
	public void Montar(String entrada[]){
		for(int i = 0; i < entrada.length; i++) {
			this.Montar(Integer.parseInt(entrada[i].substring(0,1)),
						Integer.parseInt(entrada[i].substring(3,4)),
										  entrada[i].charAt(6));			
		}
	}
	
	public void Montar(int linha, int coluna, char entrada){
		int i = 0;
		linha--;
		coluna--;
		Componente comps[] = new Componente[6];
		//comps = caverna.procura_sala(linha, coluna).componentes;
		
		switch (entrada) {
		case 'P':{
			comps[i] = new Heroi();
			comps[i].conectaCaverna(caverna);
			comps[i].setLinha(linha);
			comps[i].setColuna(coluna);
			caverna.insereSala(linha, coluna, comps);
			caverna.procura_sala(linha, coluna).setVisitada(true);
			break;
		}
		case 'W':{
			i = 1;
			if(caverna.verifica_consistencia(linha, coluna, entrada)) {
				comps[i] = new Wumpus();
				comps[i].conectaCaverna(caverna);
				comps[i].setLinha(linha);
				comps[i].setColuna(coluna);
				caverna.insereSala(linha, coluna, comps);
				((Wumpus)caverna.procura_sala(linha, coluna).componentes[i]).solicitaFedor();
			}
			else {
				System.out.println("Erro: o Wumpus nao pode ser inserido nessa sala");
				System.exit(0);
			}
			break;
		}
		case 'B':{
			i = 2;
			if(caverna.verifica_consistencia(linha, coluna, entrada)) {
				comps[i] = new Buraco();
				comps[i].conectaCaverna(caverna);
				comps[i].setLinha(linha);
				comps[i].setColuna(coluna);
				caverna.insereSala(linha, coluna, comps);
				((Buraco)caverna.procura_sala(linha, coluna).componentes[i]).solicitaBrisas();
			}
			else {
				System.out.println("Erro: o buraco nao pode ser inserido nessa sala");
				System.exit(0);
			}
			break;
		}
		case 'O':{
			i = 3;
			if(caverna.verifica_consistencia(linha, coluna, entrada)) {
				comps[i] = new Ouro();
				comps[i].conectaCaverna(caverna);
				comps[i].setLinha(linha);
				comps[i].setColuna(coluna);
				caverna.insereSala(linha, coluna, comps);
			}
			else {
				System.out.println("Erro: o ouro nao pode ser inserido nessa sala");
				System.exit(0);
			}
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