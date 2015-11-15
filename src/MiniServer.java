
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
	
	
	int id = 0;
	
	
	public MiniServer(Socket socket){
		//gameController.linkMiniServer(this);
		//gameController.setMyTurn(true);
		//gameController.isServer = true;
		//gameController.firstPlayer.setPlayerName(playerName);
		this.socket = socket;
		//gc = MainFrame.gc;
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
			System.out.println("Server revceived msg : "+ receivedMSG);
			decipherData(receivedMSG);
		}
	}
	
	public void sendData(String data){
		pw.println(data);
		pw.flush();
	}
	public void decipherData(String data){
		
		String[] d = data.split("#");
		for (int i=0; i<d.length; i++) {
			System.out.println(d[i]);
		}
		
		switch(d[0]){
			
		}
	}
	public MiniServer getAnother(){
		for(int i = 0 ;i<this.server.socketList.size();i++){
			MiniServer mn = this.server.socketList.get(i);
			if(mn != this)return mn;
		
		}
		return null;
	}
}
