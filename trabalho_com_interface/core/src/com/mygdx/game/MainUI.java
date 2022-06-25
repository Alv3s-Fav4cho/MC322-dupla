package com.mygdx.game;

import java.awt.Font;
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

	private BitmapFont font;
	private Array<String> msgs;
	private boolean check=false;
	
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
	
	
	public void atualizaStatus() {
		msgs = new Array<>();
		msgs.add("Nome: "+nome);
		msgs.add("municao: "+municao+"/5");
		msgs.add("sanidade: "+sanidade+"/10");
		msgs.add("posicao atual:("+linha+","+coluna+")");
		msgs.add("turnos: "+ turnos);
		batch.begin();
		for(int i = 0 ; i < msgs.size; i++) {
			font.draw(batch, msgs.get(i), 0, 480-(20*i));
		}
		batch.end();
	}
	
	public void mostraInvestigacao() {
		msgs = new Array<>();
		String  text = "frase estupidamente grande com ambito de verificar como esta se comporta na interface grafica do trabalho";
		//msgs.add("frase estupidamente grande com ambito de verific<br>ar como esta se comporta na interface grafica do trabalho");
		
		//colocar no controlle esse for para fazer o array de mensagens que o controlle passará para a interface 
		int length = text.length();
		for (int i = 0; i < length; i += 48) {
			msgs.add(text.substring(i, Math.min(length, i + 48)));
		}
		batch.begin();
		for(int i = 0 ; i < msgs.size; i++) {
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
		batch.begin();
		if(Gdx.input.isKeyJustPressed(Keys.T)) {
			if(municao > 0) {
				batch.draw(celwhite, _00.x + ((tamanho_celula+espacamento_celulas)*coluna), _00.y - ((tamanho_celula+espacamento_celulas) * linha));
				municao--;  
				check = true;
			}
		}
		batch.end();
		if(Gdx.input.isKeyJustPressed(Keys.W)) {
			if(linha > 0) {
				linha--;
				sanidade--;
				turnos++;
				check = false;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.S)) {
			if(linha < 5) {
				linha++;
				sanidade--;
				turnos++;
				check = false;
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.A)) {
			if(coluna > 0) {
				coluna--;
				sanidade--;
				turnos++;
				check = false;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.D)) {
			if(coluna < 5) {
				coluna++;
				sanidade--;
				turnos++;
				check = false;
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.X)) {
			sanidade = 10;
			turnos++;
		}
		
		if(Gdx.input.isKeyPressed(Keys.Z)) {
			check = true;
		}
		
		if(check) {
			mostraInvestigacao();
		}
		atualizaStatus();
		
	}
	
	@Override
	public void dispose() {
		// dispose of all the native resources
		celblack.dispose();
		celwhite.dispose();
		batch.dispose();
	}
}
