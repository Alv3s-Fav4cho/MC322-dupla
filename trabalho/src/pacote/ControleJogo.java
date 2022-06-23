package pacote;

public class ControleJogo {
	Ator prot;
	private char movimento;
	private char acao;
	private int turnos = 0;

	public void conectaProtagonista(Sala s[][]) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < s[i][j].atores.size(); k++) {
					if(s[i][j].atores.get(k).getType() == "prot") {
						this.prot = (Protagonista)s[i][j].atores.get(k);						
					}					
				}
			}
		}
	}

	/*
	 * public boolean comandoValido(char c) {
	 * 
	 * }
	 */

	public void comando(char c) {
		if(c == 'w' || c == 'a' || c == 's' || c == 'd') {
			this.movimento = c;
			executaMovimeto(movimento);
		}
		else if(c == 'e' || c == 'r' || c == 'f' || c == 'g' || c == 'z' || c == 'x' || c == 'q') {
			this.acao = c;
			executaAcao(acao);
		}
	}

	public void atira() {
		
	}

	public void curaSiMesmo() {
		
	}
	
	public void curaOutro() {
		
	}
	
	public void recolheMunicao() {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			System.out.println("nao eh possivel recolher municao sem investigar a sala");
		}
		else if(((Protagonista) prot).pegaMunicao(prot.getLinha(),prot.getColuna())) {
			((Protagonista) prot).setMunicao(10);
			System.out.println("muniçao pega com sucesso");
		}
		else {
			System.out.println("nao ha municao na sala");
		}
	}
	
	public void recolheKit() {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			System.out.println("nao eh possivel recolher kitMedico sem investigar a sala");
		}
		else if(((Protagonista) prot).pegaMunicao(prot.getLinha(),prot.getColuna())) {
			((Protagonista) prot).setKitMedico(((Protagonista) prot).getKitMedico() + 1);
			System.out.println("Kit pego com sucesso");
		}
		else {
			System.out.println("nao ha kitMedico na sala");
		}
	}
	
	public void achaPessoaPerdida() {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			System.out.println("nao eh possivel capturar a pessoa perdida sem investigar a sala");
		}
		else if(((Protagonista) prot).pegaMunicao(prot.getLinha(),prot.getColuna())) {
			((Protagonista) prot).setKitMedico(((Protagonista) prot).getKitMedico() + 1);
			System.out.println("pessoa pega com sucesso");
		}
		else {
			System.out.println("nao ha pessoa perdida nessa sala");
		}
	}
	
	public void investiga() {
		
	}
	
	public void recuperaSanidade() {
		if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
			((Protagonista) prot).setVida(((Protagonista) prot).getVida() - 1);
		}
		((Protagonista) prot).setSanidade(10);
		this.turnos++;
		System.out.println("Sanidade recuperada");
	}
	
	public void sairPelaPorta() {
		
	}
	
	public void executaMovimeto(char movimento) {
		if (movimento == 'w' && ((Protagonista)prot).protSeMove(((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna(), ((Protagonista)prot).getLinha() - 1, ((Protagonista)prot).getColuna())) {
			((Protagonista)prot).setLinha(((Protagonista)prot).getLinha() - 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			this.turnos++;
			System.out.println("Protagonista se moveu para cima");
			
		}
		else if (movimento == 's' && ((Protagonista)prot).protSeMove(((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna(), ((Protagonista)prot).getLinha() + 1, ((Protagonista)prot).getColuna())) {
			((Protagonista)prot).setLinha(((Protagonista)prot).getLinha() + 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			this.turnos++;
			System.out.println("Protagonista se moveu para baixo");
		}
		else if (movimento == 'a' && ((Protagonista)prot).protSeMove(((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna(), ((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna() - 1)) {
			((Protagonista)prot).setColuna(((Protagonista)prot).getColuna() - 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			this.turnos++;
			System.out.println("Protagonista se moveu para a esquerda");
		}
		else if (movimento == 'd' && ((Protagonista)prot).protSeMove(((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna(), ((Protagonista)prot).getLinha() , ((Protagonista)prot).getColuna() + 1)) {
			((Protagonista)prot).setColuna(((Protagonista)prot).getColuna() + 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			this.turnos++;
			System.out.println("Protagonista se moveu para a direita");
		}
		else
			System.out.println("Movimento "+movimento+" invalido");
	}
	
	
	public void executaAcao(char acao) {
		if(acao == 'r') {
			recolheMunicao();
		}
		else if(acao == 'e') {
			atira();
		}
		else if(acao == 'f') {
			recolheKit();
		}
		else if(acao == 'g') {
			curaOutro();
		}
		else if(acao == 'z') {
			investiga();
		}
		else if(acao == 'x') {
			recuperaSanidade();
		}
		else if(acao == 'c') {
			curaSiMesmo();
		}
		else if(acao == 'q') {
			sairPelaPorta();
		}
	}
}
