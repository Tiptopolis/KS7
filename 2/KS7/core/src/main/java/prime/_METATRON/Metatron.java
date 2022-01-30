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
	private static uFpsAdapter EYE;

	public InputMultiplexer Multiplexer;

	public static final _VTS VTS;
	// 8
	public static final aType NAN;
	public static final aType BLN;
	public static final aType TXT;
	public static final aType OBJ;
	public static final aType NUM;
	public static final aType INT;
	public static final aType FLT;
	public static final aType VEC;
	// public static final aType MAT;

	static {
		TheMetatron = new Metatron();
		CAMERA = new OrthoController(TheMetatron);
		CAMERA.init();
		// EYE = new uFpsAdapter(CAMERA);
		// EYE.init();
		TheMetatron.Multiplexer.addProcessor(CAMERA);
		VTS = new _VTS();

		NAN = new aType(true, VTS.Primitives, "NAN", Void.class, null);
		NAN.aliases().append("nan", "null", "void");
		VTS.All.put(NAN);

		BLN = new aType(true, VTS.Primitives, "BLN", Boolean.class, false);
		BLN.aliases().append("Boolean", "bool");
		VTS.All.put(BLN);

		TXT = new aType(true, VTS.Primitives, "STR", String.class, "\"\"");
		TXT.aliases().append("str", "String", "String[]", "char", "Character", "char[]", "Character[]", "CharSequence",
				"CharSequence[]", "VARCHAR");
		VTS.All.put(TXT, "");

		OBJ = new aType(true, VTS.Primitives, "OBJ", Object.class, NAN);
		OBJ.aliases().append("Object");
		VTS.All.put(OBJ);

		NUM = new aType(true, VTS.Primitives, "NUM", Number.class, 0);
		NUM.aliases().append("Number");
		VTS.All.put(NUM);

		INT = new aType(true, VTS.Primitives, "INT", Integer.class, 0);
		INT.aliases().append("int", "Integer", "i");
		VTS.All.put(INT, 0);

		FLT = new aType(true, VTS.Primitives, "FLT", Float.class, 0f);
		FLT.aliases().append("float", "double", "long", "short", "f", "d", "l", "s");
		VTS.All.put(FLT, 0f);

		VEC = new aType(true, VTS.Primitives, "VEC", aVector.class, new aVector());
		VEC.aliases().append("aVector", "int[]", "float[]", "long[]", "short[]", "Number[]");
		VTS.All.put(VEC, new aVector());

		NUM.extend(INT);
		NUM.extend(FLT);
		NUM.extend(VEC);
	}

	public Metatron() {
		this.Multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(this.Multiplexer);
		this.Multiplexer.addProcessor(this);
		Log(">>");

	}

	public void update(float deltaTime) {
		if (CAMERA != null && CAMERA.isOn)
			CAMERA.update(deltaTime);
		if (EYE != null && EYE.isOn)
			EYE.update();
	}

	public static void openEye() {
		EYE = new uFpsAdapter(CAMERA);
		// EYE.Camera.viewport = CAMERA.Camera.viewport;
		// TheMetatron.Multiplexer.addProcessor(EYE);
		CAMERA.addAdapter(EYE);
		EYE.init();
	}

	public static void closeEye() {
		CAMERA.removeAdapter(EYE);
		EYE.isOn = false;
	}

	public static uFpsAdapter Eye() {
		if (EYE == null)
			openEye();

		return EYE;
	}

}
