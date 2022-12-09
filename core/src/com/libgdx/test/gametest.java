package com.libgdx.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;  //classe para criar objetos
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class gametest extends Game {

	ShapeRenderer shape;
	BitmapFont font;
	SpriteBatch batch;

	float origemX, origemY;    //variáveis que receberão os pontos de origem
	float largTela, altTela;   //variáveis que recebem a altura e a largura de tela
	float posRet1X, posRet1Y, posRet2X, posRet2Y;    //variáveis que recebem as coordenadas da posição inicial do retângulo
	float posBolaX, posBolaY;  //variáveis que recebem as coordenadas da posição inicial do círculo
	float velBol;
	int finalCond = 0;
    float altRet = 4f, largRet = 68f, raioBola = 6f;  //valores proporcionais para que eventualmente eles sejam iguais

	@Override
	public void create(){  //configurações padronizadas
		//instâncias:
		shape = new ShapeRenderer();  //desenhos
		font = new BitmapFont();      //fonte de textos
		batch = new SpriteBatch();    //renderização de textos
		reset();
		setScreen(new menu(this));    //Passando classe como instância para a classe menu
	}

	public void reset(){  //reinicializa definições do início de jogo
		largTela = Gdx.graphics.getWidth();
		altTela = Gdx.graphics.getHeight();
		origemX = largTela/2;  //origem de X é o ponto médio da largura
		origemY = altTela/2;  //origem de Y é o ponto médio da altura
		posRet1X = origemX - largRet/2f;
		posRet1Y = origemY - 200;
		posRet2X = origemX - largRet/2f;
		posRet2Y = origemY + 200;
		posBolaX = origemX;
		posBolaY = origemY - 200 + altRet + raioBola;
		velBol = 0;
	}

	@Override
	public void dispose () {
		shape.dispose();
		font.dispose();
		batch.dispose();
	}
}
