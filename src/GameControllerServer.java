import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;


public class GameControllerServer {
	private static GameControllerServer gameController = new GameControllerServer();

	static boolean gameStart = false;

	static String answer;

	static String feedback = "";

	//int numberOfTries = 10;



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

	public boolean checkAnswer(String clientGuess, int numberOfTries, MiniServer ms) {

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
		ms.numberOfTries--;
		String feedback = x+"A"+y+"B"+"      " + ms.numberOfTries + "tries left";
		GameControllerServer.feedback = feedback;
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
			if(socketList.get(i).numberOfTries==0 || socketList.get(i).correct) {
				finishedPlayer++;
			}
		}
		if (finishedPlayer==socketList.size()) {
			endTheGame();
		}
	}

	public boolean compareScore(MiniServer a, MiniServer b) {
		if (a.numberOfTries<b.numberOfTries) {
			return true;
		} else {
			return false;
		}
	}

	public void endTheGame() {
		ArrayList<MiniServer> socketList = miniServer.server.socketList;
		
		if(socketList==null||socketList.isEmpty()){
			System.err.println("socketList is null or empty");
			return;
		}
		if(socketList.size()==1){
			System.err.println("Only one player on server, no need to declare winner/loser");
			return;
		}
		int[] arrayOfTries = new int[socketList.size()];				//number of tries by every player, -1 if not finish
		for(int i = 0; i<arrayOfTries.length;i++){
			MiniServer ms = socketList.get(i);
			if(ms.correct){
				arrayOfTries[i] = ms.numberOfTries;
			}else{
				ms.numberOfTries = -1;
				arrayOfTries[i] = -1;
			}		
		}

		Arrays.sort(arrayOfTries);
		
		int highestScore = arrayOfTries[arrayOfTries.length-1];
		//JOptionPane.showMessageDialog(null, "arrayOfTries: "+Arrays.toString(arrayOfTries)+" highest: "+highestScore);
		if(arrayOfTries[arrayOfTries.length-2] == highestScore){		//if second place also get the same score as first place, declare no winner
			for (int i=0; i<socketList.size(); i++){
				socketList.get(i).sendData("draw#");
			} 		//TODO, REQUEST REPLAY IF NO WINNER?
		}else{															//else normal win/lose notification
			for (int i=0; i<socketList.size(); i++) {
				if(socketList.get(i).numberOfTries==highestScore){
					socketList.get(i).sendData("winner#"+socketList.size());
				} 
				else socketList.get(i).sendData("lose#");
			}
		}	
	}
}