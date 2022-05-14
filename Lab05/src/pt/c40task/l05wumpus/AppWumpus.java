package pt.c40task.l05wumpus;

import java.util.Scanner;

public class AppWumpus {

	public static void main(String[] args) {
		AppWumpus.executaJogo((args.length > 0) ? args[0] : null, (args.length > 1) ? args[1] : null,
				(args.length > 2) ? args[2] : null);

		/*
		 * String lista[] = {"1, 1, P","1, 4, B","3, 1, B","4, 4, B",
		 * "2, 3, W","3, 3, O"}; Caverna mc = new Caverna();
		 * 
		 * for(int i = 0; i < lista.length; i++) { mc.insereSala(lista[i]); }
		 * mc.incluiFedorBrisas(); mc.incluiVazios(); mc.imprimeCaverna();
		 */

	}

	public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      String entrada[];
      Scanner in = new Scanner(System.in);
      char comando;
      
      String cave[][] = tk.retrieveCave();
      System.out.println("=== Caverna");
      entrada = new String[cave.length];
      for (int l = 0; l < cave.length; l++) {
    	 entrada[l] = "";
         for (int c = 0; c < cave[l].length; c++) {
            System.out.print(cave[l][c] + ((c < cave[l].length-1) ? ", " : ""));
         	entrada[l] += cave[l][c] + ((c < cave[l].length-1) ? ", " : "");
         }
         System.out.println();
      }
        
      Montador mont = new Montador();
      Caverna cav = new Caverna();
      ControleJogo cj = new ControleJogo();
      mont.conectaCaverna(cav);
      //Sala sala = new Sala();
      /*Heroi h = null;
      Wumpus w = null;
      Ouro o = null;
      Buraco B = null;
      Brisa b = null;
      Fedor f = null;*/
      
      /*for(int i = 0; i < 4; i++) {
    	  for(int j = 0; j < 4; j++) {
    		  //cav.s[i][j] = new Sala();
    		  cav.s[i][j].conectaHeroi(mont.h);
    		  cav.s[i][j].conectaWumpus(mont.w);
    		  cav.s[i][j].conectaOuro(mont.o);
    		  cav.s[i][j].conectaBuraco(mont.B);
    		  cav.s[i][j].conectaBrisa(mont.b);
    		  cav.s[i][j].conectaFedor(mont.f);
    	  }
      }*/
      
      //mont.conectaComponente(comp);
      
      //comp.conectaCaverna(cav);
      
      mont.Montar(entrada);
      cj.conectaHeroi(cav.s[0][0].h);
      cav.s[0][0].h.conectaCaverna(cav);
      cav.incluiFedorBrisas();
      
      cav.s[0][0].h.setNome("leo");
      System.out.println(cav.s[0][0].h.getNome());
      
      cav.imprimeCaverna(cav.montaSaida());
      comando = in.nextLine().charAt(0);
      while(comando != 'q') {
    	  cj.comando(comando);
    	  cav.imprimeCaverna(cav.montaSaida());    
    	  System.out.println(cj.heroi.getLinha() +" "+cj.heroi.getColuna()); 
    	  comando = in.nextLine().charAt(0);
      }
      
      /*for(int i = 0; i < 4; i++) {
    	  for(int j = 0; j < 4; j++) {
    		  if (cav.s[i][j].h != null) {
    			  System.out.print("h ");
    		  }
    		  else if (cav.s[i][j].b != null) {
    			  System.out.print("b ");
    		  }
    		  else if (cav.s[i][j].f != null) {
    			  System.out.print("f ");
    		  }
    		  else if (cav.s[i][j].o != null) {
    			  System.out.print("o ");
    		  }
    		  else if (cav.s[i][j].B != null) {
    			  System.out.print("B ");
    		  }
    		  else if (cav.s[i][j].w != null){
    			  System.out.print("W ");
    		  }
    		  else {
    			  System.out.print("- ");
    		  }
    	  }
    	  System.out.println();
      }*/
      /*if(mont.cavernaValida(entrada)) {
    	  Scanner in = new Scanner(System.in);
    	  
    	  /*cav.insereSala();
    	  cav.incluiFedorBrisas();
    	  cav.incluiVazios();*/
    	  //cav.imprimeCaverna();
    	  
    	  /*Montador mc = new Montador();
    	  Heroi h = new Heroi("jorge");
    	  mc.conectaHeroi(h);
    	  mc.conectaCaverna(cav);
    	  mc.imprime();
      
    	  String movements = tk.retrieveMovements();
    	  System.out.println("=== Movimentos");
    	  System.out.println(movements);
    	  
    	  System.out.println("=== Caverna Intermediaria");
    	  char partialCave[][] = {
    			  {'#', '#', 'b', '-'},
    			  {'#', 'b', '-', '-'},
    			  {'b', '-', '-', '-'},
    			  {'p', '-', '-', '-'}
    	  };
    	  int score = -120;
    	  char status = 'x'; // 'w' para venceu; 'n' para perdeu; 'x' intermediárias
    	  tk.writeBoard(partialCave, score, status);
    	  
    	  System.out.println("=== Última Caverna");
    	  char finalCave[][] = {
    			  {'#', '#', 'b', '-'},
    			  {'#', 'b', '#', 'f'},
    			  {'b', '-', '-', 'w'},
    			  {'#', '-', '-', '-'}
    	  };
    	  score = -1210;
    	  status = 'n'; // 'w' para venceu; 'n' para perdeu; 'x' intermediárias
    	  tk.writeBoard(finalCave, score, status);
    	  
    	  tk.stop();
    	  
      }
      else {
    	  System.out.println("Caverna invalida");
      }*/
   }

}
