package greedy_algorithms;
/*
ID: gaurjas1
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;


class barn1 {
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		//Scanner f = new Scanner(System.in);
		StringTokenizer in = new StringTokenizer(f.readLine());
		
		int maxBoard,totStalls,cow;
		int currentGap = 0,gapEnd = 0,biggestGap = 0, min = 200, max = 0;
		int answer = 0;
		
		maxBoard = Integer.parseInt(in.nextToken());
		totStalls = Integer.parseInt(in.nextToken());
		cow = Integer.parseInt(in.nextToken());
		int stalls[] = new int[totStalls];
		Arrays.fill(stalls, 1);
		
		for(int i = 0; i < cow;  i++) {
			int c = Integer.parseInt(f.readLine())-1;
			stalls[c] += 2;
			if(c < min) 
				min = c;
			if(c > max)
				max = c;
			
		}
		for(int i = 0; i < min; i++) {
			stalls[i] = 0;
		}
		for(int i = totStalls-1; i > max; i--) {
			stalls[i] = 0;
		}
	
		for(int board = 1; board < maxBoard; board++) {
			for(int j = min; j < max; j++) {
				if(stalls[j] == 1) {
					currentGap++;
				} else {
					currentGap = 0;
				}
				if(currentGap > biggestGap) {
					gapEnd = j;
					biggestGap = currentGap;
				}				
			}
			for(int j = gapEnd; j > gapEnd - biggestGap; j--) {
				stalls[j] = 0;
			}
			biggestGap = 0;
			currentGap = 0;
			gapEnd = 0;

		}
		
		for(int i = 0; i < totStalls; i++) {
			if(stalls[i] != 0) {
				answer++;
			}
		}
		out.println(answer);		
		out.close();  f.close();
	}
}