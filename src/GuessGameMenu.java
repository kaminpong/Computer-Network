import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class GuessGameMenu {

	private JFrame frame;
	
	GuessGameMainPanel mainPanel = GuessGameMainPanel.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String [] args) {
		new GuessGameMenu();
	}

	/**
	 * Create the application.
	 */
	public GuessGameMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		mainPanel.add(new GuessGameHomePanel());
		frame.add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
