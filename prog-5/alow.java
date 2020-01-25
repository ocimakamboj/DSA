import java.io.*;
import java.util.*;

public class alow{
	public static int[] getContent(String s){
        String[] detailsString = s.split(",");
        int size = detailsString.length;
        int[] details = new int[size];
        for(int i = 0; i < size; i++){
        	details[i] = Integer.parseInt(detailsString[i]);
        }
        return(details);
    }

    public static void main(String args[]){
    	String line;
    	int[] details;
    	Graph varGraph;
    	try{
            BufferedReader BR = new BufferedReader(new FileReader("TestCase-1.txt"));

            line = BR.readLine();
            int size = Integer.parseInt(line);
            varGraph = new Graph(size);

            for(int i = 1; i <= size; i++){
            	line = BR.readLine();
            	try{
	            	details = getContent(line);
	            	varGraph.setEdges(i, details);
	            }catch(Exception e){}//catches exception if it encounters a -
            }

            
            System.out.println(varGraph.OneWayConnected());
            
            BR.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}