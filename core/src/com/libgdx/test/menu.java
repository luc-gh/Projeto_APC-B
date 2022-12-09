package com.libgdx.test;

import com.badlogic.gdx.Gdx;                             //classe para funções específicas do framework
import com.badlogic.gdx.Input;                           //classe para entrada de dados
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;                   //classe para renderização de plano de fundo

public class menu extends ScreenAdapter {  //extensão de ScreenAdapter é necessária para construir 'gametest'

    gametest gt;  //instância da classe principal

    public menu(gametest gt){  //construtor da classe inicial
        this.gt = gt;
    }

    String c1 = "Break Bricks", c2 = "Clique na tecla ESPAÇO para jogar", c3 = "Ou aperte ESC para fechar o jogo";

    @Override
    public void show(){  //função que confirma mudança de tela e recebe a entrada de usuário
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){
                if(keyCode == Input.Keys.SPACE){
                    gt.setScreen(new jogo(gt));
                }
                else if(keyCode == Input.Keys.ESCAPE){  //fechar o jogo (ESC)
                    gt.dispose();
                    Gdx.app.exit();
                }
                return true;
            }
        });
    }

    @Override
    public void render (float delta) {  //função de renderização
        //Background color
        Gdx.gl.glClearColor(0,0,0,1);  //cor de fundo: preto
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Batch (textos):
        gt.batch.begin();
        gt.font.draw(gt.batch, c1, gt.origemX - 39, gt.origemY);
        gt.font.draw(gt.batch, c2, gt.origemX - 115, gt.origemY - 50);
        gt.font.draw(gt.batch, c3, gt.origemX - 109, gt.origemY - 75);
        gt.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);

    }
}
