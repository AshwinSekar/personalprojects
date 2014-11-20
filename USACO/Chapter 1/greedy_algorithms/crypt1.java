package greedy_algorithms;
/*
ID: gaurjas1
LANG: JAVA
TASK: crypt1
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class crypt1 {
	static int numbers[];
	
	public static boolean isValid(int number, int places) {
		int digit;
		for(int i = 1; i <= places; i++) {
			digit = number % 10;
			if(Arrays.binarySearch(numbers, digit) < 0)
				return false;
			number = (number - digit) / 10;				
		}
		return true;
	}
	
	public static int[] multiplication(int a, int b) {
		int lastDig = b % 10;
		int firstDig = (b - b % 10) /10;
		int a1 = lastDig * a;
		int a2 = firstDig * a;
		int a3 = a1 + a2*10;
		int asdf[] = {a1,a2,a3};
		return asdf;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
        // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		int N = Integer.parseInt(f.readLine());
		numbers = new int[N];
		StringTokenizer in = new StringTokenizer(f.readLine());
		for(int i = 0; in.hasMoreTokens(); i++) {
			numbers[i] = Integer.parseInt(in.nextToken());
		}
		Arrays.sort(numbers);
		int answers = 0;		
		String a1,a2,a3;
		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j < 100; j++) {
				if(isValid(i,3) && isValid(j,2)) {
					int asdf[] = multiplication(i,j);
					a1 = asdf[0] +"";
					a2 = asdf[1] + "";
					a3 = asdf[2] + "";					
					if(a1.length() == 3 && a2.length() == 3 && a3.length() == 4 
							&& isValid(asdf[0],3) && isValid(asdf[1],3) && isValid(asdf[2],4)) {
						answers++;
					}
				}

			}
		}
		out.println(answers);
		out.close();  f.close();
	}

}
