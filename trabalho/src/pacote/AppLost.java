package pacote;

public class AppLost {

	public static void main(String[] args) {
		
		Nether neth = new Nether();
		Montador m = new Montador();
		ControleJogo cj = new ControleJogo();
		m.conectaNether(neth);
		/*Protagonista prot = new Protagonista();
		Sala s = new Sala(0,0);
		prot.setType("prot");
		Monstro monstro = new Monstro();
		monstro.setType("monstro");
		s.atores.add(prot);
		s.atores.add(monstro);*/		
		m.montar();
		neth.imprimeNether1();
		
		cj.conectaProtagonista(neth.s);
		System.out.println(cj.prot.getLinha()+" "+cj.prot.getColuna());
		
		/*
		 * System.out.println(5 / 3); System.out.println((5 % 3));
		 */
		neth.imprimeNether2();
		
	}
}
