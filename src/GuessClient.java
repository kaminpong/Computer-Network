import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuessClient extends JPanel{

	private JTextField textField;
	private JLabel lblResult;
	private JLabel lblNewLabel;


	/**
	 * Create the application.
	 */
	public GuessClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		textField = new JTextField();
		textField.setColumns(10);
		
		this.add(textField, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Submit");
		this.add(btnNewButton, BorderLayout.CENTER);
		
		lblResult = new JLabel("Result: ");
		this.add(lblResult, BorderLayout.SOUTH);
		
		lblNewLabel = new JLabel("Money: ");
		this.add(lblNewLabel, BorderLayout.EAST);
		
		
	}

}
