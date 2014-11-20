package feb2013;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Comparator;

public class crossings {

	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("crossings.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crossings.out")));
		int N = Integer.parseInt(f.readLine());
		
		ArrayList<Point> lines = new ArrayList<Point>();
		ArrayList<Point> upSloping = new ArrayList<Point>();
		ArrayList<Point> downSloping = new ArrayList<Point>();
		ArrayList<Point> lines2 = new ArrayList<Point>();
		ArrayList<Point> upSloping2 = new ArrayList<Point>();
		ArrayList<Point> downSloping2 = new ArrayList<Point>();
		
		HashSet<Point> slopingAns = new HashSet<Point>();
		HashSet<Point> ans = new HashSet<Point>();

		StringTokenizer s;
		Point p;
		
		for(int i = 0; i < N; i++) {
			s = new StringTokenizer(f.readLine());
			p = new Point(Integer.parseInt(s.nextToken()) , Integer.parseInt(s.nextToken()));
			lines.add(p);
			lines2.add(p);
			if(p.y >= p.x) {
				upSloping.add(p);
				upSloping2.add(p);
			} else {
				downSloping.add(p);
				downSloping2.add(p);
			}
		}
		
		Comparator<Point> up = new sortUp();
		Comparator<Point> down = new sortDown();
		
		Collections.sort(lines, up);
		Collections.sort(upSloping, up);
		Collections.sort(downSloping, up);
		
		Collections.sort(lines2, down);
		Collections.sort(upSloping2, down);
		Collections.sort(downSloping2, down);
		
		for(int i = 0; i < N; i++) {
			if(lines.get(i).equals(lines2.get(i))) 
				ans.add(lines.get(i));
		}
		for(int i = 0; i < upSloping.size(); i++) {
			if(upSloping.get(i).equals(upSloping2.get(i)))
				slopingAns.add(upSloping.get(i));
		}
		for(int i = 0; i < downSloping.size(); i++) {
			if(downSloping.get(i).equals(downSloping2.get(i)))
				slopingAns.add(downSloping.get(i));
		}
		ans.retainAll(slopingAns);
		
		out.println(ans.size());
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}	
	
	public static class sortUp implements Comparator<Point> {

		@Override
		public int compare(Point o1, Point o2) {
			return o1.x - o2.x;
		}
		
	}
	
	public static class sortDown implements Comparator<Point> {

		@Override
		public int compare(Point o1, Point o2) {
			return o1.y - o2.y;
		}
		
	}

}


