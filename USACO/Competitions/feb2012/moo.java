package feb2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class moo {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("moo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moo.out")));
		int N = Integer.parseInt(f.readLine());
		boolean[] m = new boolean[N + 1];
		m[1] = true;
		int length = 3;
		int count = 4;
		while(length < N) {	
			if(2*length > N) {
				if(N - length - count > -1 && m[N - length - count] || N == length + 1) {
					out.println('m');
					out.close();  f.close();
					System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
					System.exit(1);
				}
				out.println('o');
				out.close();  f.close();
				System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
				System.exit(1);
			}
			for(int i = 0; i < length; i++) {
				m[i + length + count] = true;
			}
			m[length + 1] = true;
			length = length*2 + count;
			count++;
		}
		out.println(m[N]?'m':'o');
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}

}
