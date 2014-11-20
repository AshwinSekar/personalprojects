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
TASK: castle
*/
public class castle {
	static int N;
	static int M;
	static int[][] castle;
	static final int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
	static int size = 0;
	static int t = 2;
	
	static void floodFill(int x, int y) {
		castle[y][x] = t;
		size++;
		for(int i = 0; i < 4; i++) {
			if(castle[y + dir[i][1]][x + dir[i][0]] != t && castle[y + dir[i][1]][x + dir[i][0]] != 1 && castle[y + 2*dir[i][1]][x + 2*dir[i][0]] != t) {
				floodFill(x + 2*dir[i][0],y + 2*dir[i][1]);
			}
		}
	}

	static void ToString() {
		for(int i = 0; i < 2*M+1; i++) {
			for(int j = 0; j < 2*N+1; j++) {
				System.out.print((castle[i][j]==1?"#":" "));
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer in = new StringTokenizer(f.readLine());
		N = Integer.parseInt(in.nextToken());
		M = Integer.parseInt(in.nextToken());
		castle = new int[2*M+1][2*N+1];
		for(int i = 0; i <= 2*M; i++) {
			for(int j= 0; j <= 2*N; j += (i%2 == 0)?1:2) {
				castle[i][j] = 1;
			}
		}
		int l;
		for(int i = 1; i <= 2*M; i+=2) {
			in = new StringTokenizer(f.readLine());
			for(int j = 1; j <= 2*N; j+=2) {
				l = Integer.parseInt(in.nextToken());
				for(int c = 0; c < 4; c++) {
					if((1 & (l >> c)) == 0)
						castle[i + dir[c][1]][j + dir[c][0]] = 0;
				}
			}
		}
		//ToString();
		int biggestRoom = 0;
		int totalRooms = 0;
		for(int i = 1; i <= 2*M; i+=2)  {
			for(int j = 1; j <= 2*N; j+=2) {
				if(castle[i][j] != t) {
					totalRooms++;
					size = 0;
					floodFill(j,i);
					if(size > biggestRoom)
						biggestRoom = size;
				}
			}
		}
		out.println(totalRooms);
		out.println(biggestRoom);
		int wallX = 0;
		int wallY = 0;
		int dir = 0;
		int big = 0;
		for(int a = 1; a <= N; a++) {
			for(int b = M; b >= 1; b--) {
				if(b != 1 && castle[(b-1)*2][1 + (a-1)*2] == 1) {
					castle[(b-1)*2][1 + (a-1)*2] = 0;
					size = 0;
					t++;
					floodFill(1 + (a-1)*2,1 + (b-1)*2);
					if(size > big) {
						big = size;
						wallX = b;
						wallY = a;
						dir = 1;
					}
					castle[(b-1)*2][1 + (a-1)*2] = 1;
				}
				if(a != N && castle[1 + (b-1)*2][2 + (a-1)*2] == 1) {
					castle[1 + (b-1)*2][2 + (a-1)*2] = 0;
					size = 0;
					t++;
					floodFill(1 + (a-1)*2,1 + (b-1)*2);
					if(size > big) {
						big = size;
						wallX = b;
						wallY = a;
						dir = 0;
					}
					castle[1 + (b-1)*2][2 + (a-1)*2] = 1;
				}
				//System.out.println(b + " " + a + " " + big + " "+ wallX + " " +wallY + " "+ dir);
			}
		}
		out.println(big);
		out.println(wallX + " " + wallY + " " + (dir == 1?"N":"E"));
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}

}
