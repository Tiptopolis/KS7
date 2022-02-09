package com.Rev.P1_Core._MonDexApp.Views;

import static com.Rev.P1_Core.AppUtils.*;

import com.Rev.P1_Core.Console.ConsoleUI;
import com.Rev.P1_Core.Console.UI.aConsoleView;
import com.Rev.P1_Core._MonDexApp.MonDirector;
import com.Rev.P1_Core._MonDexApp.Data._Creature;

public class NewCreatureForm extends aConsoleView {

	// ui to creating a new account for current user.

	private String type = "";
	boolean dioT = false;


	public NewCreatureForm(ConsoleUI manager) {
		super(manager);
	}

	@Override
	public void init() {
		super.init();

		this.options.put("*", "CREATURE TYPE");
		this.options.put(".", "-SUBMIT-");
	}

	// print to console
	@Override
	public void render() {

		super.render();

		Log(this.options.toString());
		Log("*" + this.type);
		this.dioT = false;
	}

	// handle incoming console input
	@Override
	public boolean handle(String inp) {

		if (!this.dioT) // no dialogs open?
			if (super.handle(inp))
				return true;
		if (this.dioT)
			this.type = inp.replaceAll("[^a-zA-Z]", " ");

		// setAcctType();
		if (inp.equals(".") || inp.equals("") || inp.equals(" ") || inp.equals("SUBMIT") || inp == null) {
			Log("TRY SUBMIT>");
			this.submit();
			return true;
		}

		if (inp.equals("*") || inp.equals("CREATURE TYPE") || inp.equals("1")) {
			Log("ENTER CREATURE TYPE: ");
			this.dioT = true;
			return dioT;
		}

		return false;
	}

	//////
	//

	private void submit() {
		Log(":: " + this.type);
		if (this.type.equals("")) {
			Log("MISSING INFO");
			return;
		}
		int userID = this.manager.Session.loggedAs.dbIndex();
		String type = this.type;

		_Creature creature = new _Creature(userID, type);
		Log(this.type + "<< ");
		Log(">>>> "+ creature);
		MonDirector.Creatures.create(creature);
		this.clear();
		creature.clear();
		// this.input("^"); // casts the 'OUT' spell
		this.manager.Session.setView(new UserView(this.manager));
	}

}