import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class BlackJackClient {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackJackClient window = new BlackJackClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BlackJackClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Money");
		splitPane.setLeftComponent(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		splitPane.setRightComponent(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnDeal = new JButton("DEAL");
		panel.add(btnDeal);
		
		JButton btnStand = new JButton("STAND");
		panel.add(btnStand);
		
		JButton btnHit = new JButton("HIT");
		panel.add(btnHit);
		
		JButton btnDouble = new JButton("DOUBLE");
		panel.add(btnDouble);
		
		JButton btnSplit = new JButton("SPLIT");
		panel.add(btnSplit);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		

		
		
		
		
	}

}
