package nov2013;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class nocow {

	public static void main(String[] args) throws IOException {
		long asdjfkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("nocow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocow.out")));
		StringTokenizer s = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(s.nextToken());
		int K = Integer.parseInt(s.nextToken());
		ArrayList<ArrayList<String>> adjectives = new ArrayList<ArrayList<String>>();
		ArrayList<String> nocows = new ArrayList<String>();
		for(int i = 0; i < 30; i++) adjectives.add(new ArrayList<String>());
		int adject = 0;
		for(int i = 0 ; i < N; i++) {
			s = new StringTokenizer(f.readLine());
			s.nextToken();s.nextToken();s.nextToken();s.nextToken();
			String str;
			String no = "";
			for(int j = 0; !(str = s.nextToken()).equals("cow."); j++) {
				no += str;
				if(!adjectives.get(j).contains(str)) adjectives.get(j).add(str);
				adject = j+1;
			}
			nocows.add(no);
		}
		
		int totalSize = 1;
		for(ArrayList<String> l : adjectives) {
			Collections.sort(l);
			if(l.size() != 0)
				totalSize *= l.size();
		}
		//System.out.println(adjectives);
		
		int runningSize = totalSize;
		int j;
		int slice = 0;
		String ans = "";
		for(int i = 0; i < adject; i++) {
			//System.out.println(ans);
			runningSize = runningSize/adjectives.get(i).size();
			//System.out.println("RUN" + runningSize);
			for(j = 0; K > 0; j++) {
				//System.out.println("K" + K);
				slice = runningSize;
				for(String str : nocows) {
					if(str.startsWith(ans + adjectives.get(i).get(j)))
						slice--;
				}
				//System.out.println("SLICE" + slice);
				K = K-slice;
			}
			K = K + slice;
			ans += " " + adjectives.get(i).get(j-1);
		}
		
		out.println(ans.substring(1));
		out.close();
		f.close();  
		System.out.println((System.currentTimeMillis() - asdjfkl)/1000.0);
	}
}
