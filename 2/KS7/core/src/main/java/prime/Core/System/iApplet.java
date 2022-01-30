package prime.Core.System;

import com.badlogic.gdx.InputProcessor;

import prime.Core.System.Console.UI.iConsoleListener;


public interface iApplet extends InputProcessor, iConsoleListener{
	public void create();

	public void update(float deltaTime);

	public void update(int deltaTime);

	public void render();

	public void resize(int width, int height);

	public void dispose();
}
