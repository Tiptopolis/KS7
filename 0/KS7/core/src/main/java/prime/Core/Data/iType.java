package prime.Core.Data;

import prime.Core.Primitive.aMap;
import prime.Core.Primitive.aSet;

public interface iType {

	/*
	 * public default<T> T def() { return null; }
	 */

	public boolean is(String of);

	public boolean is(Object of);

	public Object of(String is);

	public Object of(Object is);

	public boolean covers(String other);

	public boolean covers(Object other);

	public default aSet<String> aliases() {
		return new aSet<String>();
	}

}
