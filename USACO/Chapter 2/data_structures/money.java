/*
ID: gaurjas1
LANG: JAVA
TASK: money
*/

package data_structures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class money {
	static long[] cache;
	
	public static void main(String args[]) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer in = new StringTokenizer(f.readLine());
		in.nextToken();
		int N = Integer.parseInt(in.nextToken());
		String temp = f.readLine();
		cache = new long[N + 1];
		cache[0] = 1;
		while(temp != null) {
			in = new StringTokenizer(temp);
			while(in.hasMoreTokens()) {
				int cur = Integer.parseInt(in.nextToken());
				for(int i = cur; i <= N; i++) {
					cache[i] += cache[i - cur];
				}
			}
			temp = f.readLine();
		}
		out.println(cache[N]);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}	
}
