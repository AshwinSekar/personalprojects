/*
ID: gaurjas1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class person {
	private int give;
	private int recieve;
	public person(){
		setGive(0);
		recieve = 0;
	}
	public int net(){
		return this.recieve-this.getGive();
	}
	public void setGive(int give) {
		this.give = give;
	}
	public int getGive() {
		return give;
	}
	public void distributeGifts(Hashtable<String, person> people, String[] toGive){
		if(toGive.length != 0){
		int per = (int)Math.floor(this.give/toGive.length);
		this.give-=this.give%toGive.length;
		this.recieve+=this.give%toGive.length;
		for(int i=0;i<toGive.length;i++){
			people.get(toGive[i]).recieve += per;
		}
	}
	}
}

class gift1 {
	
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
    int np = Integer.parseInt(f.readLine()); 
    Hashtable<String, person> people = new Hashtable<String, person>();
    for(int i=0;i<np;i++){
    	people.put(f.readLine(), new person());
    }
    for(int j=0;j<np;j++){
    	String currentPerson = f.readLine();
    	StringTokenizer st = new StringTokenizer(f.readLine());
    	people.get(currentPerson).setGive(Integer.parseInt(st.nextToken()));
    	String toGive[] = new String[Integer.parseInt(st.nextToken())];
    	for(int i=0;i<toGive.length;i++){
    		toGive[i]=f.readLine();
    	}
    	people.get(currentPerson).distributeGifts(people,toGive);
    	
    }
    f.close();
    BufferedReader s = new BufferedReader(new FileReader("gift1.in"));
    s.readLine();
    for(int i=0;i<np;i++){
    	String currentPerson = s.readLine();
    	out.println(currentPerson+" "+people.get(currentPerson).net());
    }
    s.close();
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
}