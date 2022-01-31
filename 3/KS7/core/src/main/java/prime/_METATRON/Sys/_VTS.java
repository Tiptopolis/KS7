package prime._METATRON.Sys;

import prime.Core.Data.aType;
import prime.Core.Primitive.aMap;
import prime.Core.Primitive.aMultiMap;
import prime.Core.Primitive.aSet;
import prime.Core.Primitive.A_I.iDisposable;

public class _VTS implements iDisposable {
	// serializable into a .Dex
	
	public aMap<aType, Object> All;
	public aMap<aType, Object> Primitives; //Type, DefaultValue

	// get aNode<T> @ default value

	public _VTS() {

		this.Primitives = new aMap<aType, Object>();
		this.All = new aMap<aType, Object>();
	}

	public aType getA(String typeName) {

		return null;
	}

	@Override
	public void dispose() {
		this.Primitives.clear();
		this.All.clear();
	}
}
