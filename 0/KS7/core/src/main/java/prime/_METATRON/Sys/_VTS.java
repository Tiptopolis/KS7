package prime._METATRON.Sys;

import prime.Core.Data.aType;
import prime.Core.Primitive.aMap;
import prime.Core.Primitive.aMultiMap;
import prime.Core.Primitive.aSet;
import prime.Core.Primitive.A_I.iDisposable;

public class _VTS implements iDisposable {
	// serializable into a .Dex
	//public aMap<String, aType> Primitives; //switch to default value?	
	//public aMap<String, aType> All;
	public aMap<aType, String> Primitives;
	public aMap<aType, Object> All;

	//get aNode<T> @ default value
	
	public _VTS() {
		//this.Primitives = new aMap<String, aType>();
		//this.All = new aMap<String, aType>();
		this.Primitives = new aMap<aType, String>();
		this.All = new aMap<aType, Object>();
	}

	public aType parseType(String typeName) {
				
		return null;
	}

	@Override
	public void dispose() {
		this.Primitives.clear();
		this.All.clear();
	}
}
