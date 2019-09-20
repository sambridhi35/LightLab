/*
 * @author      Sambridhi Acharya and Andrew Quist 
 * @version     1.0                 
 */

import java.io.*;
import java.net.*;
import java.util.*;

/*
Class to implement the lightDisplay class system and test its functions. 
*/
public class TestClient {
	/*
	runs the terminal program.
	*/
	public static void main(String[] args) {

		String ip = args[0];
		int port = 9223;

		LightDisplay d1 = new LightDisplay(new LightPanel(ip, port));

		BitHandler b1 = new BitHandler();
		BitDisplay bd1 = new BitDisplay(b1);
}
	
}

