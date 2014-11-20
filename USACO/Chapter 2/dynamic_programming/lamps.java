package dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
ID: gaurjas1
LANG: JAVA
TASK: lamps
*/

public class lamps {
	static int lamps[];
	static int cur[];
	static HashSet<String> ans;
	static int C;
	
	static void move(int N) {
		int j = 0;
		int k = 0;
		switch(N) {
		case 1:
			j = 0;
			k = 1;
			break;
		case 2:
			j = 0;
			k = 2;
			break;
		case 3:
			j = 1;
			k = 2;
			break;
		case 4:
			j = 0;
			k = 3;
			break;
		}
		for(int i = j; i < cur.length; i += k) {
			cur[i] = (cur[i] + 3) % 4 - 1;
		}
	}
	
	static boolean isValid() {
		for(int i = 0; i < cur.length; i++) {
			if(lamps[i] == 0)continue;
			if(lamps[i] != cur[i])return false;
		}
		return true;
	}
	static void find(int level) {
		if(level == C) {
			if(!isValid()) return;
			String s = "";
			for(int i = 0; i < cur.length; i++) {
				s += (cur[i] == -1?0:1);
			}
			ans.add(s);
			return;
		}
		for(int i = 1; i <= 4; i++) {
			move(i);
			find(level+1);
			move(i);
		}
	}

	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		ans = new HashSet<String>();
		int N = Integer.parseInt(f.readLine());
		lamps = new int[N];
		cur = new int[N];
		Arrays.fill(cur,1);
		int a = Integer.parseInt(f.readLine());
		C = a % 4;
		if(C == 0 && a > 0) C = 4;
		StringTokenizer in = new StringTokenizer(f.readLine());
		int t = Integer.parseInt(in.nextToken());
		while(t != -1) {
			lamps[t -1] = 1;
			t = Integer.parseInt(in.nextToken());
		}
		in = new StringTokenizer(f.readLine());
		t = Integer.parseInt(in.nextToken());
		while(t != -1) {
			lamps[t -1] = -1;
			t = Integer.parseInt(in.nextToken());
		}
		
		find(0);
		ArrayList<String> x = new ArrayList<String>(ans);
		Collections.sort(x);
		for(String s : x) {
			out.println(s);
		}
		if(x.size() == 0) out.println("IMPOSSIBLE");
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
	}
}
