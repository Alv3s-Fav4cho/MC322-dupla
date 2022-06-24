package pacote;

import java.util.ArrayList;

public class ControleJogo {
	Ator prot;
	private char movimento;
	private char acao;
	private int turnos = 0;
	private int turnos_pessoap = 10;
	//commit
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
			if(((Protagonista) prot).getSanidade() > 0) {
				executaMovimeto(movimento);			
			}
			else {
				System.out.println("Sanidade baixa, recupere para poder se mover");
			}
		}
		else if(c == 'e' || c == 'r' || c == 'f' || c == 'g' || c == 'z' || c == 'x' || c == 'q' || c == 't') {
			this.acao = c;
			executaAcao(acao);
		}
	}
	public void revelaSuaPosicao() {
		for(int i = 0; i < 5;i++) {
			for(int j = 0; j < 5;j++) {
				if(prot.getLinha() != i || prot.getColuna() != j){
					System.out.print("-  ");
				}
				else {
					System.out.print("u  ");
				}
			}
			System.out.println();
		}
	}

	public void atira() {
		//sanidade++ para não contar a sanidade perdida ao investigar
		//mata monstro e entao chama investiga
		if(((Protagonista) prot).getMunicao() > 0) {
			boolean haMonstro = ((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna());
			boolean haPessoaPerdida = ((Protagonista) prot).verificaPessoaPerdida(prot.getLinha(), prot.getColuna());
			System.out.println("sua posicao no mapa:");
			revelaSuaPosicao();
			if(haMonstro) {
				((Protagonista) prot).mataMonstro(prot.getLinha(), prot.getColuna());
				System.out.println("voce matou um monstro");
			}
			else if(haPessoaPerdida) {
				//f((Protagonista) prot).ferePessoaPerdida(prot.getLinha(), prot.getColuna());
				System.out.println("voce feriu a pessoa perdida, que morrerá em x turnos se nao for curada");
			}
			investiga();
			((Protagonista) prot).setSanidade(((Protagonista) prot).getSanidade() + 1);// repor a sanidade perdida por chamar investiga, protagonista nao deve perder sanidade ao atirar
			((Protagonista) prot).setMunicao(((Protagonista) prot).getMunicao() - 1);			
		}
		else{
			System.out.println("voce esta sem municao");
		}
	}

	public void curaSiMesmo() {
		if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
			((Protagonista) prot).setVida(((Protagonista) prot).getVida() - 1);
		}
		((Protagonista) prot).setSanidade(10);
		this.turnos++;
		System.out.println("Sanidade recuperada");
	}
	
	public void curaOutro() {
		
	}
	
	public void recolheMunicao() {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			System.out.println("nao eh possivel recolher municao sem investigar a sala");
		}
		else if(((Protagonista) prot).pegaMunicao(prot.getLinha(),prot.getColuna())) {
			((Protagonista) prot).setMunicao(7);
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
		else if(((Protagonista) prot).pegaKit(prot.getLinha(),prot.getColuna())) {
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
		else if(((Protagonista) prot).verificaPessoaPerdida(prot.getLinha(),prot.getColuna())) {
			((Protagonista) prot).setPessoaPerdida(true);
			System.out.println("pessoa pega com sucesso");
		}
		else {
			System.out.println("nao ha pessoa perdida nessa sala");
		}
	}
	
	public void sairPelaPorta() {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			System.out.println("nao eh possivel usar a porta sem investigar a sala");
		}
		else if(((Protagonista) prot).verificaPorta(prot.getLinha(),prot.getColuna())) {
			if(((Protagonista) prot).isPessoaPerdida())
				System.out.println("parabens voce ganhou!!");
			else {
				System.out.println("pessoa perdida não esta com voce, nao pode sair");
			}
		}
		else {
			System.out.println("nao ha porta de saida nessa sala");
		}
	}
	
	public void investiga() {
		
		//deve receber uma lista dos atores presentes na sala(linha, coluna) em que o protagonista se encontra
		// incluir condição de sanidade
		int sanidade_atual = ((Protagonista) prot).getSanidade();
		if(sanidade_atual > 0) {
			ArrayList<String> atores = new ArrayList<String>();
			atores = ((Protagonista) prot).tateiaSala(prot.getLinha(), prot.getColuna());
			if(atores.size() == 1) {			
				System.out.println("elementos da sala atual:");
				System.out.println("->Nao ha nada nesta sala.");
			}
			else {				
				System.out.println("elementos da sala atual:");
				for(String i : atores) {
					if(i != "prot")
						System.out.println("->"+ i);
				}
				if(atores.contains("monstro")) {
					protTomaDano();
				}
			}			
			((Protagonista) prot).setSanidade(sanidade_atual - 1);
		}
		else {
			System.out.println("Sanidade insuficiente");
		}	
	}
	
	private void protTomaDano() {
		((Protagonista) prot).setVida(((Protagonista) prot).getVida()-1);
		System.out.println("monstro na sala, Voce tomou dano.");
	}
	
	private void protTomaDano(char c) {
		((Protagonista) prot).setVida(((Protagonista) prot).getVida()-1);
		System.out.println("monstro na ultima sala, saiu sem matar, Voce tomou dano.");
	}

	public void recuperaSanidade() {
		if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
			protTomaDano();
		}
		((Protagonista) prot).setSanidade(10);
		this.turnos++;
		System.out.println("Sanidade recuperada");
	}
	
	public void statsProtagonista() {
		String nome;
		int vida, municao, sanidade, kitMedico;
		vida = ((Protagonista) prot).getVida();
		municao = ((Protagonista) prot).getMunicao();
		sanidade = ((Protagonista) prot).getSanidade();
		kitMedico = ((Protagonista) prot).getKitMedico();
		System.out.println("numero de turnos:"+turnos);
		System.out.println("HP:"+vida+"/10");
		System.out.println("municao:"+municao+"/7");
		System.out.println("sanidade:"+sanidade+"/10");
		System.out.println("kits disponiveis:"+kitMedico);
	}
	
	public void executaMovimeto(char movimento) {
		if (movimento == 'w' && ((Protagonista)prot).protSeMove(((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna(), ((Protagonista)prot).getLinha() - 1, ((Protagonista)prot).getColuna())) {
			System.out.println("Protagonista se moveu para cima");
			if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			((Protagonista) prot).alteraStatusSala();
			((Protagonista)prot).setLinha(((Protagonista)prot).getLinha() - 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			this.turnos++;
		}
		else if (movimento == 's' && ((Protagonista)prot).protSeMove(((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna(), ((Protagonista)prot).getLinha() + 1, ((Protagonista)prot).getColuna())) {
			System.out.println("Protagonista se moveu para baixo");
			if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			((Protagonista) prot).alteraStatusSala();
			((Protagonista)prot).setLinha(((Protagonista)prot).getLinha() + 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			this.turnos++;
		}
		else if (movimento == 'a' && ((Protagonista)prot).protSeMove(((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna(), ((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna() - 1)) {
			System.out.println("Protagonista se moveu para a esquerda");
			if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			((Protagonista) prot).alteraStatusSala();
			((Protagonista)prot).setColuna(((Protagonista)prot).getColuna() - 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			this.turnos++;
			
		}
		else if (movimento == 'd' && ((Protagonista)prot).protSeMove(((Protagonista)prot).getLinha(), ((Protagonista)prot).getColuna(), ((Protagonista)prot).getLinha() , ((Protagonista)prot).getColuna() + 1)) {
			System.out.println("Protagonista se moveu para a direita");
			if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			((Protagonista) prot).alteraStatusSala();
			((Protagonista)prot).setColuna(((Protagonista)prot).getColuna() + 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			this.turnos++;
		}
		else
			System.out.println("Movimento "+movimento+" invalido");
	}
	
	
	public void executaAcao(char acao) {
		if(acao == 'r') {//ok
			recolheMunicao();
		}
		else if(acao == 'e') {//ok
			atira();
		}
		else if(acao == 'f') {//ok
			recolheKit();
		}
		else if(acao == 'g') {
			curaOutro();
		}
		else if(acao == 'z') {//ok
			investiga();
		}
		else if(acao == 'x') {//ok
			recuperaSanidade();
		}
		else if(acao == 'c') {
			curaSiMesmo();
		}
		else if(acao == 'q') {//semi-ok, adicionar condicao de nao deixar sair se pessoa perdida esttiver sangrando
			sairPelaPorta();
		}
		else if(acao == 't') {//ok
			achaPessoaPerdida();
		}
	}
}
