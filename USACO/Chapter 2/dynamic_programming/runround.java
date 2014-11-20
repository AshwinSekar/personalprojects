package dynamic_programming;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: gaurjas1
LANG: JAVA
TASK: runround
*/
public class runround {
	static int len;
	static int N;
	static PrintWriter out;
	static long asdfjkl;
	static long answer;
	
	public static void findNum(long cur, int pos) {
		if((int)Math.log10(cur) == len-1 && cur > N && (pos == len || pos == 0) && !(cur + "").contains("0")) {
			if(cur < answer) answer = cur;
		}
		if((int)((cur % Math.pow(10,pos)) / Math.pow(10,pos - 1)) == 0) {
			for(int i = 1; i < 10; i++) {	
				if((cur + "").contains(i + "")) continue;
				findNum((long) (cur + i * Math.pow(10,pos-1)) , (((pos - i + 9*len) % len == 0)?len:(pos - i + 9*len) % len));			
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		N = Integer.parseInt(f.readLine());
		len = (int) Math.log10(N) + 1;
		answer = Integer.MAX_VALUE;
		
		for(int i = 0; answer == Integer.MAX_VALUE; i++){
			len = len + i;
			findNum(0,len);
		}
		out.println(answer);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
		System.exit(0);

	}
}
