package feb2013;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class perimeter {
	static int perim;
	static int[][] field;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		
		int N = Integer.parseInt(f.readLine());
		field = new int[102][102];
		visited = new boolean[102][102];
		StringTokenizer s;
		int x = 0,y = 0;
	
		for(int i = 0; i  < N; i++) {
			s = new StringTokenizer(f.readLine());
			x = Integer.parseInt(s.nextToken());
			y = Integer.parseInt(s.nextToken());
			field[y][x] = 1;
		}

		perim = 0;
		fillOutside(0,0);
		
		/*for(int i = 0; i < 102; i++) {
			for(int j = 0; j < 102; j++) {
				if(field[i][j] == -1) System.out.print(".");
				if(field[i][j] == 1) System.out.print("X");
				if(field[i][j] == 0) System.out.print(" ");
			}
			System.out.println();
		}*/
		
		fillDatFlood(x,y);
		
		out.println(perim);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}
	
	public static void fillOutside(int x, int y) {
		field[x][y] = -1;
		if(x + 1 < 102 && field[x+1][y] == 0) fillOutside(x+1,y);
		if(x - 1 > 0 && field[x-1][y] == 0) fillOutside(x-1,y);
		if(y + 1 < 102 && field[x][y+1] == 0) fillOutside(x,y+1);
		if(y - 1 > 0 && field[x][y-1] == 0) fillOutside(x,y-1);
	}
	
	public static void fillDatFlood(int x, int y) {
		
		visited[y][x] = true;
		if(field[y][x+1] == -1) perim++;
		if(field[y][x-1] == -1) perim++;
		if(field[y-1][x] == -1) perim++;
		if(field[y+1][x] == -1) perim++;
		//System.out.println(x + " " + y + " " + perim);
		
		if(field[y][x+1] != -1 && !visited[y][x+1]) {
			fillDatFlood(x+1,y);
		}
		if(field[y][x-1] != -1 && !visited[y][x-1]) {
			fillDatFlood(x-1,y);
		}
		if(field[y+1][x] != -1 && !visited[y+1][x]) {
			fillDatFlood(x,y+1);
		}
		if(field[y-1][x] != -1 && !visited[y-1][x]) {
			fillDatFlood(x,y-1);
		}
		
	}
}
