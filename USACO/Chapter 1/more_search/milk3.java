package more_search;


/*
ID: gaurjas1
LANG: JAVA
TASK: milk3
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class milk3 {
	static HashSet<String> usedStates;
	static int A;
	static int B;
	static int C;
	
	public static String[] pour(String s) {
		String ans[] = new String[6];
		String temp[] = s.split(" ");
		int a = Integer.parseInt(temp[0]);
		int b = Integer.parseInt(temp[1]);
		int c = Integer.parseInt(temp[2]);
		ans[0] = a + " " + Math.min(b + c, B) + " " + (c - Math.min(b + c, B) + b);
		ans[1] = Math.min(a + c, A) + " " + b + " " + (c - Math.min(a + c, A) + a);
		ans[2] = Math.min(a + b, A) + " " + (b - Math.min(a + b, A) + a) + " " + c;
		
		ans[3] = a + " " + (b - Math.min(b + c, C) + c) + " " + Math.min(b + c, C);
		ans[4] = (a - Math.min(a + c, C) + c) + " " + b + " " + Math.min(a + c, C);
		ans[5] = (a - Math.min(a + b, B) + b) + " " + Math.min(a + b, B) + " " + c;
		
		return ans;
		
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		String s = f.readLine();
		StringTokenizer temp = new StringTokenizer(s);
		A = Integer.parseInt(temp.nextToken());
		B = Integer.parseInt(temp.nextToken());
		C = Integer.parseInt(temp.nextToken());
		usedStates = new HashSet<String>();
		usedStates.add("0 0 " + C);
		LinkedList<String> queue = new LinkedList<String>();
		queue.add("0 0 " + C);
		
		String str;
		String states[] = new String[3];
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while(!queue.isEmpty()) {
			str = queue.poll();
			//System.out.println(str);
			if(str.startsWith("0")) {
				ans.add(Integer.parseInt(str.split(" ")[2]));
			}
			states = pour(str);
			for(int i = 0; i < 6; i++) {
				if(!usedStates.contains(states[i])) {
					usedStates.add(states[i]);
					queue.add(states[i]);
				}
			}

		}
		Collections.sort(ans);
		String ansd = "";
		for(int a : ans)
			ansd += a + " ";
		out.println(ansd.substring(0,ansd.length()-1));
		out.close();  f.close();

	}

}
