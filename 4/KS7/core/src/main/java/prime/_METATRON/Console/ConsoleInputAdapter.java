package prime._METATRON.Console;

import static prime.Core.uAppUtils.*;
import static prime.Core.uSketcher.*;
import static prime._METATRON.Metatron.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.gdx.InputProcessor;

import prime.Core.System.Event._EventShell;


public class ConsoleInputAdapter {

	

	protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	protected BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	protected _EventShell shell;

	public ConsoleInputAdapter(_EventShell shell) {
		this.shell = shell;

	}

	public int read() throws IOException {

		return reader.read();
	}

	public String readLine() throws IOException {

		return reader.readLine();
		
	}

	public void write(String str) throws IOException {
		this.writer.write(str);
	}

	public void writeLine(String str) throws IOException {

		this.write(str);
	}

	public void dispose()
	{
		
		try {
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log("ConsoleDisposed");
	}
}
