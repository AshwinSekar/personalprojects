package nov2013;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class pogocow {
	static HashMap<Point, Integer> partials = new HashMap<Point, Integer>();
	static int[] targets = new int[1000001];
	static 	ArrayList<Integer> targetLoc = new ArrayList<Integer>(1000);

	
	public static int computeMaxScoreForward(int x, int hopSize) {
		//System.out.println(targetLoc.get(x) + " " + hopSize);
		int maxScore = 0;
		for(int j = x; j < targetLoc.size(); j++) {
			if(targetLoc.get(j) - hopSize < targetLoc.get(x)) continue;
			int s = computeMaxScoreForward(j, targetLoc.get(j)-targetLoc.get(x));
			if(s > maxScore)
				maxScore = s;
		}
		maxScore += targets[targetLoc.get(x)];
		//System.out.println(targetLoc.get(x) + " " + hopSize + " " + maxScore);
		//partials.put(new Point(x,hopSize), maxScore);
		return maxScore;
	}

	public static int computeMaxScoreBackward(int x, int hopSize) {
		//System.out.println(targetLoc.get(x) + " " + hopSize);
		int maxScore = 0;
		for(int j = x; j > -1; j--) {
			if(targetLoc.get(j) + hopSize > targetLoc.get(x)) continue;
			int s = computeMaxScoreBackward(j, targetLoc.get(x)-targetLoc.get(j));
			if(s > maxScore)
				maxScore = s;
		}
		maxScore += targets[targetLoc.get(x)];
		//System.out.println(targetLoc.get(x) + " " + hopSize + " " + maxScore);
		//partials.put(new Point(x,hopSize), maxScore);
		return maxScore;
	}
	public static void main(String[] args) throws IOException {
		long asdjfkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("pogocow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pogocow.out")));
		int N = Integer.parseInt(f.readLine());
		StringTokenizer s;

		for(int i = 0; i < N; i++) {
			s = new StringTokenizer(f.readLine());
			targetLoc.add(Integer.parseInt(s.nextToken()));
			targets[targetLoc.get(i)] = Integer.parseInt(s.nextToken());
		}
		Collections.sort(targetLoc);
		
		int max = 0;
		for(int i = 0 ; i < targetLoc.size(); i++) {
			int t = computeMaxScoreForward(i,1);
			if(t > max)
				max = t;
		}
		for(int i = targetLoc.size()-1;  i > -1; i--) {
			int t = computeMaxScoreBackward(i,1);
			if(t > max)
				max = t;
		}
		
		out.println(max);
		out.close();
		f.close();  
		System.out.println((System.currentTimeMillis() - asdjfkl)/1000.0);
	}
	
}
