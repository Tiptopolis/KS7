package prime.Core.System.Event;

import com.badlogic.gdx.InputAdapter;

import prime.Core.Data.aType;
import prime.Core.Primitive.aQueue;
import prime.Core.Primitive.aSet;
import prime.Core.Primitive.A_I.iDisposable;
import prime.Core.System.Console.UI.iConsoleListener;

public class _EventShell extends InputAdapter implements iDisposable, iEventListener, iConsoleListener {

	public boolean running;
	protected aSet<aType> EventTypes = new aSet<aType>();	
	public aQueue<aEvent> EventQueue = new aQueue<aEvent>();

	public _EventShell() {

	}

	@Override
	public void init()
	{
		this.running = true;
	}
	
	public void update(float deltaTime) {

	}

	public void update(int deltaTime) {

	}

	
	public void dispose() {
		this.EventQueue.clear();
		this.EventTypes.clear();
		this.running = false;
	}
	

	
	public String toLog()
	{
		return this.getClass().getSimpleName();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

}
