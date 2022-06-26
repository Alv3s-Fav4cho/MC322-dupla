package com.mygdx.game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MainUI extends ApplicationAdapter {

	private SpriteBatch batch;
	private OrthographicCamera camera;

	//variaveis criadas para testes do trabalho
	private Rectangle _00;
	private Texture celblack;
	private Texture celwhite;
	private int tamanho_celula = 90;
	private int espacamento_celulas = 6;
	private ShapeRenderer shape;
	private String nome = "Jonathan";
	private int municao = 5;
	private int sanidade = 10;  
	private int turnos = 0;
	private int linha = 0;
	private int coluna = 0;
	private char comando;

	private BitmapFont font;
	private ArrayList<String> statsProt;
	private ArrayList<String> msgs;
	private boolean check=true;
	
	private Nether neth;
	private Montador m;
	private ControleJogo cj;
	
	
	@Override
	public void create() {
		//teste do trabalho
		celblack = new Texture(Gdx.files.internal("blackcel.png"));
		celwhite = new Texture(Gdx.files.internal("whitecel.png"));
		
		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		neth = new Nether();
		m = new Montador();
		cj = new ControleJogo();
		
		m.conectaNether(neth);
		m.montar();
		cj.conectaAtor(neth.s, "prot");
		cj.conectaAtor(neth.s, "pessoap");
		
		msgs = new ArrayList<String>();
		msgs.add("");
		statsProt = new ArrayList<String>();
		statusInicial();
		statsProt.add("");
		
		/*msgs = new Array<>();
	      msgs.add("Nome: "+nome);
	      msgs.add("municao: "+municao+"/5");
	      msgs.add("sanidade: "+sanidade+"/10");*/
		
		
		//teste do trabalho
		_00 = new Rectangle();
		_00.x = 325;
		_00.y = 100 + 3*(tamanho_celula+espacamento_celulas); 
		_00.width = tamanho_celula;
		_00.height = tamanho_celula;
	}
	
	public void statusInicial() {
		String sturnos, nome, vida, municao, sanidade, kitMedico;
		sturnos = String.valueOf(cj.getTurnos());
		vida = String.valueOf(((Protagonista) cj.prot).getVida());
		municao = String.valueOf(((Protagonista) cj.prot).getMunicao());
		sanidade = String.valueOf(((Protagonista) cj.prot).getSanidade());
		kitMedico = String.valueOf(((Protagonista) cj.prot).getKitMedico());
		statsProt.add("numero de turnos:"+sturnos);
		statsProt.add("HP:"+vida+"/10");
		statsProt.add("municao:"+municao+"/7");
		statsProt.add("sanidade:"+sanidade+"/10");
		statsProt.add("kits disponiveis:"+kitMedico);
		
	}
	
	public void atualizaStatus(ArrayList<String> status) {
		statsProt = new ArrayList<>();
		statsProt = status;
		//colocar variaveis dependentes da classe protagonista
		/*statsProt = new ArrayList<>();
		statsProt = cj.statsProtagonista();
		/*statsProt.add("Nome: "+nome);
		statsProt.add("municao: "+municao+"/5");
		statsProt.add("sanidade: "+sanidade+"/10");
		statsProt.add("posicao atual:("+linha+","+coluna+")");
		statsProt.add("turnos: "+ turnos);*/
		batch.begin();
		for(int i = 0 ; i < statsProt.size(); i++) {
			font.draw(batch, statsProt.get(i), 0, 480-(20*i));
		}
		batch.end();
	}
	
	public void mostraMsg(ArrayList<String> mensagens) {
		msgs = new ArrayList<>();
		msgs = mensagens;
		batch.begin();
		for(int i = 0 ; i < msgs.size(); i++) {
			font.draw(batch, msgs.get(i), 0, 340-(20*i));
		}
		batch.end();
	}
	
	public void mostraInvestigacao() {
		msgs = new ArrayList<>();
		String  text = "frase estupidamente grande com ambito de verificar como esta se comporta na interface grafica do trabalho";
		//msgs.add("frase estupidamente grande com ambito de verific<br>ar como esta se comporta na interface grafica do trabalho");
		
		//colocar no controlle esse for para fazer o array de mensagens que o controlle passará para a interface 
		int length = text.length();
		for (int i = 0; i < length; i += 48) {
			msgs.add(text.substring(i, Math.min(length, i + 48)));
		}
		batch.begin();
		for(int i = 0 ; i < msgs.size(); i++) {
			font.draw(batch, msgs.get(i), 0, 380-(20*i));
		}
		batch.end();
	}
	
	@Override
	public void render() {
		ScreenUtils.clear(1f, 0, 0, 1);
		
		// tell the camera to update its matrices.
		camera.update();
		
		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		
		//cria tabuleiro
		batch.begin();
		for(int linha = 0; linha < 5; linha++) {
			for(int coluna = 0; coluna < 5; coluna++) {
				batch.draw(celblack, _00.x+(coluna*(tamanho_celula+espacamento_celulas)), _00.y-(linha*(tamanho_celula+espacamento_celulas)));
			}
		}
		batch.end();
		
		//controle de teste do trabalho
		if(Gdx.input.isKeyJustPressed(Keys.E)) {
			comando = 'e';
			batch.begin();
			batch.draw(celwhite, _00.x + ((tamanho_celula+espacamento_celulas)*cj.prot.getColuna()), _00.y - ((tamanho_celula+espacamento_celulas) * cj.prot.getLinha()));
			batch.end();
			msgs = cj.comando(comando);	
			statsProt = cj.statsProtagonista();	
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.W)){ comando = 'w'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}	
		if(Gdx.input.isKeyJustPressed(Keys.S)) { comando = 's'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.D)) { comando = 'd'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.A)) { comando = 'a'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.R)) { comando = 'r'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.F)) { comando = 'f'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.G)) { comando = 'g'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.Z)) { comando = 'z'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.X)) { comando = 'x'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.C)) { comando = 'c'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.Q)) { comando = 'q'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}		
		if(Gdx.input.isKeyJustPressed(Keys.T)) { comando = 't'; msgs = cj.comando(comando); statsProt = cj.statsProtagonista();}			
		
		if(check) {
			//mostraInvestigacao();
			//mostraMsg(msgs);
			check = false;
		}
		atualizaStatus(statsProt);
		mostraMsg(msgs);
		
	}
	
	@Override
	public void dispose() {
		// dispose of all the native resources
		celblack.dispose();
		celwhite.dispose();
		batch.dispose();
	}
}
