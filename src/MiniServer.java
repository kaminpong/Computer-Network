
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class MiniServer extends Thread{
	
	Socket socket;
	InputStreamReader isr;
	BufferedReader br;
	PrintWriter pw;
	
	public Server server;
	
	GameControllerServer gameController = GameControllerServer.getInstance();
	
	int id = 0;
	
	
	public MiniServer(Socket socket){
		gameController.linkMiniServer(this);
		this.socket = socket;
		try {
			isr = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(isr);
			pw = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			String receivedMSG = "";
			try {
				receivedMSG = br.readLine();
			} catch (IOException e) {
				//JOptionPane.showMessageDialog(gc.gameUI, e.getMessage(),e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			System.out.println("Server received msg : "+ receivedMSG);
			decipherData(receivedMSG);
		}
	}
	
	public void sendData(String data){
		pw.println(data);
		pw.flush();
	}
	public void decipherData(String data){
		
		System.out.println(data);
		
		String[] d = data.split("#");
		
		switch(d[0]){
			case "start":
				boolean start = gameController.startTheGame();
				if (start) {
					sendData("start#");
				} else {
					sendData("continue#");
				}
				break;
			case "submit":
				boolean check = gameController.checkAnswer(d[1],Integer.parseInt(d[2]));
				if (check) {
					System.out.println("IN");
					sendData("correct#");
					gameController.correct = true;
				} else {
					sendData("tryagain#"+gameController.feedback);
					
				}
				
				break;
			case "check":
				gameController.checkAllClients();
				break;
			default:
				break;
		}
	}
}
