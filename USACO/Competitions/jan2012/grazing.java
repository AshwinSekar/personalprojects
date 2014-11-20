package jan2012;

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
TASK: gifts
*/
public class grazing {
	
	static int field[][];
	static int level = 0;
	static int finalLevel;
	static int numWays = 0;
	static final int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	
	public static void recurse(int Bx, int By, int Mx, int My) {
		//System.out.println(level);
		/*if(level == finalLevel) {
		for(int i = 1; i <= 5; i++) {
			for(int j = 1; j <=5; j++) {
				if(field[i][j] == 0)
					System.out.print(". ");
				else if(field[i][j] == -1)
					System.out.print("x ");
				else if(field[i][j] == 1)
					System.out.print("B ");
				else if(field[i][j] == 2)
					System.out.print("M ");
				else if(field[i][j] == 3)
					System.out.print("* ");
			}
			System.out.println();
		}
		System.out.println();
		}*/
		if(level < 0)
			System.exit(-111);
		if(level == finalLevel && field[Bx][By] == 3) {
			numWays++;
			field[Bx][By] = 0;
			field[Mx][My] = 0;
			return;
		} else if(field[Bx][By] == 3) {
			field[Bx][By] = 0;
			field[Mx][My] = 0;
			return;
		} else if(level == finalLevel) {
			field[Bx][By] = 0;
			field[Mx][My] = 0;
			return;
		}
		field[Bx][By] = -1;
		field[Mx][My] = -1;
		level++;
		for(int i  = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				//System.out.println((Bx + dxy[i][0]) + " " + (By + dxy[i][1]) + " , " + (Mx + dxy[j][0]) + " " + (My + dxy[j][1]));
				if(field[Bx + dxy[i][0]][By + dxy[i][1]] == 0 && field[Mx + dxy[j][0]][My + dxy[j][1]] == 0) {
					field[Bx + dxy[i][0]][By + dxy[i][1]] += 1;
					field[Mx + dxy[j][0]][My + dxy[j][1]] += 2;
					recurse(Bx + dxy[i][0],By + dxy[i][1] , Mx + dxy[j][0],My + dxy[j][1]);
				}
			}
		}
		level--;
		field[Bx][By] = 0;
		field[Mx][My] = 0;
	}

	
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("grazing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("grazing.out")));
		int K = Integer.parseInt(f.readLine());
		finalLevel = (25 - K) / 2;
		field = new int[7][7];
		StringTokenizer in;
		field[1][1] = 1;
		field[5][5] = 2;
		for(int i = 0; i < K; i++) {
			in = new StringTokenizer(f.readLine());
			field[Integer.parseInt(in.nextToken())][Integer.parseInt(in.nextToken())]--;
		}
		for(int i = 0; i < 7; i++) {
			field[0][i]--;
			field[6][i]--;
			field[i][0]--;
			field[i][6]--;
		}
		recurse(1,1 , 5,5);
		out.println(numWays);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
		
	}

}
