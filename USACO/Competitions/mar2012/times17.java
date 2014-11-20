package mar2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: gaurjas1
LANG: JAVA
TASK: times17
*/


public class times17 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("times17.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("times17.out")));
		String partial1 = f.readLine();
		if(partial1.equals("0")) {
			out.println("0");
			out.close();  f.close();
			System.exit(0);
		}
		String partial2 = partial1 + "0000";
		partial1 = "0000" + partial1;
		String ans = "";
		int carry = 0, a, b;
		for(int i = partial2.length() - 1; i >= 0; i--) {
			a = Character.digit(partial1.charAt(i),10);
			b = Character.digit(partial2.charAt(i),10);
			if(a + b + carry == 0)  {
				ans = "0" + ans;
				carry = 0;
			} else if(a + b + carry == 2) {
				ans = "0" + ans;
				carry = 1;
			} else if (a + b + carry == 3) {
				ans = "1" + ans;
				carry = 1;
			} else if (a + b + carry == 1) {
				ans = "1" + ans;
				carry = 0;
			}
		}
		out.println(ans);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);

	}

}
