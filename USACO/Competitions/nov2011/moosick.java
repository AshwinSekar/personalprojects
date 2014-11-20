package nov2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: moosick
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;






public class moosick {
	static int chord[];
	static int a[];

	 public static int[] getNext () {
	    int temp;  
	    int j = a.length - 2;
		 while (a[j] > a[j+1]) {
		     j--;
		    }

		    // Find index k such that a[k] is smallest integer
		    // greater than a[j] to the right of a[j]

		    int k = a.length - 1;
		    while (a[j] > a[k]) {
		      k--;
		    }

		    // Interchange a[j] and a[k]

		    temp = a[k];
		    a[k] = a[j];
		    a[j] = temp;

		    // Put tail end of permutation after jth position in increasing order

		    int r = a.length - 1;
		    int s = j + 1;

		    while (r > s) {
		      temp = a[s];
		      a[s] = a[r];
		      a[r] = temp;
		      r--;
		      s++;
		    }
		    return a;

		  }
	    public static int factorial(int n)
	    {
	        int ret = 1;
	        for (int i = 1; i <= n; ++i) ret *= i;
	        return ret;
	    }
	
	public static void main(String[] args) throws IOException {
		long x = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("moosick.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moosick.out")));
		int N = Integer.parseInt(f.readLine());
		int notes[] = new int[N];
		for(int i = 0; i < N; i++) {
			notes[i] = Integer.parseInt(f.readLine());
			
		}
		int C = Integer.parseInt(f.readLine());
		chord = new int[C];
		for(int i = 0; i < C; i++) {
			chord[i] = Integer.parseInt(f.readLine());
		}	
		a = new int[chord.length];

		int nextPerm[];
		int total = 0; 
		String answer = "";
		
		for(int j= 0; j<= N - C; j++) {
			for(int i = 0; i < a.length; i++) {
				a[i] = i;
			}
			nextPerm = a;
			int transpose = chord[nextPerm[0]] - notes[j];
			boolean works = true;
			for(int k = 0; k < C; k++) {
				if(chord[nextPerm[k]] - notes[j+k] != transpose) {
					works = false;
				}
			}
			if(works) {
				//System.out.println(chord[nextPerm[0]] + " " + chord[nextPerm[1]] + " " + chord[nextPerm[2]]);
				//System.out.println(notes[j] + " " + notes[j+1] + " " + notes[j+2]);
				total++;
				answer += (j+1)+"\n";
			}
			for(int i = 0; i < factorial(chord.length) - 1; i++) {			
				
				nextPerm = getNext();
				transpose = chord[nextPerm[0]] - notes[j];
				works = true;
				for(int k = 0; k < C; k++) {
					if(chord[nextPerm[k]] - notes[j+k] != transpose) {
						works = false;
					}
				}
				if(works) {
					//System.out.println(chord[nextPerm[0]] + " " + chord[nextPerm[1]] + " " + chord[nextPerm[2]]);
					//System.out.println(notes[j] + " " + notes[j+1] + " " + notes[j+2]);
					total++;
					answer += (j+1)+"\n";
				}
					
			}
		}
		out.println(total);
		out.println(answer);
		out.close();  f.close();  f.close();f.close();f.close();
		System.out.println(System.currentTimeMillis() - x);
		
	}

}
