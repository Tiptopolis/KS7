package prime.Core.System;

import static prime.Core.uAppUtils.*;

import prime.Core.Primitive.A_I.iCollection;
import prime.Core.System.Console.Console;
import prime.Core.System.Console.ConsoleUI;
import prime.Core.System.Console.UI.iConsoleListener;

public class uApp implements iApplet {
	public static uApp Current;
	public boolean running;
	private long prevTime = System.nanoTime();
	private float deltaTime = 0f;

	public static Console AppConsole; // console input
	public static ConsoleUI UI; // view-manager

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	// kills the console thread, ensures everything is properly GC'd.
	public void dispose() {

		AppConsole.IO.dispose();
		UI.dispose();
		Log("D-------------------------------------------------------G");

	}

	// stops running, murders the console, burns down its house
	public void terminate() {
		this.running = false;
		this.dispose();
		System.exit(0);
	}

	// call from input-sender
	@Override
	public boolean input(String inp) {
		// Log(this.getClass().getSimpleName() + ":" + inp);
		UI.input(inp);
		// Log(" App.input() >>>>>>");
		return false;
	}

	// new phone, who this?
	@Override
	public iCollection<iConsoleListener> getSubscribers() {
		return UI.getSubscribers();
	}

	public String toLog() {
		String log = "\n";
		// log += this.Management + "\n";
		// log += this.Management.DB_Link + "\n";
		log += UI.Session.Current + "\n";
		log += "o<[" + UI.Session.Current.getClass().getSimpleName() + "]\n";
		if (UI.Session.Previous != null)
			log += "o<  [" + UI.Session.Previous.getClass().getSimpleName() + "]\n";
		return log;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

}
