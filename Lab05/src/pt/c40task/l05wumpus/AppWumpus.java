package pt.c40task.l05wumpus;

public class AppWumpus {

   public static void main(String[] args) {
	   AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
	   
	   /*String lista[] = {"1, 1, P","1, 4, B","3, 1, B","4, 4, B", "2, 3, W","3, 3, O"};
	   Caverna mc = new Caverna();
	   
	   for(int i = 0; i < lista.length; i++) {
		   mc.insereSala(lista[i]);
	   }
	   mc.incluiFedorBrisas();
	   mc.incluiVazios();
	   mc.imprimeCaverna();*/

   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      String entrada[];
      Caverna cav = new Caverna();
      
      
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
      
      if(cav.cavernaValida(entrada)) {
    	  cav.insereSala(entrada);
    	  cav.incluiFedorBrisas();
    	  cav.incluiVazios();
    	  cav.imprimeCaverna();
    	  
    	  MontaCaverna mc = new MontaCaverna();
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
      }
   }

}
