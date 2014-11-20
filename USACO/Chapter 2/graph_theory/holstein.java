package graph_theory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
ID: gaurjas1
LANG: JAVA
TASK: holstein
*/

public class holstein {
	static int V;
	static int vit[];
	static int G;
	static int feed[][];
	static int minScoops = 0;
	
	public static boolean isValid(int v[]) {
		for(int i = 0; i < vit.length; i++) {
			if(v[i] < vit[i])
				return false;
		}
		return true;
	}


	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		V = Integer.parseInt(f.readLine());
		vit = new int[V];
		StringTokenizer in = new StringTokenizer(f.readLine());
		for(int i = 0; i < V; i++) {
			vit[i] = Integer.parseInt(in.nextToken());
		}
		G = Integer.parseInt(f.readLine());
		feed = new int[G][V+17];
		for(int i = 0; i < G; i++) {
			in = new StringTokenizer(f.readLine());
			for(int j = 0; j < V; j++) {
				feed[i][j] = Integer.parseInt(in.nextToken());
			}
		}
		
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[V + 17]);
		int[] temp = null;
		int[] temp2 = new int[V + 17];
		
		while(!queue.isEmpty()) {
			temp = queue.poll();
			//System.out.println(Arrays.toString(temp));
			//System.out.println(Arrays.deepToString(queue.toArray()));
			//System.out.println();
			if(isValid(temp)) {
				//System.out.println(Arrays.toString(temp));
				break;
			}			
			temp[V]++;
			for(int i = temp[temp[V]-1 + V]; i <= G; i++) {
				if(Arrays.binarySearch(temp, V+1, V+temp[V], i) > -1)
					continue;
				//System.out.println("**" + Arrays.toString(temp));
				for(int j = 0; j < V + 17; j++) {
					temp2[j] = temp[j] + feed[i-1][j];
				}
				//System.out.println("*" + Arrays.toString(temp2));
				temp2[temp2[V] + V] = i;
				//System.out.println(Arrays.toString(temp2));
				queue.add(Arrays.copyOf(temp2,temp2.length));
			}
			//System.out.println("ROUND");
		}
		
		out.print(temp[V]);
		for(int i = V + 1; i <= V + temp[V]; i++) {
			out.print(" " + temp[i]);
		}
		out.println();
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
	}

}
