package more_search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
/*
ID: gaurjas1
LANG: JAVA
TASK: packrec
*/
import java.util.HashSet;

public class packrec {
	static Rectangle pack[];
	static Rectangle packR[];
	static int minA;
	static HashSet<String> dim;
	
	private static final class Rectangle {
		final int width;
		final int height;
		
		public Rectangle(String w, String h) {
			width = Integer.parseInt(w);
			height = Integer.parseInt(h);
		}
		
		public int getArea() {
			return width*height;
		}
		
		@Override
		public String toString() {
			return width + "x" + height;
		}
	}
	
	public static Rectangle pos1(Rectangle rec[]) {
		return new Rectangle(rec[0].width + rec[1].width + rec[2].width + rec[3].width + "",
				Math.max(Math.max(Math.max(rec[0].height, rec[1].height), rec[2].height), rec[3].height) + "");
	}
	
	public static Rectangle pos2(Rectangle rec[]) {
		return new Rectangle(Math.max(rec[0].width, rec[1].width + rec[2].width + rec[3].width) + "",
				rec[0].height + Math.max(Math.max(rec[3].height, rec[1].height), rec[2].height) + "");
	}
	
	public static Rectangle pos3(Rectangle rec[]) {
		return new Rectangle(Math.max(rec[0].width + rec[1].width, rec[1].width + rec[2].width + rec[3].width) + "",
				Math.max(rec[1].height, rec[0].height + Math.max(rec[2].height,rec[3].height))+ "");
	}
	
	public static Rectangle pos4(Rectangle rec[]) {
		return new Rectangle(rec[0].width + rec[3].width + Math.max(rec[1].width,rec[2].width)+ "",
				Math.max(rec[0].height , Math.max(rec[3].height, rec[1].height + rec[2].height)) + "");
	}
	
	public static Rectangle pos5(Rectangle rec[]) {
		int y = Math.max(rec[0].height+rec[2].height, rec[1].height+rec[3].height);
		int x = rec[0].width + rec[1].width;
		if (rec[0].height < rec[1].height)
			x = Math.max(x, rec[2].width+rec[1].width);
		if (rec[0].height+rec[2].height > rec[1].height)
			x = Math.max(x, rec[2].width+rec[3].width);
		if (rec[1].height < rec[0].height)
			x = Math.max(x, rec[0].width+rec[3].width);
		x = Math.max(x, rec[2].width);
		x = Math.max(x, rec[3].width);
		return new Rectangle(x + "",y + "");
	}
	
		
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("packrec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
		pack = new Rectangle[4];
		packR = new Rectangle[4];
		for(int i = 0; i < 4; i++) {
			String s = f.readLine();
			pack[i] = new Rectangle(s.split(" ")[0], s.split(" ")[1]);
			packR[i] = new Rectangle(s.split(" ")[1], s.split(" ")[0]);
		}
		minA = Integer.MAX_VALUE;
		dim = new HashSet<String>();
		int perm[] = new int[4];
		for (perm[0] = 0; perm[0] < 4; perm[0]++) {
		    for (perm[1] = 0; perm[1] < 4; perm[1]++)   {
		        if (perm[0] == perm[1])        continue;
		        for (perm[2] = 0; perm[2] < 4; perm[2]++) {
		             if (perm[0] == perm[2] || perm[1] == perm[2])   continue;
		             perm[3] = 6 - perm[0] - perm[1] - perm[2]; 
		             Rectangle temp[] = new Rectangle[4];
		             for(int x = 0; x < 16; x++) {
		            	 for(int m = 0; m < 4; m++) {
		            		 if((x & (1 << m)) == 0) {
		            			 temp[m] = pack[perm[m]];
		            		 } else {
		            			 temp[m] = packR[perm[m]];	
		            		 }
		            	 }
			             Rectangle ans[] = {pos1(temp) , pos2(temp) , pos3(temp) , pos4(temp) , pos5(temp)};
			 			 for(int i = 0; i < 5; i++) {
			 				if(ans[i].getArea() == minA) {
			 					dim.add(Math.min(ans[i].width, ans[i].height) + " " + Math.max(ans[i].width, ans[i].height));
			 				} else if (ans[i].getArea() < minA) {
			 					minA = ans[i].getArea();
			 					dim.clear();
			 					dim.add(Math.min(ans[i].width, ans[i].height) + " " + Math.max(ans[i].width, ans[i].height));		
			 				}
			 			}       
		             }	    
		             
		        }
		    }
		}
		
		
		
		ArrayList<String> ans = new ArrayList<String>(dim);
		out.println(minA);
		Collections.sort(ans);
		for(String s : ans) {
			out.println(s);
		}
		out.close();  f.close();

	}

}
