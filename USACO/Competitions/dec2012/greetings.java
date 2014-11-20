package dec2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class greetings {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("greetings.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("greetings.out")));
		StringTokenizer s = new StringTokenizer(f.readLine());
		int B = Integer.parseInt(s.nextToken());
		int E = Integer.parseInt(s.nextToken());
		
		int[] Bstep = new int[B];
		boolean[] Bdir = new boolean[B];
		int[] Estep = new int[E];
		boolean[] Edir = new boolean[E];
		
		for(int i = 0; i < B; i++) {
			s = new StringTokenizer(f.readLine());
			Bstep[i] = Integer.parseInt(s.nextToken());
			Bdir[i] = s.nextToken().equals("R") ? true : false;
		}
		for(int i = 0; i < E; i++) {
			s = new StringTokenizer(f.readLine());
			Estep[i] = Integer.parseInt(s.nextToken());
			Edir[i] = s.nextToken().equals("R") ? true : false;
		}
		boolean Bdone = false;
		boolean Edone = false;
		int Bpos = 0;
		int Epos = 0;
		int Bindex = 0;
		int Eindex = 0;
		int Bcounter = 0;
		int Ecounter = 0;
		int ans = 0;
		boolean prev = false;
		
		while(!Bdone || !Edone) {
			if(!Bdone) {
				if(Bdir[Bindex]) {
					Bpos++;
				} else {
					Bpos--;
				}
				Bcounter++;
				if(Bcounter == Bstep[Bindex]) {
					Bcounter = 0;
					Bindex++;
					if(Bindex >= B) Bdone = true;
				}
			}
			
			if(!Edone) {
				if(Edir[Eindex]) {
					Epos++;
				} else {
					Epos--;
				}
				Ecounter++;
				if(Ecounter == Estep[Eindex]) {
					Ecounter = 0;
					Eindex++;
				}
				if(Eindex >= E) Edone = true;
			}
			if(Bpos == Epos) {
				if(!prev) ans++;
				prev = true;
			} else {
				prev = false;
			}
		}
		
		out.println(ans);
		f.close();
		out.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
	}

}
