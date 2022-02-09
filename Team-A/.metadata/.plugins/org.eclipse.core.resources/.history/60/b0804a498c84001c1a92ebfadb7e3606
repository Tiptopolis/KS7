package prime.Core.System.Console;

import static prime.Core.uAppUtils.*;

import java.io.IOException;
import java.util.Map;

import com.badlogic.gdx.Gdx;

import prime.uChumpEngine;
import prime.Core.Primitive.aList;
import prime.Core.Primitive.A_I.iCollection;
import prime.Core.System.uApp;
import prime.Core.System.Event._EventShell;
import prime.Core.System.UI.iConsoleListener;


public class Console implements iConsoleListener {

	// holds the input thread for non-blocking console input
	//

	protected _EventShell Target;
	public static ConsoleLogger Logger;
	public ConsoleIO IO;

	protected Thread ConsoleInputThread;
	public boolean echo = false;

	public aList<iConsoleListener> Subscribers = new aList<iConsoleListener>();

	public Console(_EventShell target) {
		this.Target = target;
		this.IO = new ConsoleIO(target);
		Logger = new ConsoleLogger(target);

		// create & start IO thread
		ConsoleInputThread = new Thread("ConsoleThread") {
			@Override
			public void run() {
				if (Target == null && uApp.Current != null)
					Target = uApp.Current;
				if (Target.running && Target != null) {
					try {
						Console.this.consoleLoop();
					} catch (Throwable t) {
						throw new RuntimeException(t);
					}
				}
			}
		};
		ConsoleInputThread.start();
	}

	// console loop?
	public void consoleLoop() throws IOException {
		System.out.println("_CONSOLE LOOP START");
		String tmp = ":";
		System.out.flush();
		while (this.Target.running) {// STEP INSTRUCTIONS

			synchronized (IO) {
				tmp = IO.readLine();

				// post(tmp);

				if (echo)
					System.out.println("$&: [" + tmp + "]");

			}
			boolean b = this.input(tmp);
			System.in.mark(0);
			System.in.reset();

			// System.out.println("Console Loop Executed Successfully");

		}
		System.out.println("Shell Teminated");
	}

	// post input to ConsoleLogger's pending output
	public static void post(String input) {
		if (Logger != null && Logger.active) {
			ConsoleLogger.toLog(input);
			ConsoleLogger.logOut();
		}
	}

	public static void post(Object o) {
		String r = "" + o.toString();
		post(r);
	}

	// take & handle console input, passes to subscribed listeners
	public boolean input(String msg) {

		if (msg.equals("SHELL:TERMINATE")) {
			Log(this.toLog());
			post("SHELL:TERMINATE");			
			//Target.dispose();
			System.out.println("SHELL:TERMINATE...");
			Target.running = false;		
			Gdx.app.exit();
			System.exit(0);
		}

		if (msg.equals(":LOG")) {
			post(":LOG");
			Log(this.toLog());

		}

		if (msg.equals("APP:LOG")) {
			Log(uApp.Current.toLog());
		}

		for (iConsoleListener c : this.getSubscribers()) {
			if (c.input(msg))
				return true;
		}
		return false;
	}

	// get listeners
	@Override
	public iCollection<iConsoleListener> getSubscribers() {
		if (this.Subscribers == null)
			this.Subscribers = new aList<iConsoleListener>();
		return this.Subscribers;
	}

	public String toLog() {

		String log = "";

		log += this.Target.toLog();
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
