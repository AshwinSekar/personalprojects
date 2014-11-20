/*
ID: gaurjas1
LANG: JAVA
TASK: ride
*/
import java.io.*;

class ride {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    String comet = f.readLine();   
    String group = f.readLine();    
    int cometValue = 1;
    for(int i=0;i<comet.length();i++){
    	cometValue *= "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(comet.charAt(i))+1;
    }
    int groupValue = 1;
    for(int i=0;i<group.length();i++){
    	groupValue *= "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(group.charAt(i))+1;
    }
    out.println((cometValue%47==groupValue%47)?"GO":"STAY"); // output result
    f.close();
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}