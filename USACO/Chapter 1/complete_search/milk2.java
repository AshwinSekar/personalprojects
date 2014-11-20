package complete_search;
/*
ID: gaurjas1
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;


class milk2 {
	
	static int longestTime;
	static int shortestTime;
	int begin;
	int end;
	public milk2(int b, int e) {
		begin = b;
		end = e;
	}
	private boolean isMilking(int time) {
		return (time >= this.begin) && (time < this.end);
	}
	public static boolean milkingStatus(milk2 cows[], int time) {
		boolean status = false;
		for(int i = 0; i < cows.length;i++) {
			status = cows[i].isMilking(time);
			if (status) {
				break;
			}
		}
		return status;
	}
  public static void main (String [] args) throws IOException {
    //Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
    //Scanner f = new Scanner(System.in);
    int numberOfCows = Integer.parseInt(f.readLine());
    StringTokenizer read;
    milk2[] cows = new milk2[numberOfCows];
    int begin;
    int end;
    int finalbegin = 0;
    int finalEnd = 0;
    
    for(int i = 0; i < numberOfCows; i++) {
    	read = new StringTokenizer(f.readLine());
    	begin = Integer.parseInt(read.nextToken());
    	end = Integer.parseInt(read.nextToken());
    	cows[i] = new milk2(begin, end);    	
    	if (end > finalEnd) {
    		finalEnd = end;
    	}
    	if (finalbegin > begin || finalbegin == 0)
    		finalbegin = begin;
    }
    System.out.println(finalbegin);
    milk2.longestTime = 0;
    milk2.shortestTime = 0;
    int currentLongestTime = 0;
    int currentShortestTime = 0;
    for(int j = finalbegin; j < finalEnd; j++) {
    	if (milk2.milkingStatus(cows, j)) {
    		currentLongestTime++;
    		currentShortestTime = 0;
    	} else {
    		currentShortestTime++;
    		currentLongestTime = 0;
    	}
    	if (currentLongestTime > milk2.longestTime) {
    		milk2.longestTime = currentLongestTime;
    	}
    	if (currentShortestTime > milk2.shortestTime) {
    		milk2.shortestTime = currentShortestTime;
    	}
    }
    //System.out.println(milk2.longestTime+1+" "+(milk2.shortestTime-1));
    out.println(milk2.longestTime+" "+milk2.shortestTime);
    out.close();  f.close();
  }
  
}