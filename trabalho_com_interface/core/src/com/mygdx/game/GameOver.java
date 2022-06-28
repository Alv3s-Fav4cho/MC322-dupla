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

public class GameOver {
	
	private SpriteBatch batch;
	private BitmapFont font;
	Texture fundo;
	OrthographicCamera camera;
	//Viewport viewport;
	
	GameOver(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 500,200);
		batch = new SpriteBatch();
		font = new BitmapFont();
		//fundo = new Texture("GameOver.png");
	}
	
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		font.draw(batch,"Game Over", 500/2 , 200/2);
		batch.end();
	}
}
