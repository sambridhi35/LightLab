import java.io.*;
import java.net.*;
import java.util.*;

/**
 * LightPanel is a network interface designed to simulate a wired connection.
 * any light panel can connect to a light system on the same computer (or to an ip on the local network)
 */
public class LightPanel extends Thread {
	private static Set idsUsed = new HashSet();

	private int id;
	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private boolean isHigh = false;

	public LightPanel() {
		this("localhost", LightSystem.DEFAULT_PORT);
	}
	
	/**
 	 * A constructor that connects to an outside light system. The empty constructor connects to the computer's ip and a default port number.
	 * @param string host - the ip of the computer it must connect to
	 * @param integer port - port number of the connecting light system
	*/
	public LightPanel(String host, int port) {
		
		do {
			id = LightSystem.getRandom().nextInt(15) + 1;
		} while (!idsUsed.add(Integer.valueOf(id)));
		/*
		Take the input from the host and the port and depending on the input given the output. If the host is invalid throws an error. 
		*/
		try {
			socket = new Socket(host, port);
			socketOut = new PrintWriter(socket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			start();
		} catch (UnknownHostException e) {
			throw new RuntimeException("Invalid host:  " + host);
		} catch (IOException e) {
			throw new RuntimeException("Unable to connect to LightSystem");
		}
	}

	/**
	When the switch is on the voltage of the LightSystem is high. 
	*/
	public void switchOn() {
		socketOut.println(LightSystem.HIGH);
	}


	/**
	When the switch is off the voltage of the LightSystem is low. 
	*/
	public void switchOff() {
		socketOut.println(LightSystem.LOW);
	}

	/**
	When the socket stops receiving or sending any  messages it is closed. 
	*/
	public void close() {
		try {
			socketOut.close();
			socketIn.close();
			socket.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	This function reads the socket input and if the value of socketIn is not null it checks if the voltage of the LightSystem is high ot low if 
	it is high it returns true if it is not it returns false and it keeps on going through the loop when it is called.
	*/

	public void run() {
		try {
			String line = socketIn.readLine();
			while (line != null) {
				if (line.equals(LightSystem.HIGH))
					isHigh = true;
				else if (line.equals(LightSystem.LOW))
					isHigh = false;
				line = socketIn.readLine();
			}
		/*
		throws an exception if the line is null i.e if it is not hearing anything back from the socket
		*/
		} catch (Exception e) {
			System.out.println("LightPanel disconnected");
			throw new RuntimeException(e);
		}
	}
	/**
	* if the light is on it returns that the voltage of the light is high
	* @return    boolean the voltage is high
	*/
	public boolean isOn() {
		return isHigh;
	}

	/**
	* @return	string the value of id as a string    
	*/
	public String toString() {
		return "#" + id;
	}

	/**
	* this function returns the id of the LightSystem i.e the value that is chosen randomly
	* @return   integer id of the LightSystem
	*/
	public int getID() {
		return id;
	}
}