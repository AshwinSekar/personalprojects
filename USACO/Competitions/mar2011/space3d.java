package mar2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class space3d {
	public static String floodRight(String st,int a,int b){
		int i = 0;
		st = st.substring(a);
		while((st.charAt(i)=='*')&&(i<b)){
			st = st.substring(0, i)+"."+(st+" ").substring(i+1);
			i++;
		
		}
		return st;
	}
	public static String floodLeft(String st,int a,int b){
		st = st.substring(0, b);
		int i = st.length()-1;
		while((st.charAt(i)=='*')&&(i>0)){
			st = (st+" ").substring(0, i)+"."+(st+" ").substring(i+1,(st+" ").length());
			i--;
			//System.out.println(i);
		}
		return st;
	}
  public static void main (String [] args) throws IOException {
	// Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("space3d.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("space3d.out")));
    int n = Integer.parseInt(f.readLine());
    String space[] = new String[n];
    for(int j=0;j<n;j++){
    	for(int i=0;i<n;i++){
        	space[j]=space[j]+" "+f.readLine();
        }
    	StringTokenizer st = new StringTokenizer(space[j]);
    	String y = st.nextToken();
    	System.out.println(y);
    	int x = y.indexOf('*');
    	y = floodLeft(y,0,x)+floodRight(y,x,y.length());
    	for(int i = 0;i<n;i++){
    		
    	}
    	space[j] = y + space[j].substring(n+1);
    }
    
    
    out.println(); // output result
    out.close();  f.close();                                // close the output file
    System.exit(0);                               // don't omit this!
  }
}