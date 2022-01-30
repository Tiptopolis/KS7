package prime.Core.Data;

import prime.Core.Primitive._N;
import prime.Core.Primitive.aList;
import prime.Core.Primitive.aMap;
import prime.Core.Primitive.aMap.Entry;
import prime.Core.Primitive.aNode;
import prime.Core.Primitive.aSet;
import prime.Core.Primitive.A_I.iCollection;
import prime.Core.Primitive.A_I.iGroup;
import prime.Core.Primitive.A_I.iMap;

public class aType extends aNode<aType> implements iType {

	protected aSet<aType> inherits;

	public String label = "";
	protected aSet<String> aliases;
	protected Class of;
	protected aMap<String, iType> variants; // enumerated instances with pre-defined overrides
	protected aMap<String, iType> extensions; // sub-classes, extensions

	protected Object context;
	/*
	 * public aType(iMap defineIn, String as) { this.label = as; this.aliases = new
	 * aSet<String>(); this.of = aType.class; this.get = this; this.inherits = new
	 * aSet<aType>(); defineIn.put(this,this.of); }
	 */

	public aType(iMap defineIn, String as, Object of) {
		this.label = as;
		this.aliases = new aSet<String>();
		this.variants = new aMap<String, iType>();
		this.extensions = new aMap<String, iType>();

		if (of == null)
			this.of = Class.class;
		else if (of instanceof Class)
			this.of = (Class) of;
		else
			this.of = of.getClass();
		this.get = this;

		this.inherits = new aSet<aType>();
		this.context = defineIn;
		defineIn.put(this, this.of);
	}

	public aType(iMap<aType, String> defineIn, String as, Class of, Object defaultValue) {
		this.label = as;
		this.aliases = new aSet<String>();
		this.variants = new aMap<String, iType>();
		this.extensions = new aMap<String, iType>();

		this.of = of;
		this.get = this;

		this.inherits = new aSet<aType>();
		this.context = defineIn;
		defineIn.put(this, defaultValue);

	}

	public aType(iMap<aType, String> defineIn, String as, Class of, Object defaultValue, String... aliases) {
		this(defineIn, as, of, defaultValue);
		this.aliases.append(aliases);
	}

	public aType inherit(aType parentType) {

		if (!parentType.extensions.containsValue(parentType))
			parentType.extend(this);

		return this;
	}

	public aType extend(aType subType) {
		// extends this type with subTyp
		this.extensions.put(subType.label, subType);
		subType.inherits.append(this);
		return subType;

	}

	@Override
	public boolean is(String of) {
		if (this.label.equals(of) || this.label.toUpperCase().equals(of.toUpperCase()) || this.toString().equals(of)
				|| this.aliases.contains(of) || this.aliases.contains(of.toUpperCase())
				|| this.aliases.contains(of.toLowerCase()))
			return true;
		else
			return false;
	}

	@Override
	public boolean is(Object of) {
		if (of instanceof Class)
			return this.is(((Class) of).getSimpleName());
		if (of instanceof _N)
			return this.is(((_N) of).Label());

		return this.is(of.getClass().getSimpleName());
	}

	@Override
	public Class of() {
		if (this.of == null)
			return Class.class;

		return this.of;
	}

	//////
	// .CONTAINS

	@Override
	public boolean isOf(String n) {
		if (this.aliases.contains(n))
			return true;
		for (aType t : this.inherits) {
			if (t.of.getSimpleName().equals(n) || t.of.getSimpleName().toUpperCase().equals(n.toUpperCase()))
				return true;
		}
		return false;
	}

	public boolean isOf(aType t) {
		return this.inherits.contains(t);
	}

	public boolean isOf(Class c) {
		for (aType t : this.inherits) {
			if (t.of.equals(c))
				return true;
		}
		return false;
	}

	@Override
	public boolean isOf(Object of) {

		return this.inherits.contains(of);
	}

	// is x a subtype of this?
	public boolean covers(String s) {
		// aliases
		// variants
		// extensions
		if (s == null)
			return this.covers("null");

		if (this.aliases.contains(s) || this.variants.containsKey(s) || this.extensions.containsKey(s))
			return true;

		for (Object E : this.variants.getValues()) {
			if (E instanceof aType) {
				aType T = (aType) E;
				if (T.covers(s))
					return true;

			}
		}

		for (Object E : this.extensions.getValues()) {
			if (E instanceof aType) {
				aType T = (aType) E;
				if (T.covers(s))
					return true;

			}
		}

		return false;
	}

	public boolean covers(Class c) {
		for (String s : this.aliases) {
			if (s.equals(c.getSimpleName()))
				return true;
		}
		for (Entry<String, iType> e : this.variants.getEntries()) {
			if (e.getValue() instanceof aType)
				if (((aType) e.getValue()).isOf(c))
					return true;
		}
		for (Entry<String, iType> e : this.extensions.getEntries()) {
			if (e.getValue() instanceof aType)
				if (((aType) e.getValue()).isOf(c))
					return true;
		}

		return false;
	}

	public boolean covers(aType t) {

		if (this.variants.containsValue(t) || this.extensions.containsValue(t))
			return true;

		return false;
	}

	public boolean covers(Object o) {

		if (o == null)
			return this.covers("null");
		if (o instanceof aNode) {
			aNode n = (aNode) o;
			return this.covers(n.get());
		}

		return this.covers(o.toString());
	}

	// .CONTAINS
	///////

	@Override
	public aSet<String> aliases() {
		return this.aliases;
	}

	@Override
	public String Label() {
		return this.label;
	}

	@Override
	public String toString() {
		String f = "";
		if (this.of == null)
			f = "null";
		else
			f = this.of.getSimpleName();

		String s = "<" + this.label + ">:<" + f + ">";

		return s;
	}

	@Override
	public String toLog() {
		String log = this.toString() + "\n";
		log += " ~{" + this.aliases + "}\n";
		log += " :{" + this.inherits + "}\n";
		log += " >{" + this.variants + "}\n";
		log += " _{" + this.extensions + "}\n";
		return log;
	}

	@Override
	public boolean equals(Object other) {
		return this.is(other);
	}

}
