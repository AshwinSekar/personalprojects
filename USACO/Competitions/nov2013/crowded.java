package nov2013;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class crowded {
	static ArrayList<cow> cows = new ArrayList<cow>();
	static int D;
	static int N;
	
	static class cow implements Comparable<cow>{ 
		int pos;
		int height;
		int crowdedLeft;
		int crowdedRight;
		
		public cow(int x, int h) {
			pos = x;
			height = h;
			crowdedLeft = -1;
			crowdedRight = -1;
		}

		@Override
		public int compareTo(cow o) {
			return pos - o.pos;
		}
		
		@Override
		public String toString() {
			return "Pos:" + pos + " Height:" + height + " CrowdedL:" + crowdedLeft + " CrowdedR:" + crowdedRight;
		}
	}
	
	public static void crowdLeft(int i, int start) {
		//System.out.println(i + " " + start);
		if(cows.get(i).pos - cows.get(start).pos > D) {
			cows.get(i).crowdedLeft = -2;
			return;
		}
		if(cows.get(start).height >= 2*cows.get(i).height) {
			cows.get(i).crowdedLeft = start;
			return;
		}
		if(cows.get(start).height <= cows.get(i).height) {
			if(cows.get(start).crowdedLeft == -2) {
				cows.get(i).crowdedLeft = -2;
				return;
			} else {
				crowdLeft(i, cows.get(start).crowdedLeft);
				return;
			}
		}
		if(start - 1 < 0) {
			cows.get(i).crowdedLeft = -2;
			return;
		} 
		crowdLeft(i, start-1);
		return;
	}
	
	public static void crowdRight(int i, int start) {
		//System.out.println(i + " " + start);
		if(-cows.get(i).pos + cows.get(start).pos > D) {
			cows.get(i).crowdedRight = -2;
			return;
		}
		if(cows.get(start).height >= 2*cows.get(i).height) {
			cows.get(i).crowdedRight = start;
			return;
		}
		if(cows.get(start).height <= cows.get(i).height) {
			if(cows.get(start).crowdedRight == -2) {
				cows.get(i).crowdedRight = -2;
				return;
			} else {
				crowdRight(i, cows.get(start).crowdedRight);
				return;
			}
		}
		if(start + 1 > N-1) {
			cows.get(i).crowdedRight = -2;
			return;
		} 
		crowdRight(i, start+1);
		return;
	}

	public static void main(String[] args) throws IOException {
		long asdjfkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("crowded.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crowded.out")));
		StringTokenizer s = new StringTokenizer(f.readLine());
		N = Integer.parseInt(s.nextToken());
		D = Integer.parseInt(s.nextToken());
		for(int i = 0 ; i < N; i++) {
			s = new StringTokenizer(f.readLine());
			cows.add(new cow(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken())));			
		}
		Collections.sort(cows);
		
		cows.get(0).crowdedLeft = -2;
		cows.get(N-1).crowdedRight = -2;
		
		for(int i = 1; i < N-1; i++) {
			crowdLeft(i, i-1);
		}
		for(int i = N-2; i > 0; i--) {
			crowdRight(i, i+1);
		}
		
		int crowded = 0;
		for(cow c : cows) {
			if(c.crowdedLeft >= 0 && c.crowdedRight >= 0)
				crowded++;
		}
		//System.out.println(cows);
		
		out.println(crowded);
		out.close();
		f.close();  
		System.out.println((System.currentTimeMillis() - asdjfkl)/1000.0);
	}
	
}
