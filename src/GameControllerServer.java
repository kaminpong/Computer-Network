import java.util.ArrayList;
import java.util.Collections;


public class GameControllerServer {
private static GameControllerServer gameController = new GameControllerServer();
	
	static boolean gameStart = false;
	
	static String answer;
	
	static String feedback = "";
	
	int numberOfTries = 10;
	
	boolean correct = false; 
	
	static boolean gameEnd = false;
	
	GuessGameServer gameServer = GuessGameServer.getInstance();
	
	Client client;
	MiniServer miniServer;
	
	private GameControllerServer() {
		
	}
	
	public static GameControllerServer getInstance() {
		return gameController;
	}

	public void linkMiniServer(MiniServer miniServer) {
		this.miniServer = miniServer;
	}
	
	public boolean startTheGame() {
		if (!gameStart) {
			gameStart = true;
			generateAnswer();
			return true;
		} else {
			return false;
		}
	}
	
	public void generateAnswer() {
		ArrayList<Integer> numbers = new ArrayList<>();
	    
	    for(int i = 0; i < 10; i++){
	        numbers.add(i);
	    }
	    Collections.shuffle(numbers);

	    String result = "";
	    for(int i = 0; i < 4; i++){
	        result += numbers.get(i).toString();
	    }
	    
	    this.answer = result;
	    gameServer.appendText("Correct answer:"+this.answer);
	}
	
	public boolean checkAnswer(String clientGuess, int numberOfTries) {
		    
		    int x=0;
		    int y=0;
		    
		    for(int i = 0;i<4;i++){
		    	for(int j=0;j<4;j++){
		    		if(clientGuess.charAt(i)==answer.charAt(j)){
		    			if(i==j)x++;
		    			if(i!=j)y++;
		    		}	
		    	}
		    }
		    this.numberOfTries = --numberOfTries;
		    String feedback = x+"A"+y+"B"+"      " + this.numberOfTries + "tries left";
		    this.feedback = feedback;
		    System.out.println(feedback);
		  
		    if (x==4) {
		    	return true;
		    } else {
		    	return false;
		    }
	}
	
	public void checkAllClients() {
		ArrayList<MiniServer> socketList = miniServer.server.socketList;
		int finishedPlayer = 0;
		for (int i=0; i<socketList.size(); i++) {
			if(socketList.get(i).gameController.numberOfTries==0 || socketList.get(i).gameController.correct) {
				finishedPlayer++;
			}
		}
		if (finishedPlayer==socketList.size()) {
			endTheGame();
		}
	}
	
	public boolean compareScore(MiniServer a, MiniServer b) {
		if (a.gameController.numberOfTries<b.gameController.numberOfTries) {
			return true;
		} else {
			return false;
		}
	}
	
	public void endTheGame() {
		ArrayList<MiniServer> socketList = miniServer.server.socketList;
		ArrayList<MiniServer> correctPlayers = new ArrayList<MiniServer>();
		MiniServer winner = null;
		for (int i=0; i<socketList.size(); i++) {
			if(socketList.get(i).gameController.correct) {
				System.out.println("IN");
				correctPlayers.add(socketList.get(i));
			}
		}
		if (correctPlayers.size()>0) {
			for (int i=0; i<correctPlayers.size(); i++) {
				if (i==0) {
					winner = correctPlayers.get(i);
				} else {
					if (!compareScore(winner,correctPlayers.get(i))) {
						winner = correctPlayers.get(i);
					}
				}
			}
			winner.sendData("winner#");
			socketList.remove(winner);
			for (int i=0; i<socketList.size(); i++) {
				socketList.get(i).sendData("lose#");
			}
		} else {
			System.out.println("DRAW!!!");
		}
	}
	
}
