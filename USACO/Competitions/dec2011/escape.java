//TODO
package dec2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: escape
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;



public class escape {
	
	
	public static long add(long s[]) {
		long ans = 0;
		int d;
		double asdf = Math.log10(s[s.length-1]);
		for(int i  = 0; i <= asdf; i++) {
			d = 0; 
			for(int j = 0; j < s.length; j++) {
				int c = (int) s[j] % 10;
				d += c;				
				s[j] = (s[j] - c)/10;
			}
			if(d != (d%10))
				return -1;
			ans += d*Math.pow(10,i);
		}
		return ans;		
	}
	
	static class CombinationGenerator {

		  private int[] a;
		  private int n;
		  private int r;
		  private int x;

		  public CombinationGenerator (int n, int r) {
		    this.n = n;
		    this.r = r;
		    a = new int[r];
		    for (int i = 0; i < a.length; i++) {
			      a[i] = i;
			}
		    x = 1;
		  }

		  public boolean hasMore() {
			  return a[0] != n-r;
		  }
		  //--------------------------------------------------------
		  // Generate next combination (algorithm from Rosen p. 286)
		  //--------------------------------------------------------

		  public int[] getNext () {

		    if (x == 1) {
		      x++;
		      return a;
		    }
		    
		    int i = r - 1;
		    while (a[i] == n - r + i) {
		      i--;
		    }
		    a[i] = a[i] + 1;
		    for (int j = i + 1; j < r; j++) {
		      a[j] = a[i] + j - i;
		    }

		    return a;

		  }
		}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("escape.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("escape.out")));
		int N = Integer.parseInt(f.readLine());
		long weights[] = new long[N];
		for(int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(f.readLine());
		}
		CombinationGenerator x;
		for(int i = N; i > 0; i--) {
			x = new CombinationGenerator(N,i);
			while(x.hasMore()) {
				int comb[] = x.getNext();
				long add[] = new long[comb.length];
				for(int z = 0; z < comb.length; z++) {
					add[z] = weights[comb[z]];
				}
				Arrays.sort(add);
				long addition = add(add);
				if(addition != -1) {
					out.println(i);
					out.close();  f.close();
					//System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.);
					System.exit(0);
				}
			}
		}
		
		
	}
}
