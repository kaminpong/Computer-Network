import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class GuessGameServer extends JPanel implements ActionListener{
	
	GuessGameMainPanel mainPanel = GuessGameMainPanel.getInstance();
	JPanel historyLogPanel = new JPanel();
	JPanel buttonsPanel = new JPanel();
	JTextPane historyLog = new JTextPane();
	JButton reset = new JButton("Reset");
	JButton terminate = new JButton("Terminate");
	String playerName;
	InetAddress address;
	String hostAddress;
	String hostName;
	ServerSocket serverSocket;
	Server server;
	String ipAddress;
	int port;
	
	
	final public static int PORT = 2000;
	
	private static GuessGameServer serverMain = new GuessGameServer();
	
	private GuessGameServer() {
		historyLog.setForeground(Color.WHITE);
		historyLog.setBackground(Color.BLACK);
		historyLog.setEditable(false);
		historyLog.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 28));
		historyLogPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		historyLogPanel.setBackground(Color.BLACK);
		historyLogPanel.setLayout(new BorderLayout());
		historyLogPanel.add(historyLog, BorderLayout.CENTER);
		
		reset.setBackground(Color.BLACK);
		reset.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		reset.setForeground(Color.WHITE);
		reset.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
		reset.setPreferredSize(new Dimension(160,24));
		reset.setFocusPainted(false);
		reset.addActionListener(this);
		terminate.setBackground(Color.BLACK);
		terminate.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		terminate.setForeground(Color.WHITE);
		terminate.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
		terminate.setPreferredSize(new Dimension(160,24));
		terminate.setFocusPainted(false);
		terminate.addActionListener(this);
		buttonsPanel.setBackground(Color.BLACK);
		buttonsPanel.setPreferredSize(new Dimension(300,800));
		buttonsPanel.setLayout(new GridBagLayout());
		GridBagConstraints cb = new GridBagConstraints();
		cb.gridx = 0;
		cb.gridy = 0;
		cb.ipady = 40;
		
		buttonsPanel.add(reset,cb);
		cb.gridy = 1;
		cb.insets = new Insets(100,0,0,0);
		buttonsPanel.add(terminate,cb);
		
		historyLog.setText("Server is running...");
		
		this.setLayout(new BorderLayout());
		this.add(historyLogPanel, BorderLayout.CENTER);
		this.add(buttonsPanel, BorderLayout.EAST);
		this.createServer();
	}
	
	public static GuessGameServer getInstance() {
		return serverMain;
	}
	
	public void createServer() {
		try {
			this.address = InetAddress.getLocalHost();
			System.out.println(this.address);
			this.hostAddress = address.getHostAddress();
			this.hostName = address.getHostName();
			serverSocket = new ServerSocket(PORT,50,this.address);
			this.server = new Server(serverSocket,this);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setIPandPort(String ip, int port) {
		this.ipAddress = ip;
		this.port = port;
	}
	public void appendText(String text) {
		String log = this.historyLog.getText()+"\n";
		log += text;
		this.historyLog.setText(log);
		this.historyLogPanel.repaint();
	}
	
	public static void main(String [] args) {
		JFrame frame = new JFrame();
		frame.add(serverMain);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==reset) {
			
		}
		if (e.getSource()==terminate) {
			
		}
	}
	
}
