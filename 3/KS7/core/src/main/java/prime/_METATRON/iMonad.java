package prime._METATRON;

import prime._METATRON.Core.Transform;

public interface iMonad {

	public default boolean exists() {
		return true;
	}

	public default void exists(boolean f) {
		this.exists();
	}

	public default String getName() {
		return this.getClass().getSimpleName();
	}

	public default Transform getTransform() {
		return null;
	}

	public default void transformUpdated() {
		
	} // informed by transform that it has updated

	public default String toLog() {
		return this.getClass().getSimpleName();
	}
}