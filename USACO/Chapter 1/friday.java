/*
ID: gaurjas1
LANG: JAVA
TASK: friday
*/
import java.io.*;


class friday {
	
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("friday.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    int n = Integer.parseInt(f.readLine());
    int days[] = {0,0,0,0,0,0,0};
    int months[]= {31,28,31,30,31,30,31,31,30,31,30,31};
    int currentDay = 0;
    for (int i=0;i<n;i++){
    	for(int j=0;j<12;j++){
    		//System.out.println(1990+i+" "+j+" "+currentDay);
    		days[currentDay]++;
    		//System.out.println(days[0]+" "+days[1]+" "+days[2]+" "+days[3]+" "+days[4]+" "+days[5]+" "+days[6]);
    		currentDay = (currentDay+months[j]+((j==1&&(((1900+i)%4==0)&&((((1900+i)%100)!=0)||(((1900+i)%400)==0)))?1:0)))%7;    		
    	}
    }
    out.println(days[0]+" "+days[1]+" "+days[2]+" "+days[3]+" "+days[4]+" "+days[5]+" "+days[6]);
    f.close();
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}