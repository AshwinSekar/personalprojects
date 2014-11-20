package mar2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: bfire
*/
import java.io.*;

class bfire {
	int id;
	int beenTouched;
	bfire(int i){
		this.id = i;
		beenTouched = 0;
	}
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    /*BufferedReader f = new BufferedReader(new FileReader("bfire.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bfire.out")));*/
    int n=78;//Integer.parseInt(f.readLine());
    bfire cows[] = new bfire[n+1];
    for(int i=1;i<n+1;i++){
    	cows[i]=new bfire(i);
    }
    int currentPos = 1;
    bfire temp = new bfire(0);
    cows[0]=cows[1];
    cows[currentPos]=temp;
    int winner=0;
    
    boolean done = false;
    bfire switcher = new bfire(0);
	for(int i=0;i<n;i++){
		System.out.print(cows[i].id+",");
	}
	System.out.println();
	while (!done){
    	switcher=cows[((currentPos+cows[0].id)%n==0)?n:((currentPos+cows[0].id)%n)];
    	switcher.beenTouched++;
    	
    	if (switcher.id == 0||switcher.beenTouched==2){
    		winner = cows[0].id;
    		done = true;
    	}
    	cows[((currentPos+cows[0].id)%n==0)?n:((currentPos+cows[0].id)%n)]=cows[0];
    	currentPos=((currentPos+cows[0].id)%n==0)?n:((currentPos+cows[0].id)%n);
    	cows[0]=switcher;
    	for(int i=0;i<n;i++){
    		System.out.print(cows[i].id+",");
    	}
    	System.out.println();
    	
    }
    System.out.println(winner); // output result
    System.out.close();                                 // close the output file
    System.exit(0);                               // don't omit this!
  }
}