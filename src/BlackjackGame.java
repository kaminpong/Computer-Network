import java.util.ArrayList;
import java.util.Collections;

public class BlackjackGame {
	
	//TESTEST
	
	public static void main(String[] args) {
		
		int numberofclient,deals,value,bet,money;
		boolean stand=false;
		boolean bust=false;
		String client1 = "";
		int client1value=0;
		int client12value=0;
		int index=0;
		
		//Generate card deck
		
	    ArrayList<String> cards = new ArrayList<>();
	    
	    for(int i=0;i<16;i++){
	    	cards.add("10");
	    }
	    for(int i=0;i<4;i++){
	    	for(int j = 0;j<9;j++){
	    		cards.add(""+(j+1));
	    	}
	    }
	    

	    //Shuffle card
	    Collections.shuffle(cards);
	    
	    //Client insert money he/she wants to bet in text field then click deal
	    //get money from socket
	    //deal cards to all the clients using socket when all the clients have clicked deal
	    //if(numberofclient==deals) then deal card
	    //if any client gets blackjack then skip them
	    //client 1 one chooses to stand/hit/double/split(if they have 2 of the same cards)
	    //other clients do the same
	    
	    client1=cards.get(index)+" and ";
	    client1value = client1value + Integer.parseInt(cards.get(index++));
	    while(!bust){
	    	if(client1value==21)break;
	    	if(client1value>21)break;
	    	//receive command from client
	    	//value = 
	    	//if(client1 stand)break;
	    	/*if(client1 hit){
	    	 *  client1=cards.get(index)+" and ";
	    	 *  client1value = client1value+Integer.parseInt(cards.get(index++));
	    	 }*/
	    	
	    	/*if(client1 double){
	    	 * 	client1=cards.get(index)+" and ";
	    	 * 	client1value=client1value+Integer.parseInt(cards.get(index++));
	    	 * 	money = money*2;
	    	 * }
	    	 * 
	    	 *if(client1 split){
	    	 *	
	    	 *}
	    	 */
	    }
	    
	}
}
