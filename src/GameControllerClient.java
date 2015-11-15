import java.awt.BorderLayout;

import javax.swing.JLabel;


public class GameControllerClient {
	private static GameControllerClient gameController = new GameControllerClient();
	
	int amountOfMoney = 100;
	
	int numberOfTries = 10;
	
	String feedback = "";
	
	boolean gameOver = false;
	
	boolean correct = false;
	
	Client client;
	MiniServer miniServer;
	
	private GameControllerClient() {
		
	}
	
	public static GameControllerClient getInstance() {
		return gameController;
	}
	
	public void linkClient(Client client) {
		this.client = client;
	}
	
	public void linkMiniServer(MiniServer miniServer) {
		this.miniServer = miniServer;
	}
	
	public void waitForEndGame() {
		this.correct = true;
		for (int i=0; i<client.clientGUI.buttons.size(); i++) {
			client.clientGUI.buttons.get(i).setEnabled(false);
		}
		client.clientGUI.deleteButton.setEnabled(false);
		client.clientGUI.submitButton.setEnabled(false);
		client.clientGUI.repaint();
		this.client.sendData("check#");
	}
	
	public void gameOver() {
		gameOver = true;
		for (int i=0; i<client.clientGUI.buttons.size(); i++) {
			client.clientGUI.buttons.get(i).setEnabled(false);
		}
		client.clientGUI.deleteButton.setEnabled(false);
		client.clientGUI.submitButton.setEnabled(false);
	}
	
	public void updateResult(String feedback) {
		int tries = Integer.parseInt(feedback.substring(feedback.indexOf('t')-1, feedback.indexOf('t')));
		this.numberOfTries = tries;
		if (this.numberOfTries==0) {
			gameOver();
		}
		client.clientGUI.result.setText(feedback);
		
		client.clientGUI.repaint();
	}
}
