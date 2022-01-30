package prime.Core.System.Console;

import static prime.Core.uAppUtils.*;

import prime.Core.Primitive.aLinkedList;
import prime.Core.Primitive.aSet;
import prime.Core.Primitive.A_I.iCollection;
import prime.Core.System.Console.UI.aUseSession;
import prime.Core.System.Console.UI.iConsoleListener;




public class ConsoleUI implements iConsoleListener {

	// Input Manager
	// transmits console input as String to Session(ViewManager)

	public aUseSession Session; // replaces current&previous




	public ConsoleUI() {
		//this.Session = new aUseSession(this, new MainMenu(this));
		this.Session.setView(Session.Root);

	}

	// prints session's current view to console
	public void render() {
		Session.Current.render();
	}

	// receives input, passes to user session for handling
	@Override
	public boolean input(String inp) {
		// Log(this.getClass().getSimpleName() + ":> " + inp);

		if (Session != null)
			return Session.input(inp);
		return false;
	}

	//gets listeners subscribed to this
	@Override
	public iCollection<iConsoleListener> getSubscribers() {

		aSet<iConsoleListener> s = new aSet<iConsoleListener>();
		if (this.Session != null)
			s.append(this.Session);
		return s;
	}

	public void dispose() {
		Session.dispose();
	}

}
