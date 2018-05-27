import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static void application() {
		String s="";
		boolean stop=false;
		
		while(!stop) {
			
			try{
	            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	            s = bufferRead.readLine();

	            System.out.println(s);
	        }
	        catch(IOException e)
	        {
	            e.printStackTrace();
	        }
			
			String[] args = s.split(" ",-1); 
			
			
	    	Interpreteur interpreteur = new Interpreteur(args);
	    	if(interpreteur.interprete()) {
	    		stop=true;
	    	}
			
			
		}
	}
	
    public static void main (String[] args){
    	application();
    }
}
