package mar2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: stringe
*/
import java.io.*;
import java.util.*;

class stringe {
	int n;
	int c;
	String s;
	
	stringe(String n,String c,String s){
		this.n = Integer.parseInt(n);
		this.c = Integer.parseInt(c);
		this.s = s;
	}
	
	public void evaluate(){
		for(int i = 0;i<c;i++){
			s = s.substring(n)+s;
		}
	}
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("stringe.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stringe.out")));
    int z = Integer.parseInt(f.readLine());
    stringe cows[] = new stringe[z];
    for(int i=0;i<z;i++){
    	 StringTokenizer st = new StringTokenizer(f.readLine());
    	 cows[i]= new stringe(st.nextToken(),st.nextToken(),st.nextToken());
    	 cows[i].evaluate();
    	 out.println(cows[i].s);
    }
    //out.println(); // output result
    out.close();  f.close();                                // close the output file
    System.exit(0);                               // don't omit this!
  }
}