package pt.c40task.l05wumpus;

public class Sala {
	Componente componentes[] = new Componente[6];
	/*Heroi h;
	Wumpus w;
	Ouro o;
	Buraco B;
	Brisa b;
	Fedor f;*/
	
	//private String situacao;
	private boolean visitada;
	
	/*public Sala() {
		this.h = null;
		this.w = null;
	}*/
	
	/*public void sala(char comp){
		switch (comp) {
		case 'P':{
			this.h = new Heroi();
				break;
		}
		case 'W':{
			this.w = new Wumpus();
			break;
		}
		case 'B':{
			this.B = new Buraco();
			break;
		}
		case 'O':{
			this.o = new Ouro();
			break;
		}
		}
		this.visitada = false;
	}*/
	
	public void conectaComponente(Componente comps[]) {
		this.componentes = comps;
	}

//	public void conectaHeroi(Heroi h) {
//		this.h = h;
//	}
//	
//	public void conectaWumpus(Wumpus w) {
//		this.w = w;
//	}
//	
//	public void conectaBuraco(Buraco B) {
//		this.B = B;
//	}
//	
//	public void conectaOuro(Ouro o) {
//		this.o = o;
//	}
//	
//	public void conectaBrisa(Brisa b) {
//		this.b = b;
//	}
//	
//	public void conectaFedor(Fedor f) {
//		this.f = f;
//	}
	
	public boolean isVisitada() {
		return visitada;
	}
	

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}
	
	public void componentesNaSala() {
		String comp = "";
		if(componentes[0] != null) {
			comp += "H";
		}
		if(componentes[1] != null) {
			comp += "W";
		}
		if(componentes[3] != null) {
			comp += "O";
		}
		if(componentes[2] != null) {
			comp += "B";
		}
		if(componentes[4] != null) {
			comp += "b";
		}
		if(componentes[5] != null) {
			comp += "f";
		}
		if(comp == "") {
			comp += "-";
		}
		comp += " ";
		System.out.print(comp);
	}
}
