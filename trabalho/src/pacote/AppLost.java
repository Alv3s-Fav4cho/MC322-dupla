package pacote;

import java.util.Scanner;

public class AppLost {

	public static void main(String[] args) {
		
		Nether neth = new Nether();
		Montador m = new Montador();
		ControleJogo cj = new ControleJogo();
		m.conectaNether(neth);
		Scanner in = new Scanner(System.in);
		char entrada;

		/*Protagonista prot = new Protagonista();
		Municao mu = new Municao();
		Sala s = new Sala(0,0);
		prot.setType("prot");
		monstro.setType("monstro");
		Monstro monstro = new Monstro();
		s.atores.add(prot);
		s.atores.add(monstro);	
		System.out.println(s.atores.contains(Kitmedico));*/
		
		m.montar();

		cj.conectaProtagonista(neth.s);
		neth.imprimeNether1();
		
		System.out.println(cj.prot.getLinha()+" "+cj.prot.getColuna());
		
		
		/*
		 * System.out.println(5 / 3); System.out.println((5 % 3));
		 */
		cj.statsProtagonista();
		entrada = in.nextLine().charAt(0);
		while(entrada !=  0) {
			cj.comando(entrada);
			cj.statsProtagonista();
			entrada = in.nextLine().charAt(0);
			//neth.imprimeNether1();
		}
		//System.out.println(((Protagonista) cj.prot).getKitMedico());

		
		

		//neth.imprimeNether2();
		
	}
}
