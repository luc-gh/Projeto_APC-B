package com.libgdx.test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.libgdx.test.gametest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Jogo Teste");
		config.setWindowedMode(500,500);    //janela 500x500
		config.setResizable(false);  					//dimensões inalteráveis
		new Lwjgl3Application(new gametest(), config);
	}
}
