package prime.Core.System.Event;

import com.badlogic.gdx.utils.Pool.Poolable;

import prime.Core.Data.aType;
import prime.Core.Primitive.aNode;

public class aEvent extends aNode<aEvent> implements Poolable {

	public Object origin;
	public Object target;

	public aType type;
	
	private boolean capture; // true means event occurred during the capture phase
	private boolean bubbles = true; // true means propagate to target's parents, if any
	private boolean handled; // true means the event was handled (the _EventShell will consume the input)
	private boolean stopped; // true means event propagation was stopped
	private boolean cancelled; // true means propagation was stopped and any action that this event would cause should not happen

	@Override
	public void reset() {
		this.origin = null;
		this.target = null;
		this.type = null;

	}

}
