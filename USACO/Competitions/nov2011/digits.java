package nov2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: digits
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class digits {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("digits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("digits.out")));
		String base2 = new String(f.readLine());
		String base3 = new String(f.readLine());
		int N2,N3;
		
		for(int i = 0; i < base2.length(); i++) {
			for(int j = 0; j < base3.length(); j++) {
				if(Integer.parseInt(base2.charAt(i) + "") == 0)
					N2 = Integer.parseInt(base2.substring(0, i) + "1" + base2.substring(i+1),2);
				else
					N2 = Integer.parseInt(base2.substring(0, i) + "0" + base2.substring(i+1),2);
				
				if(Integer.parseInt(base3.charAt(j) + "") == 0) {
					N3 = Integer.parseInt(base3.substring(0, j) + "1" + base3.substring(j+1),3);
					if(N3 == N2) {
						out.println(N2);
						out.close();  f.close();  f.close();f.close();f.close();
						return;
					}
					N3 = Integer.parseInt(base3.substring(0, j) + "2" + base3.substring(j+1),3);
					if(N3 == N2) {
						out.println(N2);
						out.close();  f.close();  f.close();f.close();f.close();
						return;
					}
					
				} else if(Integer.parseInt(base3.charAt(j) + "") == 1) {
					N3 = Integer.parseInt(base3.substring(0, j) + "0" + base3.substring(j+1),3);
					if(N3 == N2) {
						out.println(N2);
						out.close();  f.close();  f.close();f.close();f.close();
						return;
					}
					N3 = Integer.parseInt(base3.substring(0, j) + "2" + base3.substring(j+1),3);
					if(N3 == N2) {
						out.println(N2);
						out.close();  f.close();  
						return;
					}
				} else {
					N3 = Integer.parseInt(base3.substring(0, j) + "0" + base3.substring(j+1),3);
					if(N3 == N2) {
						out.println(N2);
						out.close();  f.close();  
						return;
					}
					N3 = Integer.parseInt(base3.substring(0, j) + "1" + base3.substring(j+1),3);
					if(N3 == N2) {
						out.println(N2);
						out.close();  f.close();  
						return;
					}
				}
			}				
		}
		out.close();
		f.close();
	}

}
