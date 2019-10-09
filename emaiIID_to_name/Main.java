package emaiIID_to_name;

import java.net.*;
import java.io.*;

import java.util.regex.*;  

public class Main {
	
	public static void main(String[] args) throws IOException {
		
			String input = new String(inputFromConsole("Enter Email: "));
					
			String username = new String(splitMethod(input, "@", 0)); 
			
			String urlstring = new String(appStrToAddress(username, "https://www.ecs.soton.ac.uk/people/"));
			
			URL urlvar = new URL(urlStrToURL(urlstring), "");
			
			String nameline = new String(urlBufferReader(urlvar));
			
			String name = new String(getBetweenString(nameline, '>', '|'));
					
			System.out.println("Name: "+ name+"\n(Note: invalid input returns \"Name:People\" ¯\\_(ツ)_/¯");
		
	}
	
	public static String inputFromConsole(String messagestr) {
		
		String outline = null;
		
	    System.out.println(messagestr);

		try {
			
		    InputStreamReader streamReader = new InputStreamReader(System.in);
		    BufferedReader bufferedReader = new BufferedReader(streamReader);
		    outline = bufferedReader.readLine();
		    
		} catch (IOException e) {
			
		    e.printStackTrace();
		    
		}
		
		return outline;
	}
	
	public static String splitMethod(String inputstr, String splitchar, int argtoreturn) {
		
		String[] splitarray = inputstr.split(splitchar, 5);
		
		return (splitarray[argtoreturn]);
		
	}
	
	public static String appStrToAddress(String inputstr, String webaddress) {
		
		StringBuilder strbuildervar = new StringBuilder();
		
        strbuildervar.append(webaddress);
        strbuildervar.append(inputstr);
        
        return (strbuildervar.toString());
	}
	
	public static URL urlStrToURL(String inputurlstr) throws MalformedURLException {

        URL newURL = null;
		newURL = new URL(inputurlstr);		
		
		return newURL;
		
	}
	
	public static String urlBufferReader(URL inputurl) throws IOException {
		   
		BufferedReader in = new BufferedReader(new InputStreamReader(inputurl.openStream())); 
		String line;
		    
		while ((line = in.readLine()) != null) {
		    	
		    if (Pattern.matches(".*<title>.*</title>",line)){
		    	return line;
		    }		    			    	
		}
		    
		in.close();
		    
		return null;
		
	}
	
	public static String getBetweenString(String inputstr, char startchar, char endchar) {
		
		Boolean startflag = false;
		StringBuilder strbuildervar = new StringBuilder();

		for (int i = 0; i < inputstr.length(); i++){
			
		    char c = inputstr.charAt(i);
		    
		    if (startflag==false) {
		    	
		    	if (c == startchar) {
		    		
		    		startflag=true;
		    		
		    	}
		    	
		    } else if (c != endchar) {
		    	
		        strbuildervar.append(Character.toString(c));
		        
		    } else {
		    	
		    	return (strbuildervar.toString());
		    	
		    }		    
		}
		return null;
	}
}
