package com.libgdx.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input;

import java.util.Random;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;  //classe para criar objetos


public class jogo extends ScreenAdapter {

    gametest gt;  //instância da classe principal
    public jogo(gametest gt){  //construtor da classe inicial
        this.gt = gt;
    }

    float velR = 5.00f, velCirX = 0f, velCirY = 4f, x1, x2;  //variáveis das figuras
    boolean cond = false;  //condição para o início do jogo
    String posXret1 = "", posYret1 = "", posXret2 = "", posYret2 = "", posXcir = "", posYcir = "";  //Strings para mostrar coordenadas dos desenhos



    @Override
    public void show(){
        if(gt.finalCond == 1){
            gt.setScreen(new fim(gt));  //tela de fim de jogo
        }
    }

    @Override
    public void render(float delta){  //delta permite a reflexão da função render através das classes
        movRect();  //função de movimento do retângulo
        posXcir = "C X = (" + gt.posBolaX + ")";
        posYcir = "C Y = (" + gt.posBolaY + ")";
        posXret1 = "R1 X = (" + gt.posRet1X + ")";
        posYret1 = "R1 Y = (" + gt.posRet1Y + ")";
        posXret2 = "R2 X = (" + gt.posRet2X + ")";
        posYret2 = "R2 Y = (" + gt.posRet2Y + ")";

        gt.shape.begin(ShapeRenderer.ShapeType.Filled);  //inicialização para desenhos/shapes
        gt.batch.begin();  //inicialização para textos

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){  //garante que a bola só se movimenta quando o jogo iniciar
            cond = true;
        }
        if(cond){
            movCirc();  //função para movimento da bola
        }
        else{
            gt.font.draw(gt.batch, "Aperte ENTER para iniciar", gt.origemX - 75, gt.origemY);
        }

        Gdx.gl.glClearColor(0,0,0,1);  //tela preta
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Linha:
        x1 = gt.posRet1X - gt.largRet/2f;
        x2 = gt.posRet1X + gt.largRet/2f;

        gt.shape.setColor(1,1,1,1);  //cor dos desenhos: branca

        //Retângulos:
        gt.shape.rect(gt.posRet1X, gt.posRet1Y, gt.largRet, gt.altRet);       //posição e tamanho do retângulo 1
        gt.font.draw(gt.batch, posXret1, gt.posRet1X, gt.posRet1Y + 35);   //texto que mostra a posição X do retângulo 1
        gt.font.draw(gt.batch, posYret1, gt.posRet1X, gt.posRet1Y + 20);   //texto que mostra a posição Y do retângulo 1
        gt.shape.rect(gt.posRet2X, gt.posRet2Y, gt.largRet, gt.altRet);       //posição e tamanho do retângulo 2
        gt.font.draw(gt.batch, posXret2, gt.posRet2X, gt.posRet2Y + 35);   //texto que mostra a posição X do retângulo 2
        gt.font.draw(gt.batch, posYret2, gt.posRet2X, gt.posRet2Y + 20);   //texto que mostra a posição Y do retângulo 2

        //Círculo:
        gt.shape.circle(gt.posBolaX, gt.posBolaY, gt.raioBola);  //posição e tamanho do círculo
        gt.font.draw(gt.batch, posXcir, gt.posBolaX, gt.posBolaY + 35);   //texto que mostra a posição X do círculo
        gt.font.draw(gt.batch, posYcir, gt.posBolaX, gt.posBolaY + 20);   //texto que mostra a posição Y do círculo

        gt.batch.end();  //finalização de textos
        gt.shape.end();  //finalização de desenhos
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

