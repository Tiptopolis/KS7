package prime.Core.System.Console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import prime.Core.System.uApp;


public class ConsoleIO {

	
	//Console Buffer Read/Write.
	
	protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	protected BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	protected uApp shell;

	public ConsoleIO(uApp shell) {
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

	public void dispose() {
		if (this.reader != null && this.writer != null)
			try {
				this.reader.close();
				this.writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

}
