package binary_numbers;
/*
ID: gaurjas1
LANG: JAVA
TASK:  checker
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class checker {
	
	static PrintWriter out;
	static int N;
	static int[] sol;
	static boolean col[];
	static boolean diagL[];
	static boolean diagR[];
	static int count = 0;
	static int dup = 0;
	static String last = "";
	
	public static void recurse(int rows) {
		if(rows == N) {
			if(count < 3) {
				for(int i = 0; i < N-1; i++) {
					out.print(sol[i] + " ");
				}
				out.println(sol[N-1]);
			}
			count++;
			if(sol[0] != (N+1)/2.0) {
				dup++;
				last = "";
				if(count < 3) {
					for(int i = 0; i < N-1; i++) {
						last += (N-sol[i] + 1) + " ";
					}
					last += N-sol[N-1]+1;
				}
			}
			return;
		}
		for(int i = 0; i < N; i++) { 
			if(!col[i] && !diagL[i + rows] && !diagR[(N-i-1) + rows]) {
				col[i] = true;
				diagL[i + rows] = true;
				diagR[(N-i-1) + rows] = true;
				sol[rows] = i+1;
				recurse(rows+1);
				col[i] = false;
				diagL[i + rows] = false;
				diagR[(N-i-1) + rows] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("checker.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));
		N = Integer.parseInt(f.readLine());
		col = new boolean[N];
		diagL = new boolean[2*N-1];
		diagR = new boolean[2*N-1];
		sol = new int[N];
		for(int i = 0; i < N/2.0; i++) {
			sol[0] = i+1;
			col[i] = true;
			diagL[i + 0] = true;
			diagR[(N-i-1) + 0] = true;
			recurse(1);
			col[i] = false;
			diagL[i + 0] = false;
			diagR[(N-i-1) + 0] = false;
		}
		if(count < 3) 
			out.println(last.substring(0, 2*N-1));
		out.println(count + dup);
		out.close();
		f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}

}
