package mar2012;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: gaurjas1
LANG: JAVA
TASK: connect
*/

public class connect {
	static int N;
	static int paths = 0;
	static Point[] cows;
	
	public static void rec(int x,int y, int left) {
		if(left == 0) {
			if(x == 0 || y == 0) {
				paths++;
			}
			return;
		}
		int temp1,temp2;
		for(int i = 0; i < N; i++) {
			if(cows[i].x == x || cows[i].y == y) {
				temp1 = cows[i].x;
				temp2 = cows[i].y;
				
				cows[i].x = 1001;
				cows[i].y = 1001;
				rec(temp1,temp2,left-1);
				cows[i].x = temp1;
				cows[i].y = temp2;
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("connect.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("connect.out")));
		N = Integer.parseInt(f.readLine());
		cows = new Point[N];
		String[] p = new String[2];
		for(int i = 0; i < N; i++) {
			p = f.readLine().split(" ");
			cows[i] = new Point(Integer.parseInt(p[0]),Integer.parseInt(p[1]));
		}
		rec(0,0,N);
		out.print(paths);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
	}

}
