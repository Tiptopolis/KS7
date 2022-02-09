package com.Rev.P1_Core.Console.UI;

import static com.Rev.P1_Core.AppUtils.*;

import com.Rev.P1_Core.App;
import com.Rev.P1_Core.Console.ConsoleUI;
import com.Rev.P1_Core.Primitive.aLinkedList;
import com.Rev.P1_Core.Primitive.A_I.iCollection;
import com.Rev.P1_Core._aBankApp.Data._User;

public class aUseSession implements iConsoleListener {

	// ViewManager, also tracks currently logged-in _User

	public aConsoleView Root;

	public aConsoleView Current;
	public aConsoleView Previous;

	aLinkedList<aConsoleView> NaigationPath;// <-ToDo

	public _User loggedAs;

	public String as = "null";

	public aUseSession(ConsoleUI handler, aConsoleView root) {
		this.Root = root;

	}

	// console input
	@Override
	public boolean input(String inp) {
		// Log(this.getClass().getSimpleName() + ":> " + inp);

		if (this.Current != null)
			return this.Current.input(inp);

		return false;
	}

	// previous screen
	public void back() {
		if (this.Previous != null) {
			this.setView(this.Previous);
		}
	}

	// return to MainMenu
	public void returnMain() {
		this.Previous = null;
		this.setView(this.Root);
	}

	// you're here forever
	public void exit() {
		this.loggedAs.clear();
		App.AppConsole.input("SHELL:TERMINATE");
	}

	// sets the view context to be rendered with render()
	public void setView(aConsoleView view) {
		if (this.Current != null) {
			this.Previous = this.Current;
			this.Current.dispose();
		}
		Log("VIEW> " + view.getClass().getSimpleName());
		this.Current = view;
		this.Current.show();

	}

	public void dispose() {
		this.loggedAs = null;
		this.as = "";
		// this.NaigationPath.clear();
		this.Current = null;
		this.Previous = null;
	}

}
