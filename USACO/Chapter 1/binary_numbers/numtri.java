package binary_numbers;

/*
ID: gaurjas1
LANG: JAVA
TASK: numtri
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class numtri {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		int N = Integer.parseInt(f.readLine());
		int tri[][] = new int[N][N];
		String temp[];
		int cur;
		tri[0][0] = Integer.parseInt(f.readLine());
		for(int i = 1; i < N; i++) {
			temp = f.readLine().split(" ");
			for(int j = 0; j <= i; j++) {
				cur = Integer.parseInt(temp[j]);
				tri[i][j] = Math.max(tri[i-1][j] + cur, tri[i-1][Math.max(j - 1,0)] + cur);
			}
		}
		int max = 0;
		for(int i = 0; i < N; i++) {
			if(tri[N-1][i] > max) {
				max = tri[N-1][i];
			}
		}
		out.println(max);
		out.close();  f.close();
	}

}
