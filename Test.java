/**
 * @author      Sambridhi Acharya and Andrew Quist 
 * @version     1.0                 
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
* An experimental class to implement the lightDisplay class system and test its functions. 
*/
public class Test {
	public static void main(String[] args) {
		LightSystem system = new LightSystem();
		LightDisplay d1 = new LightDisplay(new LightPanel());
		LightDisplay d2 = new LightDisplay(new LightPanel());

		BitHandler b1 = new BitHandler();
		BitDisplay bd1 = new BitDisplay(b1);
}
	
}

