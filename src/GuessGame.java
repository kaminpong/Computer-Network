import java.util.ArrayList;
import java.util.Collections;

public class GuessGame {
	public static void main(String[] args) {
		
		int x,y,count;
		count=10;
		
		//Generate the answer
		
	    ArrayList<Integer> numbers = new ArrayList<>();
	    
	    for(int i = 0; i < 10; i++){
	        numbers.add(i);
	    }
	    Collections.shuffle(numbers);

	    String result = "";
	    for(int i = 0; i < 4; i++){
	        result += numbers.get(i).toString();
	    }
	    System.out.println(result);
	    
	    //Get client's guess from socket
	    
	    String clientguess="";
	    
	    x=0;
	    y=0;
	    
	    for(int i = 0;i<4;i++){
	    	for(int j=0;j<4;j++){
	    		if(clientguess.charAt(i)==result.charAt(j)){
	    			if(i==j)x++;
	    			if(i!=j)y++;
	    		}	
	    	}
	    }
	    
	    String feedback = x+"A"+y+"B"+"      " + count-- + "tries left";
	    
	    //then send feedback back to client
	    

	    
	}
	
	

}
