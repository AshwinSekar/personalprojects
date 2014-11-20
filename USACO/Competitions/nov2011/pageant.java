package nov2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: pageant
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class pageant {
	static ArrayList<node> frontier;
	
	public static class node{
		int x;
		int y;
		int steps;
		public node(int x,int y, int s) {
			this.x = x;
			this.y = y;
			this.steps = s;
		}
		public String toString() {
			return x+","+y+","+steps;
		}
	}
	
	public static int BFS(int spots[][]) {
		int remove = -1;
		while(true) {
			/*for(int i = 0; i < spots.length; i++) {
				for(int j = 0; j < spots[0].length; j++ ) {
					System.out.print(spots[i][j]);
				}
				System.out.println();
			}
			System.out.println();*/
			int smallestSteps = Integer.MAX_VALUE;
			for(int i = 0; i < frontier.size(); i++) {
				if(frontier.get(i).steps < smallestSteps) {
					smallestSteps = frontier.get(i).steps;
					remove = i;
				}
			}
			//System.out.println(frontier.get(remove).x +"," + frontier.get(remove).y + "," + frontier.get(remove).steps);
			if(spots[frontier.get(remove).x][frontier.get(remove).y] == 2) {
				return frontier.get(remove).steps;
			} else {
				int x = frontier.get(remove).x;
				int y = frontier.get(remove).y;
				int s = frontier.get(remove).steps;
				int lenx = spots.length;
				int leny = spots[0].length;
				
				if(x < lenx && x >= 0 && y+1 < leny && y+1 >= 0 && (spots[x][y+1] == 0 || spots[x][y+1] == 2)) {
					frontier.add(new node(x,y+1, s+1));
					spots[x][y+1] = spots[x][y+1] == 0?3:2;
				} 
				if(x+1 < lenx && x+1 >= 0 && y < leny && y >= 0 && (spots[x+1][y] == 0 || spots[x+1][y] == 2)) {
					frontier.add(new node(x+1,y, s+1));
					spots[x+1][y] = spots[x+1][y] == 0?3:2;
				} 
				if(x-1 < lenx && x-1 >= 0 && y < leny && y >= 0 && (spots[x-1][y] == 0 || spots[x-1][y] == 2)) {
					frontier.add(new node(x-1,y, s+1));
					spots[x-1][y] = spots[x-1][y] == 0?3:2;
				}
				if(x < lenx && x >= 0 && y-1 < leny && y-1 >= 0 && (spots[x][y-1] == 0 || spots[x][y-1] == 2)) {
				    frontier.add(new node(x,y-1, s+1));
				    spots[x][y-1] = spots[x][y-1] == 0?3:2;
				}
				frontier.remove(remove);
			}
		}		
	}
	
	public static void floodFill(int x,int y, int val, int look, int spots[][]) {
		int lenx = spots.length;
		int leny = spots[0].length;
		spots[x][y] = val;
		if(x < lenx && x >= 0 && y+1 < leny && y+1 >= 0 && spots[x][y+1] == look) {
			floodFill(x,y+1,val,look,spots);
		} 
		if(x+1 < lenx && x+1 >= 0 && y < leny && y >= 0 && spots[x+1][y] == look) {
			floodFill(x+1,y,val,look,spots);
		} 
		if(x-1 < lenx && x-1 >= 0 && y < leny && y >= 0 && spots[x-1][y] == look) {
			floodFill(x-1,y,val,look,spots);
		}
		if(x < lenx && x >= 0 && y-1 < leny && y-1 >= 0 && spots[x][y-1] == look) {
			floodFill(x,y-1,val,look,spots);
		}
		node hi = new node(x,y, 0);
		frontier.add(hi);
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println(System.currentTimeMillis());
		BufferedReader f = new BufferedReader(new FileReader("pageant.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pageant.out")));
		StringTokenizer in = new StringTokenizer(f.readLine()); 
		int N = Integer.parseInt(in.nextToken());
		int M = Integer.parseInt(in.nextToken());
		int spots[][] = new int[N][M];
		int start1x = 0,start1y = 0;
		for(int i = 0; i < N; i++) {
			String row = f.readLine();
			for(int j = 0; j < M; j++) {
				spots[i][j] = (row.charAt(j) == '.')?0:2;
				if(spots[i][j] == 2 && start1x == 0 && start1y == 0) {
					start1x = i;
					start1y = j;
				}
			}
		}
		frontier = new ArrayList<node>();
		floodFill(start1x,start1y,1,2,spots);
		out.println(BFS(spots)-1);
		out.close();  f.close();  f.close();f.close();f.close();
		System.out.println(System.currentTimeMillis());
		

	}

}
