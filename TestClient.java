/**
 * @author      Sambridhi Acharya and Andrew Quist 
 * @version     1.0                 
 */

import java.io.*;
import java.net.*;
import java.util.*;

/*
* the client program. Can be run in the terminal with a custom ip as an argument!
*/
public class TestClient {
	public static void main(String[] args) {

		String ip = args[0];
		/**
		 * the server must have the default port number
		 */
		int port = 9223;

		LightDisplay d1 = new LightDisplay(new LightPanel(ip, port));

		BitHandler b1 = new BitHandler(ip, port);
		BitDisplay bd1 = new BitDisplay(b1);
}
	
}

