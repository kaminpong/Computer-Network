import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GuessClient extends JPanel implements ActionListener{
	
	private JLabel lblResult;
	private JLabel lblNewLabel;
	private JPanel inputsPanel = new JPanel();
	private JPanel resultsPanel = new JPanel();
	JPanel numbersPanel = new JPanel();
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ArrayList<JButton> answers = new ArrayList<JButton>();
	JButton deleteButton = new JButton("Delete");
	JButton submitButton = new JButton("Submit");
	JLabel moneyLabel = new JLabel("Money:");
	JLabel amountOfMoney = new JLabel("0");
	JLabel resultLabel = new JLabel("Result:");
	JLabel result = new JLabel("");
	int currentInput = 0;
	
	GameControllerClient gameController = GameControllerClient.getInstance();

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
		amountOfMoney.setText(Integer.toString(gameController.amountOfMoney));
		numbersPanel.setLayout(new GridLayout(2,5));
		for (int i=0; i<10; i++) {
			buttons.add(new JButton(Integer.toString(i)));
			numbersPanel.add(buttons.get(i));
			buttons.get(i).addActionListener(this);
		}
		numbersPanel.setPreferredSize(new Dimension(1000,400));
		
		inputsPanel.setLayout(new GridLayout(1,5));
		
		for (int i=0; i<4; i++) {
			answers.add(new JButton(""));
			inputsPanel.add(answers.get(i));
			answers.get(i).setEnabled(false);
		}
		inputsPanel.add(deleteButton);
		deleteButton.addActionListener(this);
		submitButton.addActionListener(this);
		
		resultsPanel.setLayout(new GridLayout(2,2));
		resultsPanel.add(moneyLabel);
		resultsPanel.add(amountOfMoney);
		resultsPanel.add(resultLabel);
		resultsPanel.add(result);
		
		
		this.setLayout(new BorderLayout());
		
		this.add(inputsPanel,BorderLayout.NORTH);
		this.add(numbersPanel,BorderLayout.CENTER);
		this.add(submitButton,BorderLayout.SOUTH);
		this.add(resultsPanel,BorderLayout.EAST);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i=0; i<buttons.size(); i++) {
			if (e.getSource()==buttons.get(i)) {
				if (currentInput<4) {
					answers.get(currentInput).setText(buttons.get(i).getText());
					buttons.get(i).setEnabled(false);
					currentInput++;
				}
				
			}
		}
		
		if (e.getSource()==deleteButton) {
			for (int i=0; i<answers.size(); i++) {
				answers.get(i).setText("");
			}
			for (int i=0; i<buttons.size(); i++) {
				buttons.get(i).setEnabled(true);
			}
			currentInput = 0;
		}
		
		if (e.getSource()==submitButton) {
			if (gameController.numberOfTries>0) {
				if (checkInputAnswer()) {
					String answer = "";
					for (int i=0; i<answers.size(); i++) {
						answer+=answers.get(i).getText();
					}
					System.out.println(answer);
					gameController.client.sendData("submit#"+answer+"#"+gameController.numberOfTries);
				} else {
					JOptionPane.showMessageDialog(this, "Incorrect inputs.");
				}
				
			} else {
				JOptionPane.showMessageDialog(this, "Gameover");
			}
			
		}
		
		
	}
	
	public boolean checkInputAnswer() {
		boolean result = false;
		for (int i=0; i<answers.size(); i++) {
			if (answers.get(i).getText().length()>0) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

}
