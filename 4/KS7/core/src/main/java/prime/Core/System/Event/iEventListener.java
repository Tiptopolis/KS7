package prime.Core.System.Event;

import prime.Core.System.UI.iConsoleListener;

public interface iEventListener extends iConsoleListener {

	public default boolean handle(aEvent event)
	{
		return false;
	}
	
}
