package prime;

import static prime.Core.uAppUtils.*;
import static prime.Core.uSketcher.*;
import static prime._METATRON.Metatron.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import prime.Core.uSketcher;
import prime.Core.Data.aType;
import prime.Core.Data.iType;
import prime.Core.Primitive.aMap;
import prime.Core.Primitive.aMap.Entry;
import prime.Core.Primitive.aNode;
import prime.Core.System.Console.Console;
import prime.Core.uAppUtils;
import prime._METATRON.Metatron;
import prime._METATRON.Math.aNumber;
import prime._METATRON.Math.aVector;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class uChumpEngine extends ApplicationAdapter {

	public static Metatron METATRON;
	public static Console MetatronConsole;

	private SpriteBatch batch;
	private Texture image;

	@Override
	public void create() {

		batch = new SpriteBatch();
		image = new Texture("libgdx.png");
		uSketcher s = new uSketcher();
		METATRON = Metatron.TheMetatron;
		this.gT1();
		this.gP1();
	}

	float theta = 0;

	@Override
	public void render() {

		float deltaTime = Gdx.graphics.getDeltaTime();
		uAppUtils.update(deltaTime);
		METATRON.update(deltaTime);
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Sketcher.setProjectionMatrix(CAMERA.getProjection());

		METATRON.Eye().logInput = true;
		// Sketcher.setProjectionMatrix(METATRON.Eye().getProjection());
		Matrix4 useMat = METATRON.Eye().getProjection();
		theta += deltaTime;
		if (theta >= 10) {
			METATRON.closeEye();
			useMat = METATRON.CAMERA.getProjection();
		}
		Sketcher.setProjectionMatrix(useMat);
		Sketcher.begin();
		Sketcher.getBatch().draw(image, 140, 210);
		Sketcher.end();

		
		lT1();
		// Log();
		// lT2();
		// Log();
		lT3();
		// Log(METATRON.Multiplexer.getProcessors());
		// lP1();

	}

	@Override
	public void dispose() {
		batch.dispose();
		image.dispose();
	}

	///////////
	aNode n1;
	aNode n2;
	aNode n3;
	aNode n4;
	aNode n5;

	public void gT1() {
		aVector a = new aVector(4, 0, 4);
		aType T = new aType(TheMetatron.VTS.All, "Thing", Object.class);
		n1 = new aNode("WomboChombo");
		n2 = new aNode(1);
		n3 = new aNode(a);
		n4 = new aNode(T);
		// n5 = new aDataField("TestValue", 4f);
	}

	public void gT2() {

	}

	public void gP1() {

	}

	public void lT1() {
		Log(n1);
		Log(n2);
		Log(n3);
		Log(n4.toLog());
		// Log(n5);
		// Log(aDataField.aDataType.OBJ.aliases());
		// for (iType t : aDataField.aDataType.FLT.aliases()) {
		// Log(t + " : Aliases");
		// Log(aDataField.aDataType.FLT.aliases().getValues());
		// }
		// Log("> "+((aDataField)n5).get());
		// Log();
	}

	public void lT2() {
		int[] i = new int[0];
		aType T = new aType(false, METATRON.VTS.All, "Normal", aVector.class);
		Log(i + " " + i.getClass().getSimpleName());
		Log(new aType(false, METATRON.VTS.All, "Normal", aVector.class));
		Log(T);
		Log(">> " + T.of());
		Log(METATRON.VTS.Primitives);
		Log(METATRON.VTS.All);
		Log();
		Log(METATRON.NAN.toLog());
		Log();
		Object o = null;

		// Log(Metatron.PrimitiveType.NAN);
	}

	public void lT3() {

		for (Entry E : METATRON.VTS.Primitives) {
			Log(E);
		}
		for (Object E : METATRON.VTS.Primitives.getValues()) {
			if (E instanceof aType) {
				aType T = (aType) E;
				Log(T.toLog());
			}
		}

		Log("0 " + Metatron.INT.isOf(Metatron.NUM));
		Log("1 " + Metatron.NUM.isOf(Metatron.INT));
		Log("2 " + Metatron.INT.is(Metatron.NUM));
		Log("3 " + Metatron.NUM.is(Metatron.INT));
		Log("4 " + Metatron.INT.is("Integer"));
		Log("INT> " + Metatron.INT.isOf("NUMBER"));
		Log("INT> " + Metatron.INT.isOf("Integer"));
		Log("INT> " + Metatron.INT.covers("NUM"));
		Log("NUM> " + Metatron.NUM.isOf("Integer"));
		Log("NUM> " + Metatron.NUM.covers("INT"));
		Log("NUM> " + Metatron.NUM.covers("Integer"));
		Log("NUM> " + Metatron.NUM.covers(Metatron.INT));

	}

	aMap<String, aType> Dex;

	public void lP1() {
		Dex = new aMap<String, aType>();
		aType Mon = new aType(true, null, "Mon", aType.class);

		aType newMon = new aType(true, Dex, "BLB", Mon);
		newMon = new aType(true, Dex, "CHR", Mon);
		newMon = new aType(true, Dex, "SQR", Mon);

		Log(Mon.toLog());
		for (Entry<String, aType> E : Dex) {
			// aType T = (aType)o;
			if (E.getValue() != null) {
				Log(E);
			}
		}

		Log();
		Log(Dex.get("BLB"));

	}
}