import java.awt.event.*;
import javax.swing.*;

/**
 * The LightDisplay program inspects LightPanel classes, displaying whether or not the light is on. It also creates gui for a user to switch these lights.
 * @author      Andrew Quist and Sam Acharya
*/
public class LightDisplay extends Thread implements ActionListener {
	private LightPanel panel;
	private ImageIcon lightOffIcon;
	private ImageIcon lightOnIcon;
	private JLabel lightLabel;


	/**
	 * Configures LightPanel interface and GUI displays.
	 *
	 * @param  LightPanel The light panel examined for visual display.
	 */
	public LightDisplay(LightPanel panel) {

		this.panel = panel;

		/**
 		* Configure a panel (the window with the switch buttons and icon)
 		*/
		JFrame frame = new JFrame();
		frame.setTitle(panel.toString());

		/**
 		* Determines the shape and size of panel
 		*/
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));

		/**
 		* Takes images from current folder to use as GUI
 		*/
		lightOffIcon = new ImageIcon("lightoff.gif");
		lightOnIcon = new ImageIcon("lighton.gif");

		lightLabel = new JLabel(lightOffIcon);
		lightLabel.setBorder(BorderFactory.createEtchedBorder());
		frame.getContentPane().add(lightLabel);

		/**
 		* Creates on and off buttons on the screen
 		*/
		JButton onButton = new JButton("ON");
		onButton.setMnemonic(KeyEvent.VK_N);
		onButton.setActionCommand("on");
		onButton.addActionListener(this);
		frame.getContentPane().add(onButton);

		JButton offButton = new JButton("OFF");
		offButton.setMnemonic(KeyEvent.VK_F);
		offButton.setActionCommand("off");
		offButton.addActionListener(this);
		frame.getContentPane().add(offButton);

		/**
		* Finishing touches on the UI
		*/
		frame.pack();
		frame.setVisible(true);

		start();
	}

	/**
	 * This function is called when a button is pressed. It changes a specified panel on or off depending on switch status.
	 *
	 * @param  ActionEvent carries information about which button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("on"))
			panel.switchOn();
		else
			panel.switchOff();
	}

	/**
	 * This function runs indefinitely, continually updating light status using panel information.
	 */
	public void run() {
		while (true) {
			if (panel.isOn())
				lightLabel.setIcon(lightOnIcon);
			else
				lightLabel.setIcon(lightOffIcon);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}
}