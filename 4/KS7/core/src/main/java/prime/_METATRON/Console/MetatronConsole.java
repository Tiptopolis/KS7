package prime._METATRON.Console;

import static prime.Core.uAppUtils.*;
import static prime.Core.uSketcher.*;
import static prime._METATRON.Metatron.*;
import java.io.IOException;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.GdxRuntimeException;

import prime.Core.Primitive.aSet;
import prime.Core.Resources.FontAtlas;
import prime.Core.System.Event.iEventListener;
import prime.Core.System.UI.iConsoleListener;
import prime._METATRON.Metatron;

public class MetatronConsole implements iEventListener {

	// Metatron UI
	protected static Metatron TheMetatron;
	public static MetatronConsole mConsole;
	protected static mConsoleLogger mLogger;
	protected Thread InteruptAvoidanceMechanism;
	public static ConsoleInputAdapter IO;
	// protected InputProcessor shell;

	public static FontAtlas Fonts = new FontAtlas();

	protected boolean running = false;
	public boolean echo = true;

	// protected aViewContext localPrimeModal; // focus, target context

	public aSet<iConsoleListener> subscribers = new aSet<iConsoleListener>();

	public MetatronConsole() {
		TheMetatron = Metatron.TheMetatron;
		mConsole = this;

		mLogger = new mConsoleLogger(TheMetatron);
		this.IO = new ConsoleInputAdapter(TheMetatron);
		InteruptAvoidanceMechanism = new Thread("mConsole") {
			@Override
			public void run() {

				if (running) {
					try {
						MetatronConsole.this.mainLoop();
					} catch (Throwable t) {
						if (t instanceof RuntimeException)
							throw (RuntimeException) t;
						else
							throw new GdxRuntimeException(t);
					}
				}
			}
		};
		this.running = true;
		InteruptAvoidanceMechanism.start();
	}

	void mainLoop() throws IOException {
		System.out.println("_CONSOLE LOOP START");
		String tmp = ":";
		System.out.flush();
		while (running) {// STEP INSTRUCTIONS

			synchronized (IO) {
				tmp = IO.readLine();
				if (tmp.equals("SHELL:TERMINATE")) {
					Log(this.toLog());
					post("SHELL:TERMINATE");
					this.terminate();
				}

				if (tmp.equals(":LOG") || tmp.equals("")) {
					post(":LOG");
					Log(this.toLog());

				}

				if (tmp.equals("METATRON:LOG")) {
					Log(TheMetatron.toLog());
				}

				post(tmp);

				if (echo)
					System.out.println("$&: [" + tmp + "]");

			}

			System.in.mark(0);
			System.in.reset();

			System.out.println("Console Loop Executed Successfully");

		}
		System.out.println("Shell Teminated");
	}

	public void terminate() {
		System.out.println("SHELL:TERMINATE...");
		this.running = false;
		Gdx.app.exit();
		System.exit(0);
	}

	public static boolean com(String input) {
		if (mLogger == null) {
			Log(">>no logger<<");
			return false;
		}

		mLogger.toLog(input);
		return true;
	}

	public static void post(String input) {
		if (mLogger != null && mLogger.active) {
			mConsoleLogger.toLog(input);
			mConsoleLogger.logOut();
		}
		// works, just need to reimp
		// Message m = new Message(input);
		// if (Metatron.CURRENT != null)
		// Metatron.CURRENT.handle(m);

	}

	public static void post(Object o) {
		String r = "" + o.toString();
		post(r);
	}

	public static void post(Object[] os) {
		String res = "";
		for (int i = 0; i < os.length; i++) {
			res += "[" + i + "]: " + os[i].toString() + "\n";
		}
		post(res);

	}

	@Override
	public aSet<iConsoleListener> getSubscribers() {
		return this.subscribers;

	}

	// Top-Level Input Filter
	public String toLog() {
		// java.lang.Thread.activeCount()

		String log = "";

		// log+=Metatron.CURRENT.toLog();

		log += "\n";
		log += "#ThreadsActive- " + java.lang.Thread.activeCount();
		log += "\n";
		// log += ""+java.lang.Thread.getAllStackTraces();
		Map<Thread, StackTraceElement[]> threads = java.lang.Thread.getAllStackTraces();
		for (Map.Entry<Thread, StackTraceElement[]> t : threads.entrySet()) {

			if (!t.getKey().isDaemon()) {
				log += t.toString();
				log += "\n";
			}

		}
		log += "\n";

		return log;
	}
}
