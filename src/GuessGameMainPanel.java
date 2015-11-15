import java.awt.BorderLayout;

import javax.swing.JPanel;


public class GuessGameMainPanel extends JPanel{
	
	private static GuessGameMainPanel mainPanel = new GuessGameMainPanel();
	
	
	private GuessGameMainPanel() {
		this.setLayout(new BorderLayout());
	}
	
	public static GuessGameMainPanel getInstance() {
		return mainPanel;
	}
	
	
	public void changeState(JPanel panel) {
		this.setVisible(false);
		this.removeAll();
		this.add(panel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	

}
