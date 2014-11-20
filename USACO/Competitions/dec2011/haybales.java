//TODO
package dec2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: haybales
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class haybales {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		int N = Integer.parseInt(f.readLine());
		int heights[] = new int[N];
		long sum = 0; 
		for(int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(f.readLine());
			sum += heights[i];
		}
		
		long mean = (sum)/N;
		long ans = 0;
		for(int i = 0; i < N; i++) {
			if(heights[i] < mean) {
				ans += mean - heights[i];
			}
		}
		out.println((ans));
		out.close();  f.close();
	}
}
