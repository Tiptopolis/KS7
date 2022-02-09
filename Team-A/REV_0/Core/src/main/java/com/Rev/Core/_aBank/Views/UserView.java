package com.Rev.Core._aBank.Views;

import static com.Rev.Core.AppUtils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Rev.Core.Console.ConsoleUI;
import com.Rev.Core.Console.UI.aConsoleView;
import com.Rev.Core.Primitive.aMap;
import com.Rev.Core.Primitive.aMultiMap;
import com.Rev.Core.Primitive.aSet;
import com.Rev.Core.Primitive.Data.aDataField;
import com.Rev.Core.Util.StringUtils;
import com.Rev.Core._aBank.BankDirector;
import com.Rev.Core._aBank.Data._Account;
import com.Rev.Core._aBank.Data._User;

public class UserView extends aConsoleView {

	// User Interactor

	// private aMultiMap<Integer, aDataField> accountCache = new aMultiMap<Integer,
	// aDataField>();
	private _User logged;
	private aSet<_Account> accountCache = new aSet<_Account>();
	// private aSet<_Account> accountPage = new aSet<_Account>();
	// private int page = 0;
	// show 4 accounts per page?

	private boolean dioPicAct = false;

	public UserView(ConsoleUI manager) {
		super(manager);
	}

	// setup after creation. called in the ConsoleView.show() for future
	// adaptability
	@Override
	public void init() {

		this.cacheAccounts();
		this.options = new aMap<String, String>();
		this.options.put("^", "OUT");
		this.options.put("X", "EXIT");

		if (accountCache.getSize() > 4) {
			this.options.put("<", "PREV");
			this.options.put(">", "NEXT");
		}

		this.options.put("1", "VIEW ACCT");
		this.options.put("2", "OPEN NEW ACCT");

		this.logged = this.manager.Session.loggedAs;

	}

	// print to screen
	@Override
	public void render() {
		super.render();
		// data
		// input options
		// int startInd = page*4;
		// int pg = Math.min(4, accountPage.getSize()-startInd);

		String nametag = "";
		nametag += StringUtils.toName(logged.FirstName()) + " " + StringUtils.toName(logged.LastName());
		Log("Welcome " + nametag + "!");
		Log("Accounts: ");
		for (int i = 0; i < accountCache.getSize(); i++) {
			Log("[" + i + "]" + accountCache.get(i));
		}
		Log("___________");
		Log(this.options.toString());
		Log("");

		dioPicAct = false;
	}

	// handle incoming input
	@Override
	public boolean handle(String inp) {

		if (super.handle(inp))
			return true;

		if (dioPicAct) {
			if (!StringUtils.validAmount(inp))
				return false;

			// submit
			// int i = Integer.parseInt(inp);
			// Log(">>"+i);
			float f = Float.parseFloat(inp);
			if ((int) f > this.accountCache.getSize() || (int) f < 0)
				return false;

			this.pickAccount((int) f);
			return true;
		}

		if (inp.equals("<"))
			Log("[<]placeholder");
		if (inp.equals(">"))
			Log("[>]placeholder");
		if (inp.equals("1")) {
			this.dioPicAct = true;
		}

		if (inp.equals("1")) {
			Log("[#]CHOOSE ACCOUNT: ");
			dioPicAct = true;
			return dioPicAct;
		}

		if (inp.equals("2")) {
			this.manager.Session.setView(new NewAccountForm(this.manager));
			return true;
		}

		return false;
	}

	// gets all accounts with the Session.loggedAs UserID
	private void cacheAccounts() {
		// get all accounts belonging to this user via junction table
		// get account_ids belonging to this user_id
		// get accounts by those ids
		this.accountCache = new aSet<_Account>();
		Connection connection = BankDirector.DB_Link;
		String query = "SELECT * FROM accounts WHERE owner_ID=?";
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, this.manager.Session.loggedAs.Index());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				// Log("--------"); // so account gets properly, now append them all
				int u_id = rs.getInt("owner_id");
				int type = rs.getInt("type");
				float bal = rs.getFloat("balance");
				int actnum = rs.getInt("account_num");
				int db = rs.getInt("account_ID");
				_Account acct = new _Account(u_id, type, bal, actnum, db);

				this.accountCache.append(acct);
				// Log("--------" + acct);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// set view, separated out for my own sanity
	private void pickAccount(int index) {
		this.manager.Session.setView(new AccountView(this.manager, this.accountCache.get(index)));
	}

	// clears the cache
	@Override
	public void clear() {
		super.clear();
		this.accountCache.clear();
	}

}
