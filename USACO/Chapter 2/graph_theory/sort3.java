package graph_theory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: gaurjas1
LANG: JAVA
TASK: sort3
*/

public class sort3 {
	
	public static void main(String args[]) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int N = Integer.parseInt(f.readLine());
		int a[] = new int[N];
		int freq[] = new int[3];
		int minSwitch = 0;
		int temp;
		for(int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(f.readLine());
			freq[a[i]-1]++;
		}
		int curPos[][] = new int[3][3];
		int k = 0;
		for(int i =0; i < 3; i++) {
			for(int j = k; j < k + freq[i]; j++) {
				curPos[i][a[j] - 1]++;
			}
			k = k + freq[i];
		}
			temp = Math.min(curPos[0][1], curPos[1][0]);
			curPos[0][0] += temp;
			curPos[1][1] += temp;
			curPos[0][1] -= temp;
			curPos[1][0] -= temp;
			minSwitch += temp;
			temp = Math.min(curPos[0][2], curPos[2][0]);
			curPos[0][0] += temp;
			curPos[2][2] += temp;
			curPos[0][2] -= temp;
			curPos[2][0] -= temp;
			minSwitch += temp;
			temp = freq[0] - curPos[0][0];
			minSwitch += temp;
			curPos[0][0] = freq[0];
			curPos[1][0] = curPos[2][0] = 0;
			curPos[1][2] += curPos[0][2];
			curPos[2][1] += curPos[0][1];
			minSwitch += curPos[2][1];
		out.println(minSwitch);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);		
	}

}
