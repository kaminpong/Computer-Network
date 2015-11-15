import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private Thread threadReceive;
	private static Thread serverThread;
	
	private ServerSocket serverSocket;
	private Socket socket;
	public ArrayList<MiniServer> socketList = new ArrayList<MiniServer>();
	private InputStreamReader isr;
	private BufferedReader br;
	private PrintWriter pw;
	
	private int id =1;
	
	private MiniServer miniServer;
	
	public Server(ServerSocket serverSocket, GuessGameServer serverGUI){
		serverGUI.appendText("Your IP = "+serverSocket.getInetAddress().getHostAddress());
		serverGUI.appendText("Your Port = "+serverSocket.getLocalPort());
		serverGUI.setIPandPort(serverSocket.getInetAddress().getHostAddress(), serverSocket.getLocalPort());
		this.serverSocket = serverSocket;
		serverThread = new Thread(){
			public void run(){
				while(!this.isInterrupted()){
					try {
						System.out.println("Server is listening...");
						socket = Server.this.serverSocket.accept();
						System.out.println("Server is connected to a socket");
						miniServer = new MiniServer(socket);
						miniServer.id = id++;
						socketList.add(miniServer);
						serverGUI.appendText("Current # player:"+socketList.size());
						miniServer.server = Server.this;
						miniServer.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		serverThread.start();
	}
	
	public void closeServerThread(){
		serverThread.interrupt();
	}
}
