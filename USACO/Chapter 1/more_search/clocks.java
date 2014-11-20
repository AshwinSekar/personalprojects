package more_search;

/*
ID: gaurjas1
LANG: JAVA
TASK: clocks
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class clocks {
	
	static long asdfjkl;	
	static final String moves[] = {"110110000", "111000000" , "011011000" , "100100100" , "010111010" , "001001001" , "000110110" , "000000111", "000011011"};
	static PrintWriter out;
		
	public static void eval(int clock, int moves, int current) {
		if(clock == 0 && current == 10) {
			String ans = "";
			String m = String.format("%9d", moves);
			for(int j = 0; j < m.length(); j++) {
				for(int i = 0; i < Character.digit(m.charAt(j), 10); i++) {
					ans += " " + (j + 1);
				}
			}
			out.println(ans.substring(1));
			out.close(); 
			System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
			System.exit(0);
		}
		if(current == 10)
			return;
		for(int i = 0; i < 4; i++) {
			eval(clock , moves*10 + i, current + 1);
			int newClock = 0;
			for(int z = 8, p10 = 1; z >= 0; --z, clock /= 10, p10 *= 10) {
				int v = clock % 10;
				if(clocks.moves[current - 1].charAt(z) == '1')
					v = (v+1)%4;
				
				newClock += v * p10;	
			}
			clock = newClock;
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("clocks.in"));
        // input file name goes above
		out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
		String clock = f.readLine();
		clock += f.readLine();
		clock += f.readLine();
		int c = Integer.parseInt(clock.replaceAll("12", "0").replaceAll(" ", ""))/3;
		eval(c,0,1);		
		f.close();
		
	}

}
