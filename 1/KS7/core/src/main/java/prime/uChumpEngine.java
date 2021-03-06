package prime;

import static prime.Core.uAppUtils.*;
import static prime.Core.uSketcher.*;
import static prime._METATRON.Metatron.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import prime.Core.uSketcher;
import prime.Core.Data.aType;
import prime.Core.Data.iType;
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
	}

	@Override
	public void render() {

		float deltaTime = Gdx.graphics.getDeltaTime();
		uAppUtils.update(deltaTime);
		METATRON.update(deltaTime);
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Sketcher.setProjectionMatrix(CAMERA.getProjection());

		// METATRON.Eye().logInput = true;
		// Sketcher.setProjectionMatrix(METATRON.Eye().getProjection());
		Sketcher.setProjectionMatrix(METATRON.CAMERA.getProjection());
		Sketcher.begin();
		Sketcher.getBatch().draw(image, 140, 210);
		Sketcher.end();

		// lT1();
		// Log();
		// lT2();
		// Log();
		lT3();
		// Log(METATRON.Multiplexer.getProcessors());

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
		// aType T = new aType(TheMetatron.VTS.All, "Thing");
		n1 = new aNode("WomboChombo");
		n2 = new aNode(1);
		n3 = new aNode(a);
		// n4 = new aNode(T);
		// n5 = new aDataField("TestValue", 4f);
	}

	public void gT2() {

	}

	public void lT1() {
		Log(n1);
		Log(n2);
		Log(n3);
		Log(n4);
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
		aType T = new aType(METATRON.VTS.All, "Normal", aVector.class);
		Log(i + " " + i.getClass().getSimpleName());
		Log(new aType(METATRON.VTS.All, "Normal", aVector.class));
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

		for (Object E : METATRON.VTS.Primitives.getKeys()) {
			if (E instanceof aType) {
				aType T = (aType) E;
				Log(T.toLog());
			}
		}

		Log("0 " + Metatron.INT.isOf(Metatron.NUM));
		Log("1 " + Metatron.NUM.isOf(Metatron.INT));
		Log("2 " + Metatron.INT.is(Metatron.NUM));
		Log("3 " + Metatron.NUM.is(Metatron.INT));
		Log("INT> " + Metatron.INT.isOf("NUMBER"));
		Log("INT> " + Metatron.INT.isOf("Integer"));
		Log("INT> " + Metatron.INT.covers("NUM"));
		Log("NUM> " + Metatron.NUM.isOf("Integer"));
		Log("NUM> " + Metatron.NUM.covers("INT"));
		Log("NUM> " + Metatron.NUM.covers("Integer"));

	}
}