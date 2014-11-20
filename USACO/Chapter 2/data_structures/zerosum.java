/*
ID: gaurjas1
LANG: JAVA
TASK: zerosum
*/

package data_structures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class zerosum {
	static ArrayList<String> answer;
	
	public static int eval(String exp) {
		int ans = 0;
		exp = exp.replaceAll(" ", "");
		StringTokenizer num =  new StringTokenizer(exp, "[+-]");
		StringTokenizer op =  new StringTokenizer(exp, "[123456789]*");
		ans = Integer.parseInt(num.nextToken());
		String t;
		while(num.hasMoreTokens()) {
			t = op.nextToken();
			if(t.equals("+"))
				ans += Integer.parseInt(num.nextToken());
			else if(t.equals("-"))
				ans -= Integer.parseInt(num.nextToken());
		}
		return ans;
	}
	
	public static void search(int i, String exp) {
		if(i == exp.length()) {
			if(eval(exp) == 0) answer.add(exp); 
			return;
		}
		search(i + 2 , exp);
		search(i + 2 , exp.substring(0,i) + "+" + exp.substring(i+1));
		search(i + 2 , exp.substring(0,i) + "-" + exp.substring(i+1));
	}

	public static void main(String args[]) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		int N = Integer.parseInt(f.readLine());
		String exp = "1";
		for(int i = 2; i <= N; i++) {
			exp += " " + i;
		}
		answer = new ArrayList<String>();
		search(1,exp);
		Collections.sort(answer);
		for(String a : answer) out.println(a);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}
}
