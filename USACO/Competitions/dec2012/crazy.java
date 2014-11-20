package dec2012;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class crazy {
	int x1;
	int x2;
	int y1;
	int y2;
	boolean active;
	
	public crazy(int a,int b, int c, int d) {
		x1 = a; 
		y1 = b;
		x2 = c;
		y2 = d;
		active = true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return x1 + " " + y1 + " " + x2 + " " + y2 + " " + active + "\n";
	}
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("crazy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crazy.out")));
		StringTokenizer s = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(s.nextToken());
		int C = Integer.parseInt(s.nextToken());
		ArrayList<crazy> fences = new ArrayList<crazy>();
		for(int i = 0; i < N; i++) {
			s = new StringTokenizer(f.readLine());
			fences.add(new crazy(Integer.parseInt(s.nextToken()),Integer.parseInt(s.nextToken()),Integer.parseInt(s.nextToken()),Integer.parseInt(s.nextToken())));
		}
		ArrayList<Point> cows = new ArrayList<Point>();
		for(int i = 0; i < C; i++) {
			s = new StringTokenizer(f.readLine());
			cows.add(new Point(Integer.parseInt(s.nextToken()),Integer.parseInt(s.nextToken())));
		}
		boolean left,right;
		for(crazy a : fences) {
			left = false;
			right = false;
			for(crazy b : fences) {
				if(a != b) {
					if((a.x1 == b.x1 && a.y1 == b.y1) || (a.x1 == b.x2 && a.y1 == b.y2))
						left = true;
					if((a.x2 == b.x1 && a.y2 == b.y1) || (a.x2 == b.x2 && a.y2 == b.y2))
						right = true;
				}
			}
			if(!left || !right) a.active = false;
		}
		int ans = 0;
		int temp;
		boolean pass;
		for(Point p : cows) {
			if(p.x == -1) continue;
			temp = 1;
			pass = true;
			for(Point q : cows) {
				if(q.x == -1 || p == q) continue;
				for(crazy c : fences) {
					if((c.x1 == c.x2) && ((c.x1 > p.x && c.x1 < q.x) || (c.x1 < p.x && c.x1 > q.x))) {
						pass = false;
						break;
					} else if((c.y1 == c.y2) && ((c.y1 > p.y && c.y1 < p.y) || (c.y1 < p.y && c.y1 > q.y))) {
						pass = false;
						break;
					}
				}
				if(pass)  {
					q.x = -1;
					temp++;
				}
				
			}
			p.x = -1;
			if(temp > ans) ans = temp;
		}
		out.println(ans);
		f.close();
		out.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
	}

}
