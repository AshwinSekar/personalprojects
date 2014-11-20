package jan2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
ID: gaurjas1
LANG: JAVA
TASK: gifts
*/

public class gifts {
	static int N;
	static int B;
	
	static class Cow implements Comparable<Cow> {
		int P;
		int S;
		int PS;
		
		public Cow(int p, int s) {
			P = p;
			S = s;
			PS = p + s;
		}

		@Override
		public int compareTo(Cow o) {
			return this.PS - o.PS;
		}
	}
	
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("gifts.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gifts.out")));
		StringTokenizer in = new StringTokenizer(f.readLine());
		N = Integer.parseInt(in.nextToken());
		B = Integer.parseInt(in.nextToken());
		Cow farm[] = new Cow[N+1];
		for(int i = 0; i < N; i++) {
			in = new StringTokenizer(f.readLine());
			farm[i] = new Cow(Integer.parseInt(in.nextToken()),  Integer.parseInt(in.nextToken()));

		}
		farm[N] = new Cow(0,0);
		Arrays.sort(farm);
		int i = 0;
		int sum = 0;
		int coupon = 0;
		int prev = 0;
		
		for(i = 1; i <= N; i++) { 
			if(farm[i].P > coupon) {
				sum -= coupon;
				sum += farm[prev].PS;
				coupon = farm[i].P;
				prev = i;
				sum += farm[i].P / 2 + farm[i].S;
			} else {
				sum += farm[i].PS;
			}
			if(sum > B)
				break;
		}
		out.println(i-1);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);

	}

}
