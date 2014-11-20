package binary_numbers;
/*
ID: gaurjas1
LANG: JAVA
TASK:  sprime
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class sprime {
	static int len;
	static ArrayList<Long> sprime;

	public static boolean isPrime(long N) {
		for(long i = 2; i <= Math.sqrt(N); i++) {
			if(N % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void recurse(long N) {
		if((int)Math.log10(N) == len - 1) {
			sprime.add(N);
			return;
		}
		for(int i = 1; i < 10; i+=2) {
			if(isPrime(10*N + i))
				recurse(10*N + i);
		}
	}

	
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		len = Integer.parseInt(f.readLine());
		sprime = new ArrayList<Long>();
		recurse(2);
		recurse(3);
		recurse(5);
		recurse(7);
		Collections.sort(sprime);
		for(long x : sprime) {
			out.println(x);
		}
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);
	}

}
