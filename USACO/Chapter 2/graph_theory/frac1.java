package graph_theory;
/*
ID: gaurjas1
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class frac1 implements Comparable<frac1>{
	
	int a;
	int b;
	
	public frac1(int a,int b) {
		this.a = a;
		this.b = b;
	}
	
	private double getVal() {
		return ((double)a)/b;
	}
	
	@Override
	public String toString() {
		return a + "/" + b;
	}
	
	@Override
	public int hashCode() {
		return (int) (this.getVal() * 100000);
	}
	
	@Override
	public boolean equals(Object o) {
		return this.getVal() == ((frac1)o).getVal();		
	}

	@Override
	public int compareTo(frac1 o) {
		return (int) (this.hashCode() - o.hashCode());
	}
	public static void main (String [] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		int N = Integer.parseInt(f.readLine());
		HashSet<frac1> ans = new HashSet<frac1>();
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= i; j++) {
				ans.add(new frac1(j,i));
			}
		}
		ArrayList<frac1> o = new ArrayList<frac1>(ans);
		Collections.sort(o);
		for(frac1 s : o) {
			out.println(s);
		}
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}
	

}