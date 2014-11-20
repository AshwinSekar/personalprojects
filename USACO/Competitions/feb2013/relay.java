package feb2013;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class relay {
	

	static int nonloopy;
	static HashMap<Integer, ArrayList<Integer>> fwd;
	
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("relay.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("relay.out")));
		int N = Integer.parseInt(f.readLine());
		fwd = new HashMap<Integer, ArrayList<Integer>>();
		int val;
				
		for(int i = 1; i <= N; i++) {
			val = Integer.parseInt(f.readLine());
			if(fwd.containsKey(val)) {
				fwd.get(val).add(i);
			} else {
				fwd.put(val, new ArrayList<Integer>());
				fwd.get(val).add(i);
			}
		}
		
		nonloopy = 0;
		if(fwd.containsKey(0))
			loopyfy(fwd.get(0));
		
		out.println(nonloopy);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}
	
	public static void loopyfy(ArrayList<Integer> a) {
		for(int i : a) {
			nonloopy++;
			if(fwd.containsKey(i))
				loopyfy(fwd.get(i));
		}
	}

}
