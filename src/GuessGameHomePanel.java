import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class GuessGameHomePanel extends JPanel implements ActionListener{
	
	GuessGameMainPanel mainPanel = GuessGameMainPanel.getInstance();
	
	//GuessGameServer serverMain = GuessGameServer.getInstance();
	
	JButton playButton = new JButton("Play"), exitButton;
	
	final String ipAddress = "10.20.14.120";
	
	public GuessGameHomePanel() {
		exitButton = new JButton("Exit");
		playButton.addActionListener(this);
		exitButton.addActionListener(this);
		this.setPreferredSize(new Dimension(1000,800));
		this.setLayout(new GridLayout(1,3));
		this.add(playButton);
		this.add(new JPanel());
		this.add(exitButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==playButton) {
			Client client = new Client(ipAddress, 2000);
		}
		else if (e.getSource()==exitButton) {
			System.exit(0);
		}
	}

}
