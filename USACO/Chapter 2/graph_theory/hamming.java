package graph_theory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
ID: gaurjas1
LANG: JAVA
TASK: hamming
*/

public class hamming {	
	static int cur[];
	static int N;
	static int B;
	static int D;
	static int bytes[];
	static PrintWriter out;
	static long asdfjkl;
	
	static boolean isHamming(int x, int level) {
		int d;
		for(int i = 0; i < level; i++) {
			d = 0;
			for(int j = 0; j < B; j++) {
				if((x >> j) % 2 != (cur[i] >> j) % 2)
					d++;
			}
			if(d < D)
				return false;
		}
		return true;
	}
	
	static void recurse(int level, int last) {
		if(level == N) {
			String ans = "";
			for(int i = 0; i < N; i++) {
				if(i % 10 == 0) {
					ans += "\n" + cur[i];
				} else {
					ans += " "	+ cur[i];
				}
			}
			out.println(ans.substring(1));
			out.close();  
			System.out.println((System.currentTimeMillis() - asdfjkl)/ 1000.0);
			System.exit(0);
		}
		for(int i = last + 1; i <= Math.pow(2,B) - N + level; i++) {
			if(isHamming(bytes[i], level)) {
				cur[level] = bytes[i];
				recurse(level + 1, i);
			}
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer in = new StringTokenizer(f.readLine());
		N = Integer.parseInt(in.nextToken());
		B = Integer.parseInt(in.nextToken());
		D = Integer.parseInt(in.nextToken());
		bytes = new int[(int)Math.pow(2, B)];
		cur = new int[N];
		for(int i = 0; i < Math.pow(2,B); ++i) {
			bytes[i] = i;
		}
		recurse(0 , -1);
		f.close();
		
	}

}
