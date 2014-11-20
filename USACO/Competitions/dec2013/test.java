package dec2013;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("vacation.in")));
		out.println("200 10000 1 10000");
		Random r = new Random();
		for(int i = 0 ; i < 10000; i++) {
			out.println((r.nextInt(200)+1) + " " + (r.nextInt(200)+1) + " " + (r.nextInt(100000)+1));
		}
		for(int i = 0; i < 10000; i++) {
			out.println((r.nextInt(200)+1) + " " + (r.nextInt(200)+1));
		}
		out.close();
	}

}
