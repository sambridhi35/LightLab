import java.awt.event.*;
import javax.swing.*;

public class BitDisplay implements ActionListener, BitListener {
	private BitHandler handler;
	private JTextField receiveField;
	private JTextField sendField;

	/**
	 * Initializes the class by taking in a BitHandler class and initializing windows/fields.
	 */
	public BitDisplay(BitHandler handler) {
		this.handler = handler;

		/**
		 * makes the frame to generate the interface
		 */
		JFrame frame = new JFrame(handler.toString());
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

		/**
		 * makes a text field to type in the ones and zeroes
		 */
		receiveField = new JTextField(20);
		receiveField.setEditable(false);
		frame.getContentPane().add(receiveField);

		sendField = new JTextField(20);
		sendField.addActionListener(this);
		frame.getContentPane().add(sendField);

		/**
		 * finishing touches on GUI
		 */
		frame.pack();
		frame.setVisible(true);
		handler.setListener(this);
	}


	/**
	 * takes input from the field and makes a new thread to send the broadcast through the bithandler
	 */
	public void actionPerformed(ActionEvent e) {
		new Thread() {
			public void run() {
				//receiveField.setText("");

				try{
				handler.broadcast(sendField.getText());
				// System.out.println("actionPerformed: done sending " + sendField.getText());
				}catch(Exception e){
					receiveField.setText("Collision detected");
				}
			}
		}.start();
		sendField.selectAll();
	}

	/**
	 * shows what bits are comin in
	 */
	public void bitsReceived(BitHandler h, String bits) {
		receiveField.setText(bits);
	}
}