    public void movRect(){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && gt.posRet1X < (gt.largTela - gt.largRet)){
            gt.posRet1X += velR;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && gt.posRet1X > 0){
            gt.posRet1X -= velR;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && gt.posRet2X < (gt.largTela - gt.largRet)){
            gt.posRet2X += velR;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && gt.posRet2X > 0){
            gt.posRet2X -= velR;
        }
    }

    public void movCirc(){
        gt.posBolaX += velCirX;
        gt.posBolaY += velCirY;

        //Limites de tela:
        if(gt.posBolaX > gt.largTela - gt.raioBola){  //bola bate no limite da direita
            velCirX *= -1;
        }
        if(gt.posBolaX < gt.raioBola){  //bola bate no limite da esquerda
            velCirX *= -1;
        }
        if(gt.posBolaY > gt.altTela - gt.raioBola){  //bola bate no limite superior
            gt.finalCond = 1;
            show();
        }
        if(gt.posBolaY < 0 + gt.raioBola){  //bola bate no limite inferior
            gt.finalCond = 1;
            show();
        }

        //Retângulo inferior:
        if(gt.posBolaY - gt.raioBola - 2 == gt.posRet1Y + gt.altRet/2){  //base da bolinha na mesma altura que o topo do retângulo inferior
            Random random = new Random();
            if(gt.posBolaX - gt.raioBola >= gt.posRet1X - 8 && gt.posBolaX - gt.raioBola < gt.posRet1X + gt.largRet/4){  //extrema esquerda
                velCirX = -1 * (random.nextInt(3) + 2);  //gera um número aleatório entre -4 e -2
                velCirY *= -1;
            }
            else if(gt.posBolaX - gt.raioBola >= gt.posRet1X + gt.largRet/4 && gt.posBolaX - gt.raioBola < gt.posRet1X + gt.largRet/2){  //esquerda central
                velCirX = -1 * (random.nextInt(3) + 1);  //gera um número aleatório entre -3 e -1
                velCirY *= -1;
            }
            else if(gt.posBolaX - gt.raioBola == gt.posRet1X + gt.largRet/2){
                velCirX = 0;
                velCirY *= -1;
            }
            else if(gt.posBolaX - gt.raioBola > gt.posRet1X + gt.largRet/2 && gt.posBolaX - gt.raioBola < gt.posRet1X + 3*gt.largRet/4){  //direita central
                velCirX = random.nextInt(3) + 1;  //gera um número aleatório entre 1 e 3
                velCirY *= -1;
            }
            else if(gt.posBolaX - gt.raioBola >= gt.posRet1X + 3*gt.largRet/4 && gt.posBolaX - gt.raioBola < gt.posRet1X + gt.largRet){  //extrema direita
                velCirX = random.nextInt(3) + 2;  //gera um número aleatório entre 2 e 4
                velCirY *= -1;
            }
        }
        //Retângulo superior:
        if(gt.posBolaY + gt.raioBola + 2 == gt.posRet2Y - gt.altRet/2){  //base da bolinha na mesma altura que o sopé do retângulo superior
            Random random = new Random();
            if(gt.posBolaX - gt.raioBola >= gt.posRet2X - 8 && gt.posBolaX - gt.raioBola < gt.posRet2X + gt.largRet/4){  //extrema esquerda
                velCirX = -1 * (random.nextInt(3) + 2);  //gera um número aleatório entre -4 e -2
                velCirY *= -1;
            }
            else if(gt.posBolaX - gt.raioBola >= gt.posRet2X + gt.largRet/4 && gt.posBolaX - gt.raioBola < gt.posRet2X + gt.largRet/2){  //esquerda central
                velCirX = -1 * (random.nextInt(3) + 1);  //gera um número aleatório entre -3 e -1
                velCirY *= -1;
            }
            else if(gt.posBolaX - gt.raioBola == gt.posRet2X + gt.largRet/2){
                velCirX = 0;
                velCirY *= -1;
            }
            else if(gt.posBolaX - gt.raioBola > gt.posRet2X + gt.largRet/2 && gt.posBolaX - gt.raioBola < gt.posRet2X + 3*gt.largRet/4){  //direita central
                velCirX = random.nextInt(3) + 1;  //gera um número aleatório entre 1 e 3
                velCirY *= -1;
            }
            else if(gt.posBolaX - gt.raioBola >= gt.posRet2X + 3*gt.largRet/4 && gt.posBolaX - gt.raioBola < gt.posRet2X + gt.largRet){  //extrema direita
                velCirX = random.nextInt(3) + 2;  //gera um número aleatório entre 2 e 4
                velCirY *= -1;
            }
        }
    }
}
