package dynamic_programming;

/*
ID: gaurjas1
LANG: JAVA
TASK: preface
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class preface {
	
	static final int[] rom = {1, 5, 10, 50 , 100, 500, 1000};
	static final char[] num = {'I' , 'V' , 'X' , 'L', 'C', 'D', 'M'};
	static int count[] = new int[7];
	
	public static void romNum(int n) {
		int dig;
		for(int i = 0; n > 0; i+=2) {
			dig = n % 10;
			if(dig <= 3) {
				count[i] += dig;
			} else if(dig <= 5) {
				count[i+1]++;
				count[i] += 5 - dig;
			} else if(dig <= 8) {
				count[i+1]++;
				count[i] += dig - 5;
			} else {
				count[i+2]++;
				count[i]++;
			}
			n = n/10;
		}
	}
	
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		int N = Integer.parseInt(f.readLine());
		for(int i = 1; i <= N; i++) {
			romNum(i);
		}
		for(int i = 0; i < 7; i++) {
			if(count[i] != 0) {
				out.println(num[i] + " " + count[i]);
			}
		}
		out.close(); f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/ 1000.0);
	}

}
