import java.io.*;

/**
 * LightSystemThread is a multithreaded manager of all connected light panels.
 */
public class LightSystemThread extends Thread {
	private LightSystem system;
	private BufferedReader in;

	/**
	 * Creates a new light system thread using existing light systems.
	 * @param system
	 * @param in the buffer reader for getting networked data
	 */
	public LightSystemThread(LightSystem system, BufferedReader in) {
		this.system = system;
		this.in = in;
	}

	/**
	 * takes in the data from light panels and parses it
	 */

	public void run() {
		try {
			String line = in.readLine();
			while (line != null) {
				if (line.equals(LightSystem.HIGH))
					system.switchOn();
				else
					system.switchOff();
				line = in.readLine();
			}
		} catch (IOException e) {
			throw new RuntimeException("LightPanel disconnected");
		}
	}
}