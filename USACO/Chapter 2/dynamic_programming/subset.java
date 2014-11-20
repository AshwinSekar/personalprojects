package dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
ID: gaurjas1
LANG: JAVA
TASK: subset
*/

public class subset {
	static long check[][];
	
	public static long sub(int n, int k) {
		if(n < 0 || k < 0) return 0;
		if(check[n][k] != -1) return check[n][k];
		if(n== 0 && k == 0) return 1;
		check[n][k] = sub(n,k-1) + sub(n-k,k-1);
		return check[n][k];
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
        // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int N = Integer.parseInt(f.readLine());
		check = new long[N*(N+1)/4 + 1][N + 1];
		for(long[] x: check) Arrays.fill(x, -1);
		if((N)*(N+1)/2 % 2 == 1) out.println(0);
		else out.println(sub(N*(N+1)/4 , N)/2);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}

}
