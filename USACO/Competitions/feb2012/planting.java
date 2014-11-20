package feb2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class planting {
	int x1 , x2 , y1  , y2;
	
	public planting(int a,int b,int c,int d) {
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x1 + "," + y1 + ")" + " " + "(" + x2 + "," + y2 + ")";
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("planting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		int N = Integer.parseInt(f.readLine());
		planting[] p = new planting[N];
		int minX = 10000,minY = 10000,maxX = -10000,maxY = -10000;
		int a,b,c,d;
		StringTokenizer in;
		for(int i = 0; i < N; i++) {
			in = new StringTokenizer(f.readLine());
			a = Integer.parseInt(in.nextToken()); if(a < minX) minX = a;
			b = Integer.parseInt(in.nextToken()); if(b > maxY) maxY = b;
			c = Integer.parseInt(in.nextToken()); if(c > maxX) maxX = c;
			d = Integer.parseInt(in.nextToken()); if(d < minY) minY = d;
			p[i] = new planting(a,b,c,d);
		}
		int B = 0; int z;
		int I = 0;
		for(int i = minX; i <= maxX; i++) {
			for(int j = minY; j <= maxY; j++) {
				for(z = 0; z < N; z++) {
					if((i > p[z].x1 && i < p[z].x2 && j > p[z].y2 && j < p[z].y1)) {
						I++;
						break;
					}
				}	
				
			}
		}
		for(int i = minX; i <= maxX; i++) {
			for(int j = minY; j <= maxY; j++) {
				for(z = 0; z < N; z++) {
					if((i >= p[z].x1 && i <= p[z].x2 && j >= p[z].y2 && j <= p[z].y1)) {
						B++;
						break;
					}
				}
			}
		}
		B = B - I;		
		out.println(Math.max(I + B/2 -1,0));
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}
}
