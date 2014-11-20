package mar2012;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

/*
ID: gaurjas1
LANG: JAVA
TASK: wrongdir
*/
public class wrongdir {
	static String instruct;
	static HashSet<Point> points;
	static int curdir = 0;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};

	public static Point eval(int i, Point pos) {
		if(i == instruct.length()) {
			return pos;
		}
		char cur = instruct.charAt(i);
		int oldx = pos.x;
		int oldy = pos.y;
		int oldcurdir = curdir;
		if(cur == 'R') curdir = (curdir + 1) % 4;
		if(cur == 'L') curdir = (curdir + 3) % 4;
		if(cur == 'F') {
			pos.x += dir[curdir][0];
			pos.y += dir[curdir][1];
		}
		Point fin = eval(i+1, (Point)pos.clone());
		int dx = fin.x - pos.x;
		int dy = fin.y - pos.y;
		if(cur == 'F') {
			points.add(new Point(oldx + dy, oldy - dx));
			points.add(new Point(oldx - dy, oldy + dx));
		} else if(cur == 'L') {
			if(oldcurdir == 0) {
				points.add(new Point(oldx + dy, oldy + 1 - dx));
			} 
			if(oldcurdir == 1) {
				points.add(new Point(oldx + 1 + dy, oldy - dx));
			}
			if(oldcurdir == 2) {
				points.add(new Point(oldx + 0 + dy, oldy - 1 - dx));
			}
			if(oldcurdir == 3) {
				points.add(new Point(oldx - 1 + dy, oldy - dx));
			}
			points.add(new Point(oldx - dx, oldy - dy));
		} else if(cur == 'R') {
			if(oldcurdir == 0) {
				points.add(new Point(oldx - dy, oldy + 1 + dx));
			} 
			if(oldcurdir == 1) {
				points.add(new Point(oldx + 1 - dy, oldy + dx));
			}
			if(oldcurdir == 2) {
				points.add(new Point(oldx + 0 - dy, oldy - 1 + dx));
			}
			if(oldcurdir == 3) {
				points.add(new Point(oldx - 1 - dy, oldy + dx));
			}
			points.add(new Point(oldx - dx, oldy - dy));
		}
		
		return fin;
		
	}
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("wrongdir.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wrongdir.out")));
		instruct = f.readLine();
		points = new HashSet<Point>();
		eval(0,new Point(0,0));
		//System.out.println(points);
		out.println(points.size());
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);

	}

}
