import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JOptionPane;


public class Client{

	private Socket socket;
	private InputStreamReader isr;
	private BufferedReader br;
	private PrintWriter pw;
	
	private Thread thread;
	
	private String serverName;
	
	GuessGameMainPanel mainPanel = GuessGameMainPanel.getInstance();
	
	GameControllerClient gameController = GameControllerClient.getInstance();
	
	GuessClient clientGUI = new GuessClient();
	
	public Client(String IP, int port) {
		gameController.linkClient(this);
		try {
			socket = new Socket();
			InetAddress address = InetAddress.getByName(IP);
			socket.connect(new InetSocketAddress(IP,port), 300);
			isr = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(isr);
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error");
			JOptionPane.showMessageDialog(null,
					"Could not connect to Socket with IP : "+IP,
					"Connect Failed",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		mainPanel.changeState(clientGUI);
		
		thread = new Thread() {
			public void run() {
				sendData("start#");
				while (true) {
					String receivedMSG = "";
					try {
						receivedMSG = br.readLine();
					} catch (IOException e) {
						System.exit(0);
					}
					System.out.println("Client received msg : " + receivedMSG);
					
					decipherData(receivedMSG);
				}
			}
		};
		thread.start();
	}
	
	public void sendData(String data) {
		pw.println(data);
		pw.flush();
	}
	
	public void decipherData(String data) {
		System.out.println("Client received: "+data);
	//	JOptionPane.showMessageDialog(mainPanel, "Client received: "+data,"DEBUG",JOptionPane.INFORMATION_MESSAGE);
		String[] d = data.split("#");
		
		switch(d[0]) {
			case "start":
				break;
			case "continue":
				break;
			case "result":
				break;
			case "correct":
				gameController.waitForEndGame();
				break;
			case "tryagain":
				JOptionPane.showMessageDialog(mainPanel, "Try Again");
				gameController.updateResult(d[1]);
				break;
			case "lose":
				JOptionPane.showMessageDialog(mainPanel, "You Lose!\nYou owe me 10 NTD");
				break;
			case "winner":
				JOptionPane.showMessageDialog(mainPanel, "You're the Winner!\n You get "+(Integer.parseInt(d[1])-1)*10+" NTD");
				break;
			case "draw":		// >1 player get the highest score
				JOptionPane.showMessageDialog(mainPanel, "No winner");
				break;
			default:
				break;
		}
	}
}
