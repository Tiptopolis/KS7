package prime.Core.Primitive.GeneralData;

import static prime.Core.uAppUtils.*;

import prime.Core.Data.iType;
import prime.Core.Primitive._N;
import prime.Core.Primitive.aNode;
import prime.Core.Primitive.aSet;
import prime._METATRON.Math.aVector;

public class aDataField<Y> extends aNode<Y> {

	// property
	// table column
	public String label;
	public aDataType T;

	public aDataField(String label, aDataType type) {
		super();
		this.label = label;
		this.T = type;
		this.init(this.T);
	}

	public aDataField(String label, Y data) {
		super();
		this.label = label;
		if (data instanceof CharSequence)
			this.T = parseType("" + data);
		else
			this.T = parseType(data.getClass().getSimpleName());
		this.get = data;
	}

	private aDataType parseType(String type) {
		if (aDataType.NAN.is(type)) {
			return this.T = aDataType.NAN;
		}
		if (aDataType.STR.is(type)) {
			return this.T = aDataType.STR;
		}
		if (aDataType.INT.is(type)) {
			return this.T = aDataType.INT;
		}
		if (aDataType.FLT.is(type)) {
			return this.T = aDataType.FLT;
		}
		if (aDataType.OBJ.is(type)) {
			return this.T = aDataType.OBJ;
		}
		return this.T = aDataType.OBJ;
	}

	private void init(aDataType T) {
		if (T.is("STR"))
			this.set((Y) " ");

		if (T.is("INT")) {
			this.set((Y) new Integer(0));
		}
		if (T.is("FLT")) {
			this.set((Y) new Float(0f));
		}
		if (T.is("VEC")) {
			this.set((Y) new aVector(0));
		}
		if (T.is("OBJ") || this.T.is("NAN")) {
			// this.get = null;
			this.set(null);
		}

		// Log(" aDataField}>=- " + T + " " + this.T + " -> " + this.get());
	}

	@Override
	public String toString() {
		String f = "";
		if (this.get != this)
			f = this.get.toString();

		String s = this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode()) + "=[" + this.label
				+ "](" + "<" + this.get.getClass().getSimpleName() + ">" + f + ")";

		return s;
	}

	public static enum aDataType implements iType {
		NAN(null, "nan", "null", "void"), //
		OBJ(Object.class, "Object"), //
		STR(String.class, "str", "String", "char", "Character", "char[]", "CharSequence", "VARCHAR"), //
		INT(Integer.class, "int", "Integer", "i"), //
		FLT(Float.class, "float", "double", "long", "short", "f", "d", "l", "s"), //
		VEC(aVector.class, "Vector");

		private Class of;
		private aSet<String> aliases = new aSet<String>();

		private aDataType(Class of, String... aliases) {
			this.of = of;
			this.aliases.append(aliases);
		}

		public boolean is(String s) {
			if (this.toString().equals(s) || this.aliases.contains(s) || this.aliases.contains(s.toUpperCase())
					|| this.aliases.contains(s.toLowerCase()))
				return true;
			else
				return false;
		}

		public Object of(String s) {
			if (this.aliases.contains(s) || this.aliases.contains(s.toUpperCase())
					|| this.aliases.contains(s.toLowerCase()))
				return this.of;

			return null;
		}

		@Override
		public boolean is(Object of) {
			
			if(of instanceof _N)
				return this.is(((_N)of).Label());

			return this.is(of.toString());
		}

		@Override
		public Object of(Object is) {
			return this.of(is.toString());
		}

		@Override
		public boolean covers(String other) {
			
			return this.is(other);
		}

		@Override
		public boolean covers(Object other) {
			return this.is(other);
		}

	}

}