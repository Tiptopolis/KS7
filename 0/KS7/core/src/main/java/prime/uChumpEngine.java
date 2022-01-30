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

	public static Metatron TheMetatron;
	public static Console MetatronConsole;

	private SpriteBatch batch;
	private Texture image;

	@Override
	public void create() {

		batch = new SpriteBatch();
		image = new Texture("libgdx.png");
		uSketcher s = new uSketcher();
		this.gT1();
	}

	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		uAppUtils.update(deltaTime);

		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Sketcher.setProjectionMatrix(CAMERA.getProjection());
		Sketcher.begin();
		Sketcher.getBatch().draw(image, 140, 210);
		Sketcher.end();

		lT1();
		Log();
		lT2();
		Log();
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
		aType T = new aType(TheMetatron.VTS.All, "Normal", aVector.class);
		Log(i + " " + i.getClass().getSimpleName());
		Log(new aType(TheMetatron.VTS.All, "Normal", aVector.class));
		Log(T);
		Log(">> " + T.of());
		Log(TheMetatron.VTS.Primitives);
		Log(TheMetatron.VTS.All);
		Log();
		Log(TheMetatron.NAN.toLog());
		Log();
		Object o = null;
		Log("1 " + Metatron.PrimitiveType.NAN.is(o) + "<");
		Log("2 " + Metatron.PrimitiveType.NAN.is(Metatron.NAN));
		Log("3 " + Metatron.PrimitiveType.NAN.is(Metatron.INT));
		Log("4 " + Metatron.NAN.is(Metatron.PrimitiveType.NAN));
		Log("5 " + Metatron.INT.is(Metatron.PrimitiveType.NAN));
		Log("6 " + Metatron.INT.is(Metatron.PrimitiveType.INT));
		// Log(Metatron.PrimitiveType.NAN);
	}
}