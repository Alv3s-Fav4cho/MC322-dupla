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
	
	private void mensagensPause() {
		guardaMensagem("Comandos:");
		guardaMensagem("Movimentos:");
		guardaMensagem("    w: anda para sala de cima");
		guardaMensagem("    s: anda para sala de baixo");
		guardaMensagem("    d: anda para sala da direita");
		guardaMensagem("    a: anda para sala da esquerda");
		guardaMensagem("A��es:");
		guardaMensagem("    e: atira");
		guardaMensagem("    r: pega muni�ao na sala");
		guardaMensagem("    f: pega kit na sala");
		guardaMensagem("    g: cura pessoa perdida");
		guardaMensagem("    z: investiga");
		guardaMensagem("    x: recupera sanidade");
		guardaMensagem("    c: cura a si mesmo");
		guardaMensagem("    q: usa a porta para sair");
		guardaMensagem("    p: mostra lista de comandos");
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
				guardaMensagem("Movimento "+movimento+" inv�lido");
			}
			catch (MovimentoSemSanidade e) {
				guardaMensagem("Sanidade baixa, recupere para poder se mover");
			}
			return mensagens;					
		}
		else if(c == 'c' || c == 'e' || c == 'r' || c == 'f' || c == 'g' || c == 'z' || c == 'x' || c == 'q' || c == 't' || c == 'p' || c == '0') {
			this.acao = c;
			executaAcao(acao);
			return mensagens;
		}
		return mensagens;
	}
	
	public void atira() throws TiroSemMunicao {
		//sanidade++ para n�o contar a sanidade perdida ao investigar
		//mata monstro e entao chama investiga
		if(prot.getMunicao() > 0) {
			boolean haMonstro = prot.verificaMonstro(prot.getLinha(), prot.getColuna());
			boolean haPessoaPerdida = prot.verificaPessoaPerdida(prot.getLinha(), prot.getColuna());
			if(haMonstro) {
				prot.mataMonstro(prot.getLinha(), prot.getColuna());
				guardaMensagem("Voc� matou um monstro");
			}
			else if(haPessoaPerdida) {
				if(!pessoap.isAchada()) {
					pessoap.setSangrando(true);
					String turnosp = String.valueOf(turnos_pessoap);
					guardaMensagem("Voc� feriu a pessoa perdida, que morrer� em "+turnosp+" turnos se n�o for curada");				
				}
			}
			ArrayList<String> atores = new ArrayList<String>();
			atores = prot.tateiaSala(prot.getLinha(), prot.getColuna());
			if(atores.size() == 1) {
				guardaMensagem("Elementos da sala atual:");
				guardaMensagem("-> N�o h� nada nesta sala.");
			}
			else if(atores.size() == 2 && pessoap.isAchada()) {
				guardaMensagem("Elementos da sala atual:");
				guardaMensagem("-> N�o h� nada nesta sala.");
			}
			else {				
				guardaMensagem("Elementos da sala atual:");
				for(String i : atores) {
					if(i != "prot")
						if(pessoap.isAchada()) {
							if(i != "pessoap") {
								guardaMensagem("-> "+ i);															
							}
						}
						else {
							guardaMensagem("-> "+ i);
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
			guardaMensagem("Curado com sucesso");
		}
		else if(prot.getVida() == 10){
			guardaMensagem("Vida cheia, n�o h� necessidade de se curar");
		}
		else {
			throw new UsarKit_SemTer();	
		}
	}
	
	public void curaOutro() {
		if(pessoap.isSangrando() && prot.getKitMedico() > 0) {
			pessoap.setSangrando(false);
			prot.setKitMedico(prot.getKitMedico() - 1);
			guardaMensagem("Pessoa curada com sucesso!");
		}
		else if(prot.getKitMedico() > 0){
			guardaMensagem("Voc� n�o possui kits para curar");
		}
		else {
			guardaMensagem("pessoa nao esta ferida.");
		}
	}
	
	public void recolheMunicao() throws PegarMunicao_SemMunicao {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("N�o � poss�vel recolher muni��o sem investigar a sala");
		}
		else if(prot.pegaMunicao(prot.getLinha(),prot.getColuna())) {
			prot.setMunicao(7);
			guardaMensagem("Muni��o pega com sucesso");
		}
		else {
			throw new PegarMunicao_SemMunicao();
		}
	}
	
	public void recolheKit() throws PegarKit_SemKit {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("N�o � poss�vel recolher kit m�dico sem investigar a sala");
		}
		else if(prot.pegaKit(prot.getLinha(),prot.getColuna())) {
			prot.setKitMedico(prot.getKitMedico() + 1);
			guardaMensagem("Kit pego com sucesso");
		}
		else {
			throw new PegarKit_SemKit();
		}
	}
	
	public void achaPessoaPerdida() {
		if (pessoap.isAchada()) {
			guardaMensagem("Voc� j� capturou a pessoa");
		}
		else if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("N�o � poss�vel capturar a pessoa perdida sem investigar a sala");
		}
		else if(prot.verificaPessoaPerdida(prot.getLinha(),prot.getColuna())) {
			pessoap.setAchada(true);
			guardaMensagem("Pessoa pega com sucesso");
		}
		else {
			guardaMensagem("N�o h� pessoa perdida nessa sala");
		}
	}
	
	public void sairPelaPorta() {
		if(!prot.neth.s[prot.getLinha()][prot.getColuna()].isSala_investigada()) {
			guardaMensagem("N�o � poss�vel usar a porta sem investigar a sala");
		}
		else if(prot.verificaPorta(prot.getLinha(),prot.getColuna())) {
			if(pessoap.isAchada())
				prot.setConcluiuMeta(true);
			else {
				guardaMensagem("Pessoa perdida n�o est� com voc�. N�o pode sair");
			}
		}
		else {
			guardaMensagem("N�o h� porta de sapida nessa sala");
		}
	}
	
	public void investiga() {
		
		//deve receber uma lista dos atores presentes na sala(linha, coluna) em que o protagonista se encontra
		// incluir condi��o de sanidade
		int sanidade_atual = prot.getSanidade();
		if(sanidade_atual > 0) {
			ArrayList<String> atores = new ArrayList<String>();
			atores = prot.tateiaSala(prot.getLinha(), prot.getColuna());
			if(atores.size() == 1 && !pessoap.isAchada()) {
				guardaMensagem("Elementos da sala atual:");
				guardaMensagem("-> N�o h� nada nesta sala.");
			}
			else if(atores.size() == 2 && pessoap.isAchada()) {
				guardaMensagem("Elementos da sala atual:");
				guardaMensagem("-> N�o h� nada nesta sala.");
			}
			else {
				guardaMensagem("Elementos da sala atual:");
				for(String i : atores) {
					if(i != "prot")
						if(pessoap.isAchada()) {
							if(i != "pessoap")
								guardaMensagem("-> "+ i);
								System.out.println("-> "+ i);							
						}
						else
							guardaMensagem("-> "+ i);
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
		}	
	}
	
	private void protTomaDano() {
		prot.setVida(prot.getVida()-1);
		guardaMensagem("Monstro na sala, voc� tomou dano.");
	}
	
	private void protTomaDano(char c) {
		prot.setVida(prot.getVida()-1);
		guardaMensagem("Monstro na �ltima sala e saiu sem matar. Voc� tomou dano.");
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
		}
		else {
			guardaMensagem("Sanidade j� est� cheia");
		}
	}
	
	public void continua(){
		if(prot.getVida() == 0) {
			guardaMensagem("Sua vida chegou a 0. Voc� perdeu!");
			//return false;
		}
		else if(turnos_pessoap == 0){
			guardaMensagem("Pessoa perdida morreu. Voc� perdeu!");
			//return false;
		}else if(prot.isConcluiuMeta()){
			guardaMensagem("Parab�ns, voc� ganhou!!");
			//return false;
		}
		if(mensagens.size() == 0) {
			guardaMensagem(" ");
		}
		//return true;
	}
	
	public ArrayList<String> statsProtagonista() {// fazer ter ligacao com a interface (retornar um arraylist)
		status = new ArrayList<String>();
		String sturnos, vida, municao, sanidade, kitMedico;
		sturnos = String.valueOf(turnos);
		vida = String.valueOf(prot.getVida());
		municao = String.valueOf(prot.getMunicao());
		sanidade = String.valueOf(prot.getSanidade());
		kitMedico = String.valueOf(prot.getKitMedico());
		guardaStatus("N�mero de turnos: "+sturnos);
		guardaStatus("HP: "+vida+"/10");
		guardaStatus("Muni��o: "+municao+"/7");
		guardaStatus("Sanidade: "+sanidade+"/10");
		guardaStatus("Kits dispon�veis: "+kitMedico);
		
		if(pessoap.isSangrando()) {
			statsPessoap();
		}
		return status;
	}
	
	private void statsPessoap() {
		String sturnos_pessoap = String.valueOf(turnos_pessoap);
		guardaStatus("Pessoa perdida morrer� em "+ sturnos_pessoap +" turnos");
	}

	public void executaMovimento(char movimento) throws MovimentoParaBorda {
		if (movimento == 'w' && prot.atorSeMove(prot.getLinha(), prot.getColuna(), prot.getLinha() - 1, prot.getColuna(), prot.getType())) {
			if(pessoap.isAchada()) {
				pessoap.atorSeMove(pessoap.getLinha(), pessoap.getColuna(), pessoap.getLinha() - 1, pessoap.getColuna(), pessoap.getType());
				pessoap.setLinha(pessoap.getLinha() - 1);
			}
			guardaMensagem("Protagonista se moveu para cima");
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
				guardaMensagem("N�o h� muni��o na sala");
			}
		}
		else if(acao == 'e') {
			try {
				atira();
			}
			catch (TiroSemMunicao e){
				guardaMensagem("Voc� est� sem muni��o");
			}
		}
		else if(acao == 'f') {
			try {
				recolheKit();
			}
			catch (PegarKit_SemKit e){
				guardaMensagem("N�o h� kit m�dico na sala");
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
				guardaMensagem("N�o possui kit m�dico para se curar");
			}
		}
		else if(acao == 'q') {
			sairPelaPorta();
		}
		else if(acao == 't') {
			achaPessoaPerdida();
		}
		else if(acao == 'p') {
			mensagensPause();
		}
		else if(acao == '0') {
			continua();
		}
	}
}