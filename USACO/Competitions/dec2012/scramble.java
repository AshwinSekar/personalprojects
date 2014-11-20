package dec2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class scramble {
	String normal;
	String least;
	String greatest;
	int low = 0;
	int great = 0;
	
	public scramble(String n) {
		normal = n;
		char[] temp = normal.toCharArray();
		Arrays.sort(temp);
		least = String.valueOf(temp);
		greatest = new StringBuffer(least).reverse().toString();
	}
	

	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("scramble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scramble.out")));
		int N = Integer.parseInt(f.readLine());
		scramble[] names = new scramble[N];
		scramble[] least = new scramble[N];
		scramble[] greatest = new scramble[N];
		scramble s;
		for(int i = 0; i < N; i++) {
			s = new scramble(f.readLine());
			names[i] = s;
			least[i] = s;
			greatest[i] = s;
		}
		Arrays.sort(least,new Comparator<scramble>() {

			@Override
			public int compare(scramble o1, scramble o2) {
				return o1.least.compareTo(o2.least);
			}
		});
		Arrays.sort(greatest,new Comparator<scramble>() {

			@Override
			public int compare(scramble o1, scramble o2) {
				return o1.greatest.compareTo(o2.greatest);
			}
		});
		int j = 0;
		String s1;
		for(int i = 0; i < N; i++) {
			s1 = least[i].greatest;
			for(j = i+1; j < N; j++) {
				if(s1.compareTo(least[j].least) < 0) {
					break;
				}
			}
			least[i].great = j;
		}
		for(int i = 0; i < N; i++) {
			s1 = greatest[i].least;
			for(j = i-1; j >= 0; j--) {
				if(s1.compareTo(greatest[j].greatest) > 0) {
					break;
				}
			}
			greatest[i].low = j + 2;
		}
		for(int i = 0; i < N; i++) {
			out.println(names[i].low + " " + names[i].great);
		}
		/*for(int i = 0; i < N; i++) {
			System.out.println(names[i].normal);
		}
		for(int i = 0; i < N; i++) {
			System.out.println(least[i].least);
		}
		for(int i = 0; i < N; i++) {
			System.out.println(greatest[i].greatest);
		}*/		
		f.close();
		out.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
	}
}
