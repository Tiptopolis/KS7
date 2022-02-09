package com.Rev.P1_Core.Console;

import static com.Rev.P1_Core.AppUtils.*;

import com.Rev.P1_Core.App;
import com.Rev.P1_Core.Primitive.aSet;

public class ConsoleLogger {

	public boolean active = true;
	public static ConsoleLogger DefaultLogger;
	private static aSet<String> pending = new aSet<String>();
	private static aSet<String> toLog = new aSet<String>();

	App owner;

	//Logs console input to IDE console
	public ConsoleLogger(App owner) {
		DefaultLogger = this;
		this.owner = owner;

	}

	//flushes pending -> toLog and toLog -> System.out
	public static void logOut(String log) {

		Log(log);
	}

	//flushes pending -> toLog and toLog -> System.out
	public static void logOut() {
		for (String p : pending) {
			toLog.append(p);
		}
		for (String s : toLog) {
			Log(s);
		}
		toLog.clear();
	}

	//append to pending
	public static void toLog(String to) {

		pending.append(to);
		// scan toLog list for Commands

	}

	//append to pending
	public static void toLog(Object o) {
		pending.append(o.toString());
	}

	public static void toLog(Object[] os) {
		String res = "";
		for (int i = 0; i < os.length; i++) {
			res += "[" + i + "]: " + os[i].toString() + "\n";
		}
		pending.append(res);
	}

	public static void toLog(aSet<String> to) {
		for (String s : to) {
			pending.append(s);
		}
	}

}
