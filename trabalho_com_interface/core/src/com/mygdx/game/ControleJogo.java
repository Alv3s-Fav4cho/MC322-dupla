package com.mygdx.game;

import java.util.ArrayList;

public class ControleJogo {
	/*
	 */
	Ator prot;
	Ator pessoap;
	private char movimento;
	private char acao;
	private int turnos = 0;
	private int turnos_pessoap = 10;	
	ArrayList<String> status;
    ArrayList<String> mensagens;

	public void conectaAtor(Sala s[][], String tipo) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < s[i][j].atores.size(); k++) {
					if(tipo == "prot") {
						if(s[i][j].atores.get(k).getType() == "prot") {
							this.prot = s[i][j].atores.get(k);					
						}											
					}else if(tipo == "pessoap") {
						if(s[i][j].atores.get(k).getType() == "pessoap") {
							this.pessoap = s[i][j].atores.get(k);				
						}
					}
				}
			}
		}
	}
	
	public int getTurnos() {
		return turnos;
	}

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
			try {
				if(prot.getSanidade() > 0) {
					executaMovimento(movimento);	
				}
				
				else {
					throw new MovimentoSemSanidade();
				}
			}
			catch (MovimentoParaBorda e) {
				guardaMensagem("Movimento "+movimento+" invalido");
			}
			catch (MovimentoSemSanidade e) {
				guardaMensagem("Sanidade baixa, recupere para poder se mover");
			}
			return mensagens;					
		}
		else if(c == 'c' || c == 'e' || c == 'r' || c == 'f' || c == 'g' || c == 'z' || c == 'x' || c == 'q' || c == 't') {
			this.acao = c;
			executaAcao(acao);
			return mensagens;
		}
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

	public void atira() throws TiroSemMunicao {
		//sanidade++ para não contar a sanidade perdida ao investigar
		//mata monstro e entao chama investiga
		if(prot.getMunicao() > 0) {
			boolean haMonstro = prot.verificaMonstro(prot.getLinha(), prot.getColuna());
			boolean haPessoaPerdida = prot.verificaPessoaPerdida(prot.getLinha(), prot.getColuna());
			if(haMonstro) {
				prot.mataMonstro(prot.getLinha(), prot.getColuna());
				guardaMensagem("voce matou um monstro");
				System.out.println("voce matou um monstro");
			}
			else if(haPessoaPerdida) {
				if(!pessoap.isAchada()) {
					pessoap.setSangrando(true);
					String turnosp = String.valueOf(turnos_pessoap);
					guardaMensagem("voce feriu a pessoa perdida, que morrerá em "+turnosp+" turnos se nao for curada");
					System.out.println("voce feriu a pessoa perdida, que morrerá em "+turnos_pessoap+" turnos se nao for curada");					
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
			else if(atores.size() == 2 && pessoap.isAchada()) {
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
			prot.setMunicao(prot.getMunicao() - 1);			
		}
		else {
			throw new TiroSemMunicao();
		}
	}

	public void curaSiMesmo() throws UsarKit_SemTer {
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
			throw new UsarKit_SemTer();	
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
	
	public void recolheMunicao() throws PegarMunicao_SemMunicao {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("nao eh possivel recolher municao sem investigar a sala");
			System.out.println("nao eh possivel recolher municao sem investigar a sala");
		}
		else if(prot.pegaMunicao(prot.getLinha(),prot.getColuna())) {
			prot.setMunicao(7);
			guardaMensagem("muniçao pega com sucesso");
			System.out.println("muniçao pega com sucesso");
		}
		else {
			throw new PegarMunicao_SemMunicao();
		}
	}
	
	public void recolheKit() throws PegarKit_SemKit {
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
			throw new PegarKit_SemKit();
		}
	}
	
	public void achaPessoaPerdida() {
		if (pessoap.isAchada()) {
			guardaMensagem("Você já pegou a pessoa");
			System.out.println("Você já pegou a pessoa");
		}
		else if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("nao eh possivel capturar a pessoa perdida sem investigar a sala");
			System.out.println("nao eh possivel capturar a pessoa perdida sem investigar a sala");
		}
		else if(prot.verificaPessoaPerdida(prot.getLinha(),prot.getColuna())) {
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
				guardaMensagem("pessoa perdida não esta com voce, nao pode sair");
				System.out.println("pessoa perdida não esta com voce, nao pode sair");
			}
		}
		else {
			guardaMensagem("nao ha porta de saida nessa sala");
			System.out.println("nao ha porta de saida nessa sala");
		}
	}
	
	public void investiga() {
		
		//deve receber uma lista dos atores presentes na sala(linha, coluna) em que o protagonista se encontra
		// incluir condição de sanidade
		int sanidade_atual = prot.getSanidade();
		if(sanidade_atual > 0) {
			ArrayList<String> atores = new ArrayList<String>();
			atores = prot.tateiaSala(prot.getLinha(), prot.getColuna());
			if(atores.size() == 1 && !pessoap.isAchada()) {
				guardaMensagem("elementos da sala atual:");
				System.out.println("elementos da sala atual:");
				guardaMensagem("->Nao ha nada nesta sala.");
				System.out.println("->Nao ha nada nesta sala.");
			}
			else if(atores.size() == 2 && pessoap.isAchada()) {
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
		if(prot.getSanidade() < 10){
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
		else {
			guardaMensagem("sanidade ja esta cheia");
			System.out.println("sanidade ja esta cheia");
		}
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
		guardaStatus("Pessoa perdida morrerá em "+ sturnos_pessoap +" turnos");
		System.out.println("Pessoa perdida morrerá em "+ turnos_pessoap +" turnos");
	}

	public void executaMovimento(char movimento) throws MovimentoParaBorda {
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
		else {
			throw new MovimentoParaBorda();
		}
		
	}
	
	public void executaAcao(char acao) {
		if(acao == 'r') {
			try {
				recolheMunicao();
			}
			catch (PegarMunicao_SemMunicao e){
				guardaMensagem("nao ha municao na sala");
			}
		}
		else if(acao == 'e') {
			try {
				atira();
			}
			catch (TiroSemMunicao e){
				guardaMensagem("voce esta sem municao");
			}
		}
		else if(acao == 'f') {
			try {
				recolheKit();
			}
			catch (PegarKit_SemKit e){
				guardaMensagem("nao ha kitMedico na sala");
			}
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
			try {
				curaSiMesmo();
			}
			catch (UsarKit_SemTer e){
				guardaMensagem("nao possui kitMedico para se curar");
			}
		}
		else if(acao == 'q') {
			sairPelaPorta();
		}
		else if(acao == 't') {
			achaPessoaPerdida();
		}
	}
}