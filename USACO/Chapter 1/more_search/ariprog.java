package more_search;
/*
ID: gaurjas1
LANG: JAVA
TASK: ariprog
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ariprog {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		int N = Integer.parseInt(f.readLine());
		int M = Integer.parseInt(f.readLine());
		int biSquares[] = new int[(M+1)*(M+1)];
		int highestEl = 2*M*M;
		int i = 0;
		for(int p = 0; p <= M; p++) {
			for(int q = 0; q <= M; q++) {
				biSquares[i++] = p*p+q*q;				
			}
		}
		Arrays.sort(biSquares);
		boolean isArth;
		int x = 0;
		for(int d = 1; d <= highestEl/(N-1); d++) {
			for(int a : biSquares) {
				if(a + d*(N-1) > highestEl) {
					continue;
				}
				isArth = true;
				for(i = 1; i < N; i++) {
					if(Arrays.binarySearch(biSquares, a+d*i) < 0) {
						isArth = false;
						break;
					}
				}
				if(isArth) {
					out.println(a + " " + d);
					x++;
				}
			}
		}

		if(x == 0)
			out.println("NONE");
		out.close();  f.close();
		System.out.println((System.currentTimeMillis()-asdfjkl)*.001);
	}

}
