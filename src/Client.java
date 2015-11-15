import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class Client{

	private Socket socket;
	private InputStreamReader isr;
	private BufferedReader br;
	private PrintWriter pw;
	
	private Thread thread;
	
	private String serverName;
	/*MainPanel mainPanel = MainPanel.getInstance();
	NewGameController gameController = NewGameController.getInstance();
	
	private int id=0;
	
	String playerName;
	
	public Client(String IP, int port, String playerName){
		gameController.linkClient(this);
		gameController.isServer = false;
		gameController.secondPlayer.setPlayerName(playerName);
		gameController.setMyTurn(false);
		this.playerName = playerName;
		try {
			socket = new Socket();
			InetAddress addr = InetAddress.getByName(IP);
			System.out.println("HostAddress = "+addr.getHostAddress());
			String hostName = addr.getHostName();
			socket.connect(new InetSocketAddress(IP,port), 300);
			isr = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(isr);
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			JOptionPane.showMessageDialog(null,
					"Could not connect to Socket with IP : "+IP,
					"Connect Failed",
					JOptionPane.ERROR_MESSAGE);
			return;
			//e.printStackTrace();
		}
		thread = new Thread(){
			public void run(){
				sendData("1#"+Client.this.playerName);
				while(true){
					String receivedMSG = "";
				//	System.out.println("Client Ready to receive message");
					try {
						receivedMSG = br.readLine();
					} catch (IOException e) {
						//JOptionPane.showMessageDialog(gc.gameUI, e.getMessage(),e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}
					System.out.println("Client received msg : " + receivedMSG);
					
					decipherData(receivedMSG);
					//serverName = receivedMSG;
				}
			}
		};
		//gc.GameStateUpdate(gc.gamestate.GAME_PLAYING);
		thread.start();
	}
	
	public void sendData(String data){
		pw.println(data);
		pw.flush();
	}
	
	public void decipherData(String data){
		
		String[] d = data.split("#");	
		
		switch(d[0]){
			case "1":	//CLIENT TELL SERVER THEIR NAME
				gameController.secondPlayer.setPlayerName(d[1]);
				//gameController.serverID = id;
				sendData("2"+"#"+gameController.firstPlayer.getPlayerName());// flow e - send type 2
				break;
			case "2":	//SERVER REMOTELY INITIALIZE CLIENT'S GAME
				System.out.println("IN");
				gameController.firstPlayer.setPlayerName(d[1]);//flow
				gameController.setBackgroundNum(Integer.parseInt(d[3]));
				gameController.boardGame.setBackgroundNum(Integer.parseInt(d[3]));
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						mainPanel.changeState(new NewGameGUI());
					}
					
				});
				break;
			case "3": //copy cards
				ArrayList<String> allCards = new ArrayList<String>();
				ArrayList<Integer> allCosts = new ArrayList<Integer>();
				for (int i=1; i<=30; i++) {
					allCards.add(d[i]);
				}
				for (int i=31; i<=60; i++) {
					allCosts.add(Integer.parseInt(d[i]));
				}
				gameController.setCards(allCards,allCosts);
				break;
			case "4":
				Card card = gameController.cards.get(Integer.parseInt(d[1]));
				card.open();
				gameController.choosedCard(card);
				if (gameController.choosedCards.size()!=2) {
					gameController.myTurn = true;
				}
				gameController.firstPlayer.setPlayerTurn(true);
				gameController.secondPlayer.setPlayerTurn(false);
				if (gameController.choosedCards.size()==1) 
					gameController.countdownTimer.start();
				break;
				
			case "5":
				boolean result = gameController.checkMatch();
				if (result) {
					gameController.myTurn = false;
					this.sendData("7#");
				} else {
					gameController.myTurn = true;
				}
				gameController.firstPlayer.setScore(Integer.parseInt(d[1]));
				break;
				
			case "6":
				gameController.myTurn = true;
				gameController.firstPlayer.setPlayerTurn(true);
				gameController.secondPlayer.setPlayerTurn(false);
				gameController.countdownTimer.start();
				break;
				
			case "7":
				gameController.myTurn= true;
				break;
				
			case "8":
				gameController.checkEndGame();
				break;
				
			default:
				System.err.println("Unknown data type");
				break;
		}
	}*/
}
