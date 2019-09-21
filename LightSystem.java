import java.io.*;
import java.net.*;
import java.util.*;

/**
* The LightSystem class utilizes a network to link different displays together. 
* @author	Sambridhi Acharya and Andrew Quist 
*/
public class LightSystem extends Thread {
	/*
	*	Default port opens a arbitrary port in the local network. HIGH and LOW are representations of voltage messages. 
	*/
	public static final int DEFAULT_PORT = 9223;
	public static final String HIGH = "H";
	public static final String LOW = "L";

	private static Random random = new Random();

	public static Random getRandom() {
		return random;
	}
	/*
	*This stores all of our clients information. 
	*/
	private Set clients = new HashSet();
	private boolean isHigh = false;
	private int port;

	/**
	*Makes an empty light system class with a DEFAULT_PORT. 
	*/
	public LightSystem() {
		this(DEFAULT_PORT);
	}


	/**
	*Makes an empty light system class with the custom defined port
	*@param    int port 
	*/
	public LightSystem(int port) {
		this.port = port;
		start();
	}

	/**
	*This function runs indefinitely to handle each client and their ports. 
	*/
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {

				/**
				* This opens a socket for the client to connect to the server socket. The server accepts the connection and prints a message. 
				*/
				Socket clientSocket = serverSocket.accept();

				System.out.println(clientSocket + " connected");

				/**
				* ClientOut is created to gather data for the client's output. 
				*/
				PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
				clients.add(clientOut);
				/*
				* BufferReader is created to interpret the data sent through the client outputstream. 
				*/
				BufferedReader clientSocketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				/**
				* Creates a new thread for the client in order to handle each client concurrently. 
				*/
				LightSystemThread thread = new LightSystemThread(this, clientSocketIn);
				thread.start();
				notifyClient(clientOut);
			}
		} catch (BindException e) {
			throw new RuntimeException("LightSystem/other already running on port");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	* Is the voltage high? no? switch the light on and tell the other lights.
	*/
	public void switchOn() {
		if (!isHigh) {
			isHigh = true;
			notifyClients();
		}
	}
	/**
	* Is the voltage high? yes? switch the light off.
	*/
	public void switchOff() {
		if (isHigh) {
			isHigh = false;
			notifyClients();
		}
	}

	/**
	 * works through the list of clients, notifying them of a voltage change
	 */
	private void notifyClients() {
		Iterator it = clients.iterator();
		while (it.hasNext()) {
			PrintWriter clientOut = (PrintWriter) it.next();
			notifyClient(clientOut);
		}
	}

	/**
	 * The actual messaage sent to a client
	 * @param clientOut id of recieving client
	 */
	private void notifyClient(PrintWriter clientOut) {
		if (isHigh)
			clientOut.println(HIGH);
		else
			clientOut.println(LOW);
	}
}