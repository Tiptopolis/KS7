package prime.Core.System;

import static prime.Core.uAppUtils.*;

import prime.Core.Primitive.A_I.iCollection;
import prime.Core.System.Console.Console;
import prime.Core.System.Console.ConsoleUI;
import prime.Core.System.Console.UI.iConsoleListener;
import prime.Core.System.Event._EventShell;

public class uApp extends _EventShell  implements iApplet{
	public static uApp Current;
	public boolean running;
	private long prevTime = System.nanoTime();
	private float deltaTime = 0f;

	public static Console AppConsole; // console input
	public static ConsoleUI UI; // view-manager

	@Override
	public void create() {
		super.init();


	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

	}

	@Override
	public void update(int deltaTime) {
		super.update(deltaTime);

	}

	@Override
	public void render() {


	}

	@Override
	public void resize(int width, int height) {


	}

	// kills the console thread, ensures everything is properly GC'd.
	public void dispose() {
		super.dispose();
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

	

}
