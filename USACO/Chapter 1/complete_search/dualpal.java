package complete_search;
/*
ID: gaurjas1
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;


class dualpal {
	
	public static boolean isPalindrome(String pal) {
		for(int i = 0; i < Math.floor(pal.length()/2); i++) {
			if(pal.charAt(i) != pal.charAt(pal.length()-i-1))
				return false;				
		}
		return true;
	}
	public static boolean isPalBase(int num) {
		int bases = 0;
		for(int i = 2; i <= 10; i++) {
			if(isPalindrome(Integer.toString(num, i))) {
				bases++;
				//System.out.println("           "+i);
			}
			if(bases >= 2)
				return true;
		}
		return false;
	}
	public static void main (String [] args) throws IOException {
    //Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
                                                  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		//Scanner f = new Scanner(System.in);
		StringTokenizer in = new StringTokenizer(f.readLine());
		int num = Integer.parseInt(in.nextToken());
		int starting = Integer.parseInt(in.nextToken());
		int current = 0;
		int currentnum = starting+1;
		while(current < num) {
			if(isPalBase(currentnum)) {
				out.println(currentnum);
				current++;
			}
			currentnum++;
		}
		out.close();  f.close();
	}
}