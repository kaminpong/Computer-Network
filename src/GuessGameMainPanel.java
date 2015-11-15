import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class GuessGameMainPanel extends JPanel implements ActionListener{
	
	JButton playButton, exitButton;
	
	public GuessGameMainPanel() {
		playButton = new JButton("Play");
		exitButton = new JButton("Exit");
		playButton.addActionListener(this);
		exitButton.addActionListener(this);
		this.setLayout(new GridLayout(1,3));
		this.add(playButton);
		this.add(new JPanel());
		this.add(exitButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==playButton) {
			new GuessClient();
		}
		else if (e.getSource()==exitButton) {
			System.exit(0);
		}
	}

}
