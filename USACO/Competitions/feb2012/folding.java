package feb2012;

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
TASK: folding
*/

public class folding {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("folding.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("folding.out")));
		StringTokenizer in = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(in.nextToken());
		int L = Integer.parseInt(in.nextToken());
		boolean rope[] = new boolean[L+1];
		for(int i = 0; i < N; i++) {
			rope[Integer.parseInt(f.readLine())] = true;
		}
		int total  = 0;
		boolean works;
		for(int i = 1; i < L; i++) {
			works = true;
			for(int j = 1; i - j >= 0 && i + j <= L; j++) {
				if(rope[i-j] != rope[i+j]) {
					works = false;
					break;
				}
			}
			if(works) total++;
		}
		out.println(total);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}

}
