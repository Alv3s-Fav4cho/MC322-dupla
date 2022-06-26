package com.mygdx.game;

import java.util.ArrayList;

public class ControleJogo {
	Ator prot;
	Ator pessoap;
	private char movimento;
	private char acao;
	private int turnos = 0;
	private int turnos_pessoap = 10;	
	ArrayList<String> status;
    ArrayList<String> mensagens;
	//commit
	public void conectaAtor(Sala s[][], String tipo) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < s[i][j].atores.size(); k++) {
					if(tipo == "prot") {
						if(s[i][j].atores.get(k).getType() == "prot") {
							this.prot = s[i][j].atores.get(k); //cast						
						}											
					}else if(tipo == "pessoap") {
						if(s[i][j].atores.get(k).getType() == "pessoap") {
							this.pessoap = s[i][j].atores.get(k);	//cast							
						}
					}
				}
			}
		}
	}
	
	public int getTurnos() {
		return turnos;
	}

	/*
	 * public boolean comandoValido(char c) {
	 * 
	 * }
	 */
	
	public void guardaStatus(String msg) {
		int length = msg.length();
		for (int i = 0; i < length; i += 48) {
			status.add(msg.substring(i, Math.min(length, i + 48)));
		}
	}
	
	public void guardaMensagem(String msg) {
		int length = msg.length();
		for (int i = 0; i < length; i += 48) {
			mensagens.add(msg.substring(i, Math.min(length, i + 48)));
		}
	}

	public ArrayList<String> comando(char c) {
		mensagens = new ArrayList<String>();
		if(c == 'w' || c == 'a' || c == 's' || c == 'd') {
			this.movimento = c;
			if(prot.getSanidade() > 0) {
				executaMovimeto(movimento);
				return mensagens;
			}
			else {
				guardaMensagem("Sanidade baixa, recupere para poder se mover");
				System.out.println("Sanidade baixa, recupere para poder se mover");
			}
		}
		else if(c == 'c' || c == 'e' || c == 'r' || c == 'f' || c == 'g' || c == 'z' || c == 'x' || c == 'q' || c == 't') {
			this.acao = c;
			executaAcao(acao);
			return mensagens;
		}
		mensagens.add("comando nao reconhecido");
		return mensagens;
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
		//sanidade++ para n�o contar a sanidade perdida ao investigar
		//mata monstro e entao chama investiga
		if(prot.getMunicao() > 0) {
			boolean haMonstro = prot.verificaMonstro(prot.getLinha(), prot.getColuna());
			boolean haPessoaPerdida = prot.verificaPessoaPerdida(prot.getLinha(), prot.getColuna());
			//System.out.println("sua posicao no mapa:");
			//revelaSuaPosicao();
			if(haMonstro) {
				prot.mataMonstro(prot.getLinha(), prot.getColuna());
				guardaMensagem("voce matou um monstro");
				System.out.println("voce matou um monstro");
			}
			else if(haPessoaPerdida) {
				if(!pessoap.isAchada()) {
					pessoap.setSangrando(true);
					String turnosp = String.valueOf(turnos_pessoap);
					guardaMensagem("voce feriu a pessoa perdida, que morrer� em "+turnosp+" turnos se nao for curada");
					System.out.println("voce feriu a pessoa perdida, que morrer� em "+turnos_pessoap+" turnos se nao for curada");					
				}
			}
			ArrayList<String> atores = new ArrayList<String>();
			atores = prot.tateiaSala(prot.getLinha(), prot.getColuna());
			if(atores.size() == 1) {
				guardaMensagem("elementos da sala atual:");
				guardaMensagem("->Nao ha nada nesta sala.");
				System.out.println("elementos da sala atual:");
				System.out.println("->Nao ha nada nesta sala.");
			}
			else {				
				guardaMensagem("elementos da sala atual:");
				System.out.println("elementos da sala atual:");
				for(String i : atores) {
					if(i != "prot")
						if(pessoap.isAchada()) {
							if(i != "pessoap") {
								guardaMensagem("->"+ i);
								System.out.println("->"+ i);															
							}
						}
						else {
							guardaMensagem("->"+ i);
							System.out.println("->"+ i);
						}					
				}
			}
			//((Protagonista) prot).setSanidade(((Protagonista) prot).getSanidade() + 1);// repor a sanidade perdida por chamar investiga, protagonista nao deve perder sanidade ao atirar
			prot.setMunicao(prot.getMunicao() - 1);			
		}
		else{
			guardaMensagem("voce esta sem municao");
			System.out.println("voce esta sem municao");
		}
	}

	public void curaSiMesmo() {
		if(prot.verificaMonstro(prot.getLinha(), prot.getColuna())) {
			prot.setVida(prot.getVida() - 1);
		}
		if(prot.getKitMedico() > 0 && prot.getVida() < 10) {
			prot.setVida(10);
			prot.setKitMedico(prot.getKitMedico() - 1);
			guardaMensagem("curado com sucesso");
			System.out.println("curado com sucesso");
		}
		else if(prot.getVida() == 10){
			guardaMensagem("vida cheia, nao ha necessidade de se curar");
			System.out.println("vida cheia, nao ha necessidade de se curar");
		}
		else {
			guardaMensagem("nao possui kitMedico para se curar");
			System.out.println("nao possui kitMedico para se curar");			
		}
	}
	
	public void curaOutro() {
		if(pessoap.isSangrando() && prot.getKitMedico() > 0) {
			pessoap.setSangrando(false);
			prot.setKitMedico(prot.getKitMedico() - 1);
			guardaMensagem("pessoa curada com sucesso!");
			System.out.println("pessoa curada com sucesso!");
		}
		else if(prot.getKitMedico() > 0){
			guardaMensagem("voce nao possui kits para curar");
			System.out.println("voce nao possui kits para curar");
		}
		else {
			guardaMensagem("pessoa nao esta ferida.");
			System.out.println("pessoa nao esta ferida.");
		}
	}
	
	public void recolheMunicao() {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("nao eh possivel recolher municao sem investigar a sala");
			System.out.println("nao eh possivel recolher municao sem investigar a sala");
		}
		else if(prot.pegaMunicao(prot.getLinha(),prot.getColuna())) {
			prot.setMunicao(7);
			guardaMensagem("muni�ao pega com sucesso");
			System.out.println("muni�ao pega com sucesso");
		}
		else {
			guardaMensagem("nao ha municao na sala");
			System.out.println("nao ha municao na sala");
		}
	}
	
	public void recolheKit() {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("nao eh possivel recolher kitMedico sem investigar a sala");
			System.out.println("nao eh possivel recolher kitMedico sem investigar a sala");
		}
		else if(prot.pegaKit(prot.getLinha(),prot.getColuna())) {
			prot.setKitMedico(prot.getKitMedico() + 1);
			guardaMensagem("Kit pego com sucesso");
			System.out.println("Kit pego com sucesso");
		}
		else {
			guardaMensagem("nao ha kitMedico na sala");
			System.out.println("nao ha kitMedico na sala");
		}
	}
	
	public void achaPessoaPerdida() {
		if (pessoap.isAchada()) {
			guardaMensagem("Voc� j� pegou a pessoa");
			System.out.println("Voc� j� pegou a pessoa");
		}
		else if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("nao eh possivel capturar a pessoa perdida sem investigar a sala");
			System.out.println("nao eh possivel capturar a pessoa perdida sem investigar a sala");
		}
		else if(prot.verificaPessoaPerdida(prot.getLinha(),prot.getColuna())) {
			//((Protagonista) prot).setPessoaPerdida(true);
			pessoap.setAchada(true);
			guardaMensagem("pessoa pega com sucesso");
			System.out.println("pessoa pega com sucesso");
		}
		else {
			guardaMensagem("nao ha pessoa perdida nessa sala");
			System.out.println("nao ha pessoa perdida nessa sala");
		}
	}
	
	public void sairPelaPorta() {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("nao eh possivel usar a porta sem investigar a sala");
			System.out.println("nao eh possivel usar a porta sem investigar a sala");
		}
		else if(prot.verificaPorta(prot.getLinha(),prot.getColuna())) {
			if(pessoap.isAchada())
				prot.setConcluiuMeta(true);
			else {
				guardaMensagem("pessoa perdida n�o esta com voce, nao pode sair");
				System.out.println("pessoa perdida n�o esta com voce, nao pode sair");
			}
		}
		else {
			guardaMensagem("nao ha porta de saida nessa sala");
			System.out.println("nao ha porta de saida nessa sala");
		}
	}
	
	public void investiga() {
		
		//deve receber uma lista dos atores presentes na sala(linha, coluna) em que o protagonista se encontra
		// incluir condi��o de sanidade
		int sanidade_atual = prot.getSanidade();
		if(sanidade_atual > 0) {
			ArrayList<String> atores = new ArrayList<String>();
			atores = prot.tateiaSala(prot.getLinha(), prot.getColuna());
			if(atores.size() == 1) {
				guardaMensagem("elementos da sala atual:");
				System.out.println("elementos da sala atual:");
				guardaMensagem("->Nao ha nada nesta sala.");
				System.out.println("->Nao ha nada nesta sala.");
			}
			else {
				guardaMensagem("elementos da sala atual:");
				System.out.println("elementos da sala atual:");
				for(String i : atores) {
					if(i != "prot")
						if(pessoap.isAchada()) {
							if(i != "pessoap")
								guardaMensagem("->"+ i);
								System.out.println("->"+ i);							
						}
						else
							guardaMensagem("->"+ i);
							System.out.println("->"+ i);
				}
				if(atores.contains("monstro")) {
					protTomaDano();
				}
			}			
			prot.setSanidade(sanidade_atual - 1);
		}
		else {
			guardaMensagem("Sanidade insuficiente");
			System.out.println("Sanidade insuficiente");
		}	
	}
	
	private void protTomaDano() {
		prot.setVida(prot.getVida()-1);
		guardaMensagem("monstro na sala, Voce tomou dano.");
		System.out.println("monstro na sala, Voce tomou dano.");
	}
	
	private void protTomaDano(char c) {
		prot.setVida(prot.getVida()-1);
		guardaMensagem("monstro na ultima sala, saiu sem matar, Voce tomou dano.");
		System.out.println("monstro na ultima sala, saiu sem matar, Voce tomou dano.");
	}

	public void recuperaSanidade() {
		if(prot.verificaMonstro(prot.getLinha(), prot.getColuna())) {
			protTomaDano();
		}
		prot.setSanidade(10);
		if(pessoap.isSangrando()) {
			turnos_pessoap--;
		}
		this.turnos++;
		guardaMensagem("Sanidade recuperada");
		System.out.println("Sanidade recuperada");
	}
	
	public boolean continua(){
		if(prot.getVida() == 0) {
			guardaMensagem("Sua vida chegou a 0, voce perdeu!");
			System.out.println("Sua vida chegou a 0, voce perdeu!");
			return false;
		}
		else if(turnos_pessoap == 0){
			guardaMensagem("pessoa perdida morreu, voce perdeu!");
			System.out.println("pessoa perdida morreu, voce perdeu!");
			return false;
		}else if(prot.isConcluiuMeta()){
			guardaMensagem("parabens voce ganhou!!");
			System.out.println("parabens voce ganhou!!");
			return false;
		}
		return true;
	}
	
	public ArrayList<String> statsProtagonista() {// fazer ter ligacao com a interface (retornar um arraylist)
		status = new ArrayList<String>();
		String sturnos, vida, municao, sanidade, kitMedico;
		sturnos = String.valueOf(turnos);
		vida = String.valueOf(prot.getVida());
		municao = String.valueOf(prot.getMunicao());
		sanidade = String.valueOf(prot.getSanidade());
		kitMedico = String.valueOf(prot.getKitMedico());
		guardaStatus("numero de turnos:"+sturnos);
		guardaStatus("HP:"+vida+"/10");
		guardaStatus("municao:"+municao+"/7");
		guardaStatus("sanidade:"+sanidade+"/10");
		guardaStatus("kits disponiveis:"+kitMedico);
		
		System.out.println("numero de turnos:"+sturnos);
		System.out.println("HP:"+vida+"/10");
		System.out.println("municao:"+municao+"/7");
		System.out.println("sanidade:"+sanidade+"/10");
		System.out.println("kits disponiveis:"+kitMedico);
		if(pessoap.isSangrando()) {
			statsPessoap();
		}
		return status;
	}
	
	private void statsPessoap() {
		String sturnos_pessoap = String.valueOf(turnos_pessoap);
		guardaStatus("Pessoa perdida morrer� em "+ sturnos_pessoap +" turnos");
		System.out.println("Pessoa perdida morrer� em "+ turnos_pessoap +" turnos");
	}

	public void executaMovimeto(char movimento) {
		if (movimento == 'w' && prot.atorSeMove(prot.getLinha(), prot.getColuna(), prot.getLinha() - 1, prot.getColuna(), prot.getType())) {
			if(pessoap.isAchada()) {
				pessoap.atorSeMove(pessoap.getLinha(), pessoap.getColuna(), pessoap.getLinha() - 1, pessoap.getColuna(), pessoap.getType());
				pessoap.setLinha(pessoap.getLinha() - 1);
			}
			guardaMensagem("Protagonista se moveu para cima");
			System.out.println("Protagonista se moveu para cima");
			if(prot.verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			prot.alteraStatusSala();
			prot.setLinha(prot.getLinha() - 1);
			prot.setSanidade(prot.getSanidade()-1);
			if(pessoap.isSangrando()) {
				turnos_pessoap--;
			}
			this.turnos++;
		}
		else if (movimento == 's' && prot.atorSeMove(prot.getLinha(), prot.getColuna(), prot.getLinha() + 1, prot.getColuna(), prot.getType())) {
			if(pessoap.isAchada()) {
				pessoap.atorSeMove(pessoap.getLinha(), pessoap.getColuna(), pessoap.getLinha() + 1, pessoap.getColuna(), pessoap.getType());
				pessoap.setLinha(pessoap.getLinha()+1);
			}
			guardaMensagem("Protagonista se moveu para baixo");
			System.out.println("Protagonista se moveu para baixo");
			if(prot.verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			prot.alteraStatusSala();
			prot.setLinha(prot.getLinha() + 1);
			prot.setSanidade(prot.getSanidade()-1);
			if(pessoap.isSangrando()) {
				turnos_pessoap--;
			}
			this.turnos++;
		}
		else if (movimento == 'a' && prot.atorSeMove(prot.getLinha(), prot.getColuna(), prot.getLinha(), prot.getColuna() - 1, prot.getType())) {
			if(pessoap.isAchada()) {
				pessoap.atorSeMove(pessoap.getLinha(), pessoap.getColuna(), pessoap.getLinha(), pessoap.getColuna()-1, pessoap.getType());
				pessoap.setColuna(pessoap.getColuna() - 1);
			}
			guardaMensagem("Protagonista se moveu para a esquerda");
			System.out.println("Protagonista se moveu para a esquerda");
			if(prot.verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			prot.alteraStatusSala();
			prot.setColuna(prot.getColuna() - 1);
			prot.setSanidade(prot.getSanidade()-1);
			if(pessoap.isSangrando()) {
				turnos_pessoap--;
			}
			this.turnos++;
			
		}
		else if (movimento == 'd' && prot.atorSeMove(prot.getLinha(), prot.getColuna(), prot.getLinha() , prot.getColuna() + 1, prot.getType())) {
			if(pessoap.isAchada()) {
				pessoap.atorSeMove(pessoap.getLinha(), pessoap.getColuna(), pessoap.getLinha(), pessoap.getColuna() + 1,  pessoap.getType());
				pessoap.setColuna(pessoap.getColuna() + 1);
			}
			guardaMensagem("Protagonista se moveu para a direita");
			System.out.println("Protagonista se moveu para a direita");
			if(prot.verificaMonstro(prot.getLinha(), prot.getColuna())) {
				protTomaDano(movimento);
			}
			prot.alteraStatusSala();
			prot.setColuna(prot.getColuna() + 1);
			prot.setSanidade(prot.getSanidade()-1);
			if(pessoap.isSangrando()) {
				turnos_pessoap--;
			}
			this.turnos++;
		}
		else
			guardaMensagem("Movimento "+movimento+" invalido");
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
