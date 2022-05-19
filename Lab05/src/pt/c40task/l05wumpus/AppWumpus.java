package pt.c40task.l05wumpus;

import java.util.Scanner;

public class AppWumpus {
	
	public static void main(String[] args) {
		AppWumpus.executaJogo((args.length > 0) ? args[0] : null, (args.length > 1) ? args[1] : null,
				(args.length > 2) ? args[2] : null);		
	}
	
	public static void executaJogo(String arquivoCaverna, String arquivoSaida, String arquivoMovimentos) {
		Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
		String entrada[];
		
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
		
		Scanner in = new Scanner(System.in);
		Montador mont = new Montador();
		Caverna cav = new Caverna();
		ControleJogo cj = new ControleJogo();
		mont.conectaCaverna(cav);		
		mont.Montar(entrada);
		
		char comando;
		String alerta = "";
		char status = 'P';
		String aviso = "";
		
		cj.conectaHeroi(cav.procura_sala(0, 0).componentes[0]);
		
		if(mont.cavernaValida(entrada)) {
			
			String movements = tk.retrieveMovements();
			
			//movements = "";
			
			if(movements == "") {
				
				System.out.print("Nome do heroi: ");
				((Heroi)cj.heroi).setNome(in.nextLine());
				System.out.println("=== Caverna Intermediaria");
				
				cav.imprimeCaverna(cav.montaSaida());
				tk.writeBoard(cav.montaSaida(), ((Heroi)cj.heroi).getScore(), status);
				comando = in.nextLine().charAt(0);
				while(comando != 'q') {
					if (comando != 'q' && cj.comandoValido(comando)) {
						cj.comando(comando);
						cav.imprimeCaverna(cav.montaSaida());
						tk.writeBoard(cav.montaSaida(), ((Heroi)cj.heroi).getScore(), status);
						System.out.println("Player: " + ((Heroi)cj.heroi).getNome());
						System.out.println("Score: " + ((Heroi)cj.heroi).getScore());
						if(cav.procura_sala(((Heroi)cj.heroi).getLinha(), ((Heroi)cj.heroi).getColuna()).componentes[4] != null)
							alerta += "Brisa ";
						if(cav.procura_sala(((Heroi)cj.heroi).getLinha(), ((Heroi)cj.heroi).getColuna()).componentes[5] != null)
							alerta += "Fedor";
						if(alerta != "") {
							System.out.println("Alerta: " + alerta);
							alerta = "";
						}
						if(cav.procura_sala(((Heroi)cj.heroi).getLinha(), ((Heroi)cj.heroi).getColuna()).componentes[3] != null)
							aviso = "Ouro encontrado!!!";
						if(aviso != "")
							System.out.println("Aviso: " + aviso );
						
						if (cj.heroi.getLinha() == 0 && cj.heroi.getColuna() == 0 && ((Heroi)cj.heroi).getOuro_capturado() || !((Heroi)cj.heroi).isVivo()) {
							if(cj.heroi.getLinha() == 0 && cj.heroi.getColuna() == 0 && ((Heroi)cj.heroi).getOuro_capturado())
								status = 'W';
							else
								status = 'L';
							comando = 'q';
						}
						else
							comando = in.nextLine().charAt(0);
					}
					else if (comando != 'q') {
						System.out.println(comando+ " nao eh um comando valido");
						comando = in.nextLine().charAt(0);
					}
				}	
				if(status == 'W') 
					System.out.println("Voce ganhou =D !!!");
				else if(status == 'L') 
					System.out.println("Voce perdeu =(...");
				else
					System.out.println("Volte sempre !");
				
				System.out.println("=== Ultima Caverna");
				cav.imprimeCaverna(cav.montaSaida());
				tk.writeBoard(cav.montaSaida(), ((Heroi)cj.heroi).getScore(), status);
				tk.stop();
				
			}
			else {
				System.out.println("=== Movimentos");
				System.out.println(movements);
				((Heroi)cj.heroi).setNome("Alcebiades");
				cav.imprimeCaverna(cav.montaSaida());
				System.out.println("Player: " + ((Heroi)cj.heroi).getNome());
				System.out.println("Score: " + ((Heroi)cj.heroi).getScore());
				tk.writeBoard(cav.montaSaida(), ((Heroi)cj.heroi).getScore(), status);
				comando = movements.charAt(0);
				int cont = 0;
				while(comando != 'q') {
					cont++;
					if (comando != 'q' && cj.comandoValido(comando)) {
						cj.comando(comando);
						cav.imprimeCaverna(cav.montaSaida());
						tk.writeBoard(cav.montaSaida(), ((Heroi)cj.heroi).getScore(), status);
						System.out.println("Player: " + ((Heroi)cj.heroi).getNome());
						System.out.println("Score: " + ((Heroi)cj.heroi).getScore());
						if(cav.procura_sala(((Heroi)cj.heroi).getLinha(), ((Heroi)cj.heroi).getColuna()).componentes[4] != null)
							alerta += "Brisa ";
						if(cav.procura_sala(((Heroi)cj.heroi).getLinha(), ((Heroi)cj.heroi).getColuna()).componentes[5] != null)
							alerta += "Fedor";
						if(alerta != "") {
							System.out.println("Alerta: " + alerta);
							alerta = "";
						}
						if(cav.procura_sala(((Heroi)cj.heroi).getLinha(), ((Heroi)cj.heroi).getColuna()).componentes[3] != null)
							aviso = "Ouro encontrado!!!";
						if(aviso != "")
							System.out.println("Aviso: " + aviso );
						if (cj.heroi.getLinha() == 0 && cj.heroi.getColuna() == 0 && ((Heroi)cj.heroi).getOuro_capturado() || !((Heroi)cj.heroi).isVivo()) {
							if(cj.heroi.getLinha() == 0 && cj.heroi.getColuna() == 0 && ((Heroi)cj.heroi).getOuro_capturado())
								status = 'W';
							else
								status = 'L';
							comando = 'q';
						}
						else {
							if(cont < movements.length())
								comando = movements.charAt(cont);
						}
					}
					else if (comando != 'q') {
						System.out.println(comando+ " nao eh um comando valido");
						cont++;
						if(cont < movements.length())
							comando = movements.charAt(cont);
					}
				}
				if(status == 'W') 
					System.out.println("Voce ganhou =D !!!");
				else if(status == 'L') 
					System.out.println("Voce perdeu =(...");
				else
					System.out.println("Volte sempre !");
				
				System.out.println("=== Ultima Caverna");
				cav.imprimeCaverna(cav.montaSaida());
				tk.writeBoard(cav.montaSaida(), ((Heroi)cj.heroi).getScore(), status);
				tk.stop();
				
			}
		}
		else
			System.out.println("Caverna invalida");
		in.close();
	}
	
}

