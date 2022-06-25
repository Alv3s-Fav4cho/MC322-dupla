package pacote;

import java.util.ArrayList;

public class ControleJogo {
	Ator prot;
	Ator pessoap;
	private char movimento;
	private char acao;
	private int turnos = 0;
	private int turnos_pessoap = 10;
	//commit
	public void conectaAtor(Sala s[][], String tipo) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < s[i][j].atores.size(); k++) {
					if(tipo == "prot") {
						if(s[i][j].atores.get(k).getType() == "prot") {
							this.prot = (Protagonista)s[i][j].atores.get(k);						
						}											
					}else if(tipo == "pessoap") {
						if(s[i][j].atores.get(k).getType() == "pessoap") {
							this.pessoap = (PessoaPerdida)s[i][j].atores.get(k);						
						}
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
				if(!((PessoaPerdida) pessoap).isAchada())
					((PessoaPerdida) pessoap).setSangrando(true);
					System.out.println("voce feriu a pessoa perdida, que morrerá em "+turnos_pessoap+" turnos se nao for curada");
			}
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
						if(((PessoaPerdida) pessoap).isAchada()) {
							if(i != "pessoap")
								System.out.println("->"+ i);							
						}
						else
							System.out.println("->"+ i);
				}
			}
			//((Protagonista) prot).setSanidade(((Protagonista) prot).getSanidade() + 1);// repor a sanidade perdida por chamar investiga, protagonista nao deve perder sanidade ao atirar
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
		if(((PessoaPerdida) pessoap).isSangrando() && ((Protagonista) prot).getKitMedico() > 0) {
			((PessoaPerdida) pessoap).setSangrando(false);
			((Protagonista) prot).setKitMedico(((Protagonista) prot).getKitMedico() - 1);
			System.out.println("pessoa curada com sucesso!");
		}
		else if(((Protagonista) prot).getKitMedico() > 0){
			System.out.println("voce nao possui kits para curar");
		}
		else {
			System.out.println("pessoa nao esta ferida.");
		}
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
			//((Protagonista) prot).setPessoaPerdida(true);
			((PessoaPerdida) pessoap).setAchada(true);
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
			if(((PessoaPerdida) pessoap).isAchada())
				((Protagonista) prot).setConcluiuMeta(true);
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
						if(((PessoaPerdida) pessoap).isAchada()) {
							if(i != "pessoap")
								System.out.println("->"+ i);							
						}
						else
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
		if(((PessoaPerdida) pessoap).isSangrando()) {
			turnos_pessoap--;
		}
		this.turnos++;
		System.out.println("Sanidade recuperada");
	}
	
	public boolean continua(){
		if(((Protagonista) prot).getVida() == 0) {
			System.out.println("Sua vida chegou a 0, voce perdeu!");
			return false;
		}
		else if(turnos_pessoap == 0){
			System.out.println("pessoa perdida morreu, voce perdeu!");
			return false;
		}else if(((Protagonista) prot).isConcluiuMeta()){
			System.out.println("parabens voce ganhou!!");
			return false;
		}
		return true;
	}
	
	public void statsProtagonista() {
		String nome;
		//if(((Protagonista) prot).getVida() > 0) {
		
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
		if(((PessoaPerdida) pessoap).isSangrando()) {
			statsPessoap();
		}
		
	}
	
	private void statsPessoap() {
		System.out.println("Pessoa perdida morrerá em "+ turnos_pessoap +" turnos");
	}

	public void executaMovimeto(char movimento) {
		if (movimento == 'w' && prot.atorSeMove(prot.getLinha(), prot.getColuna(), prot.getLinha() - 1, prot.getColuna(), prot.getType())) {
			if(((PessoaPerdida) pessoap).isAchada()) {
				pessoap.atorSeMove(pessoap.getLinha(), pessoap.getColuna(), pessoap.getLinha() - 1, pessoap.getColuna(), pessoap.getType());
				pessoap.setLinha(pessoap.getLinha() - 1);
			}
			System.out.println("Protagonista se moveu para cima");
			if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			((Protagonista) prot).alteraStatusSala();
			prot.setLinha(prot.getLinha() - 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			if(((PessoaPerdida) pessoap).isSangrando()) {
				turnos_pessoap--;
			}
			this.turnos++;
		}
		else if (movimento == 's' && prot.atorSeMove(prot.getLinha(), prot.getColuna(), prot.getLinha() + 1, prot.getColuna(), prot.getType())) {
			if(((PessoaPerdida) pessoap).isAchada()) {
				pessoap.atorSeMove(pessoap.getLinha(), pessoap.getColuna(), pessoap.getLinha() + 1, pessoap.getColuna(), pessoap.getType());
				pessoap.setLinha(pessoap.getLinha()+1);
			}
			System.out.println("Protagonista se moveu para baixo");
			if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			((Protagonista) prot).alteraStatusSala();
			prot.setLinha(prot.getLinha() + 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			if(((PessoaPerdida) pessoap).isSangrando()) {
				turnos_pessoap--;
			}
			this.turnos++;
		}
		else if (movimento == 'a' && prot.atorSeMove(prot.getLinha(), prot.getColuna(), prot.getLinha(), prot.getColuna() - 1, prot.getType())) {
			if(((PessoaPerdida) pessoap).isAchada()) {
				pessoap.atorSeMove(pessoap.getLinha(), pessoap.getColuna(), pessoap.getLinha(), pessoap.getColuna()-1, pessoap.getType());
				pessoap.setColuna(pessoap.getColuna() - 1);
			}
			System.out.println("Protagonista se moveu para a esquerda");
			if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			((Protagonista) prot).alteraStatusSala();
			prot.setColuna(prot.getColuna() - 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			if(((PessoaPerdida) pessoap).isSangrando()) {
				turnos_pessoap--;
			}
			this.turnos++;
			
		}
		else if (movimento == 'd' && prot.atorSeMove(prot.getLinha(), prot.getColuna(), prot.getLinha() , prot.getColuna() + 1, prot.getType())) {
			if(((PessoaPerdida) pessoap).isAchada()) {
				pessoap.atorSeMove(pessoap.getLinha(), pessoap.getColuna(), pessoap.getLinha(), pessoap.getColuna() + 1,  pessoap.getType());
				pessoap.setColuna(pessoap.getColuna() + 1);
			}
			System.out.println("Protagonista se moveu para a direita");
			if(((Protagonista) prot).verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			((Protagonista) prot).alteraStatusSala();
			prot.setColuna(prot.getColuna() + 1);
			((Protagonista)prot).setSanidade(((Protagonista) prot).getSanidade()-1);
			if(((PessoaPerdida) pessoap).isSangrando()) {
				turnos_pessoap--;
			}
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
