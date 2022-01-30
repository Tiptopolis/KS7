package prime._METATRON;

import static prime.Core.uAppUtils.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;

import prime.Core.Data.aType;
import prime.Core.Data.iType;
import prime.Core.Primitive._N;
import prime.Core.Primitive.aSet;
import prime.Core.Primitive.A_I.iCollection;
import prime._METATRON.Camera.OrthoController;
import prime._METATRON.Camera.uFpsAdapter;
import prime._METATRON.Math.aVector;
import prime._METATRON.Sys._VTS;

public class Metatron extends InputAdapter implements iMonad {

	public static final Metatron TheMetatron;
	public static final OrthoController CAMERA;
	// public static final uFpsAdapter EYE;

	public InputMultiplexer Multiplexer;

	public static final _VTS VTS;
	public static final aType NAN;
	public static final aType STR;
	public static final aType OBJ;
	public static final aType NUM;
	public static final aType INT;
	public static final aType FLT;
	public static final aType VEC;

	static {
		TheMetatron = new Metatron();
		CAMERA = new OrthoController(TheMetatron);
		CAMERA.init();
		// EYE = new uFpsAdapter(CAMERA);
		// EYE.init();
		TheMetatron.Multiplexer.addProcessor(CAMERA);
		VTS = new _VTS();

		NAN = new aType(VTS.Primitives, "NAN", Void.class, null);
		NAN.aliases().append("nan", "null", "void");
		VTS.All.put(NAN);

		STR = new aType(VTS.Primitives, "STR", Object.class);
		STR.aliases().append("str", "String", "char", "Character", "char[]", "CharSequence", "VARCHAR");
		VTS.All.put(STR, "");

		OBJ = new aType(VTS.Primitives, "OBJ", Object.class);
		OBJ.aliases().append("Object");
		VTS.All.put(OBJ);

		NUM = new aType(VTS.Primitives, "NUM", Number.class);
		NUM.aliases().append("Object");
		VTS.All.put(NUM);

		INT = new aType(VTS.Primitives, "INT", Integer.class);
		INT.aliases().append("int", "Integer", "i");
		VTS.All.put(INT, 0);

		FLT = new aType(VTS.Primitives, "FLT", Float.class);
		FLT.aliases().append("float", "double", "long", "short", "f", "d", "l", "s");
		VTS.All.put(FLT, 0f);

		VEC = new aType(VTS.Primitives, "VEC", aVector.class);
		OBJ.aliases().append("aVector", "int[]", "float[]", "long[]", "short[]");
		VTS.All.put(VEC, new aVector());
	}

	public Metatron() {
		this.Multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(this.Multiplexer);
		this.Multiplexer.addProcessor(this);
		Log(">>");

	}

	public static enum PrimitiveType implements iType, _N<iType> {
		NAN(null, null, "nan", "null", "void", "", " "), //
		OBJ(Object.class, null, "Object"), //
		STR(String.class, " ", "str", "String", "char", "Character", "char[]", "CharSequence", "VARCHAR"), //
		INT(Integer.class, 0, "int", "Integer", "i"), //
		FLT(Float.class, 0f, "float", "double", "long", "short", "f", "d", "l", "s"), //
		VEC(aVector.class, new aVector(), "Vector")//
		;

		private Class of;
		private Object value;
		private aSet<String> aliases = new aSet<String>();
		public aType get;

		private PrimitiveType(Class of, Object defaultVal, String... aliases) {
			this.of = of;
			this.value = defaultVal;
			this.aliases.append(aliases);
			this.get = new aType(TheMetatron.VTS.Primitives, this.toString(), of, defaultVal, aliases);
		}

		@Override
		public boolean is(String s) {
			if (this.Label().equals(s) || this.Label().toUpperCase().equals(s.toUpperCase())
					|| this.toString().equals(s) || this.aliases.contains(s) || this.aliases.contains(s.toUpperCase())
					|| this.aliases.contains(s.toLowerCase()))
				return true;
			else
				return false;
		}

		@Override
		public boolean is(Object of) {
			if (this == NAN && of == null)
				return true;
			if (of instanceof Class)
				return this.is(((Class) of).getSimpleName());
			if (of instanceof _N)
				return this.is(((_N) of).Label());

			return this.is(of.getClass().getSimpleName());
		}

		@Override
		public Object of(String s) {
			if (this.aliases.contains(s) || this.aliases.contains(s.toUpperCase())
					|| this.aliases.contains(s.toLowerCase()))
				return this.of;

			return null;
		}

		@Override
		public Object of(Object is) {
			return this.of(is.toString());
		}

		@Override
		public boolean covers(String other) {
			return this.aliases.contains(other);

		}

		@Override
		public boolean covers(Object other) {
			if (other == null)
				return this.covers("null");

			return this.covers(other.toString());
		}

		@Override
		public String Label() {
			return this.toString();
		}

	}
}
