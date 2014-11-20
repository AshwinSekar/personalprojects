package nov2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: ctiming
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;



public class ctiming {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ctiming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ctiming.out")));
		StringTokenizer in = new StringTokenizer(f.readLine());
		int days = Integer.parseInt(in.nextToken());
		int hours = Integer.parseInt(in.nextToken());
		int minutes = Integer.parseInt(in.nextToken());
		int daysPast = days - 11;
		int hoursPast = hours - 11;
		int minutesPast = minutes - 11;
		int totalTimePast = daysPast*24*60 + hoursPast*60 + minutesPast;
		if(totalTimePast >= 0)
			out.println(totalTimePast);
		else
			out.println(-1);
		out.close();  f.close();  
	}

}
