package prime.Core.System.UI;

import static prime.Core.System.Console.ConsoleUI.*;
import static prime.Core.uAppUtils.*;

import prime.Core.Primitive.aMap;
import prime.Core.Primitive.aNode;
import prime.Core.Primitive.A_I.iCollection;
import prime.Core.Primitive.A_I.iDisposable;
import prime.Core.System.uApp;
import prime.Core.System.Console.ConsoleUI;





public abstract class aConsoleView extends aNode<aConsoleView> implements iConsoleListener, iDisposable {

	// a UI frame, some default navigation options built-in

	protected ConsoleUI manager;
	protected aMap<String, String> options;// temporary

	public aConsoleView(ConsoleUI manager) {
		super();
		this.manager = manager;
		this.options = new aMap<String, String>();

	}

	// fill widgets
	public void init() {
		if (manager.Session.Previous != null)
			this.options.put("<", "BACK");

		this.options = new aMap<String, String>();
		this.options.put("^", "OUT");
		this.options.put("X", "EXIT");
	}

	// console input
	// checks own handle() before bubbling input further
	@Override
	public boolean input(String inp) {
		// Log(this.getClass().getSimpleName() + ":> " + inp);
		if (this.handle(inp)) {
			return true;
		}
		this.render();
		if (this.getSubscribers() != null)
			for (iConsoleListener s : this.getSubscribers()) {
				if (s.input(inp))
					return true;

			}
		return false;
	}

	// used in conjunction with input. Override this to actually perform the logic
	// intended from an input event
	protected boolean handle(String inp) {
		if (inp.toUpperCase().equals("X") || inp.toUpperCase().equals("EXIT")) {
			uApp.AppConsole.input("SHELL:TERMINATE");
			return true;
		}

		if (inp.equals("^") || inp.toUpperCase().equals("OUT")) {
			this.manager.Session.returnMain();
			return true;
		}

		if (inp.equals("<") || inp.toUpperCase().equals("BACK")) {
			this.manager.Session.back();
			return true;
		}

		return false;
	}

	// iRenderer:Runnable lol
	// print to IDE console.
	public void render() {
		Page();
		// Log(this.options.toString());
		// Log("[" + this.getClass().getSimpleName() + "]");
	}

	// sub-subscribers. could be useful if this were a proper scene-graph
	@Override
	public iCollection<iConsoleListener> getSubscribers() {
		// bubbles through members
		// SceneGraph
		return null;
	}

	// init + bump to hide any debug logs init might throw out
	// force of habit; if this were a GUI, i'd use this to initialize graphics
	public void show() {
		this.init();
		Page();
		this.render();
	}

	// quit complaining and just write it. 
	// its worth waiting 0.001 more seconds 
	// for your $#!% to properly terminate
	public void dispose() {
		this.options.clear();
	}

}
