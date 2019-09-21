/**
 * @author      Sambridhi Acharya and Andrew Quist 
 * @version     1.0                 
 */

import java.io.*;
import java.net.*;
import java.util.*;

/*
* Recieves any incoming client with the default port.
*/
public class TestServer {
	public static void main(String[] args) {
		LightSystem system = new LightSystem();
		LightDisplay d2 = new LightDisplay(new LightPanel());

		BitHandler b1 = new BitHandler();
		BitDisplay bd1 = new BitDisplay(b1);
}
	
}

