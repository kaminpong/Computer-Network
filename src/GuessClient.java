import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class GuessClient {

	private JFrame frame;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
		lblResult = new JLabel("Result: ");
		frame.getContentPane().add(lblResult, BorderLayout.SOUTH);
		
		lblNewLabel = new JLabel("Money: ");
		frame.getContentPane().add(lblNewLabel, BorderLayout.EAST);
		
		frame.setVisible(true);
		
	}

}
