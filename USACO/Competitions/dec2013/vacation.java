package dec2013;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class vacation {
	static int N;
	static int K;
	static int Q;
	static int M;
	static int numTrips = 0;
	static HashMap<Integer,	HashSet<Point>> flights;
	static HashMap<Point, Integer> visited;
	
	static int leastCost(int source, int dest) {
		int shortest = Integer.MAX_VALUE;
		visited = new HashMap<Point, Integer>();
		
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {source, 0, (source <= K)?1:0});
		while(!queue.isEmpty()) {
			//for(int[] s : queue) System.out.print("Farm:" + s[0] + " Cost:" + s[1] + " Hub:" + s[2] + " ");
			//System.out.println();
			int[] temp = queue.poll();
			//System.out.println(temp[0] + " "+ temp[1] + " "+ temp[2]);
			if(temp[0] == dest && temp[2] == 1) {
				if(temp[1] < shortest) shortest = temp[1];
				continue;
			}
			if(visited.containsKey(new Point(temp[0],temp[2]))) {
				if(visited.get(new Point(temp[0],temp[2])) <= temp[1]) continue;
			} else  {
				visited.put(new Point(temp[0],temp[2]), temp[1]);
			}
			if(flights.containsKey(temp[0])) {
				for(Point p : flights.get(temp[0])) {
					queue.add(new int[] {p.x, p.y + temp[1], (p.x <= K || temp[2] == 1)?1:0});
				}
			}
		}
		if(Integer.MAX_VALUE == shortest) return 0;
		numTrips++;
		return shortest;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long asdjfkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("vacation.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("vacation.out")));
		StringTokenizer s = new StringTokenizer(f.readLine());
		N = Integer.parseInt(s.nextToken());
		M = Integer.parseInt(s.nextToken());
		K = Integer.parseInt(s.nextToken());
		Q = Integer.parseInt(s.nextToken());
		flights = new HashMap<Integer, HashSet<Point>>();
		for(int i = 0; i < M; i++) {
			s = new StringTokenizer(f.readLine());
			int key = Integer.parseInt(s.nextToken());
			if(flights.containsKey(key)) {
				flights.get(key).add(new Point(Integer.parseInt(s.nextToken()),Integer.parseInt(s.nextToken())));
			} else {
				flights.put(key, new HashSet<Point>());
				flights.get(key).add(new Point(Integer.parseInt(s.nextToken()),Integer.parseInt(s.nextToken())));
			}			
		}
		int sum = 0;
		for(int i = 0; i < Q; i++) {
			s = new StringTokenizer(f.readLine());
			int source = Integer.parseInt(s.nextToken());
			int dest = Integer.parseInt(s.nextToken());
			sum += leastCost(source, dest);
		}
		
		out.println(numTrips);
		out.println(sum);
		out.close();
		f.close();  
		System.out.println((System.currentTimeMillis() - asdjfkl)/1000.0);
	}

}
