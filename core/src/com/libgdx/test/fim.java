package com.libgdx.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;

public class fim extends ScreenAdapter{

    gametest gt;  //instância da classe principal
    public fim(gametest gt){
        this.gt = gt;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keyCode){  //voltar ao menu
                if(keyCode == Input.Keys.SPACE){
                    gt.reset();  //redefine e redeclara as configurações e estados iniciais de jogo
                    gt.finalCond = 0;  //redefine a condição de derrota para que o jogo possa recomeçar
                    gt.setScreen(new menu(gt));
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
        gt.font.draw(gt.batch, "FIM DE JOGO", gt.origemX - 39, gt.origemY);
        gt.font.draw(gt.batch, "Aperte ESPAÇO para voltar ao menu", gt.origemX - 115, gt.origemY - 50);
        gt.font.draw(gt.batch, "Ou aperte ESC para fechar o jogo", gt.origemX - 109, gt.origemY - 75);
        gt.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

}
