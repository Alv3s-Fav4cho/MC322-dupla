package pacote;

public class AppLost {

	public static void main(String[] args) {
		
		Nether neth = new Nether();
		Montador m = new Montador();
		m.conectaNether(neth);
		
		
		System.out.println("Hello world!");
		
		/*
		 * System.out.println(5 / 3); System.out.println((5 % 3));
		 */
		 
		m.montar();
		
	}
}
