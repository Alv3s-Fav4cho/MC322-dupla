package pacote;

import java.util.Random;

public class Montador {
	Nether neth;
	
	public void montar() {
		int x;
		int linha = 0;
		int coluna = 0; 
		//matriz 5x5, ordem n=5
		int n = 5;
		String classe = "teste";
		Random rand = new Random();
		
		for(int i = 0; i<=2; i++ ) {
			if (i == 0) {
				classe = "Protagonista";
				x = rand.nextInt(26) + 1;
				if (x % n == 0) {
					linha = x/n - 1;
					coluna = n - 1;
				} else if(x/n == 0){
					linha = x/n;
					coluna = x%n - 1;
				}
				else{
					linha = x/n;
					coluna = x%n - 1;
				}
			} else if (i == 1) {
				classe = "PessoaPerdida";
				x = rand.nextInt(26) + 1;
				if (x % n == 0) {
					linha = x/n - 1;
					coluna = n - 1;
				} else if(x/n == 0){
					linha = x/n;
					coluna = x%n - 1;
				}
				else{
					linha = x/n;
					coluna = x%n - 1;
				}
			} else {
				classe = "Porta";
				x = rand.nextInt(26) + 1;
				if (x % n == 0) {
					linha = x/n - 1;
					coluna = n - 1;
				} else if(x/n == 0){
					linha = x/n;
					coluna = x%n - 1;
				}
				else{
					linha = x/n;
					coluna = x%n - 1;
				}
			}
			System.out.println("numero aleatorio gerado: "+ x);
			System.out.println("posicao correspondente do "+classe+" na matriz: ["+linha+"]["+coluna+"]");				
		}
	}
	
	public void conectaNether(Nether neth) {
		this.neth = neth;
	}
}
