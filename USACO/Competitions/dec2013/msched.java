package dec2013;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class msched {

	static int N;
	static int[] path;
	static ArrayList<ArrayList<Cow>> cows;
	
	static class Cow {
		int g;
		int d;
		
		public Cow(int gallons, int deadline) {
			g = gallons;
			d = deadline;
		}
		
		public Cow(Cow o) {
			g = o.g;
			d = o.d;
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long asdjfkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("msched.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
		N = Integer.parseInt(f.readLine());
		StringTokenizer s;
		path = new int[10000];
		cows = new ArrayList<ArrayList<Cow>>();
		for(int i = 0; i <= 10000; i++) cows.add(new ArrayList<Cow>());
		Cow temp;
		for(int i = 0; i < N; i++) {
			s = new StringTokenizer(f.readLine());
			temp = new Cow(Integer.parseInt(s.nextToken()), Integer.parseInt(s.nextToken()));
			cows.get(temp.d).add(new Cow(temp));
		}
		for(int i = 0 ; i < 10000; i++) {
			Arrays.sort(path,0,i+1);
			for(int j = 0; j < cows.get(i+1).size(); j++) {
				if(cows.get(i+1).get(j).g > path[0]) {
					path[0] = cows.get(i+1).get(j).g;
					Arrays.sort(path,0,i+1);
				}
			}			
		}
		int sum = 0;
		for(int i : path) sum += i;
		out.println(sum);
		out.close();
		f.close();  
		System.out.println((System.currentTimeMillis() - asdjfkl)/1000.0);
	}

}
