package binary_numbers;
/*
ID: gaurjas1
LANG: JAVA
TASK:  pprime
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class pprime {

	public static boolean isPrime(long N) {
		for(long i = 2; i <= Math.sqrt(N); i++) {
			if(N % i == 0) {
				return false;
			}
		}
		return true;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		StringTokenizer in = new StringTokenizer(f.readLine());
		long A = Integer.parseInt(in.nextToken());
		long B = Integer.parseInt(in.nextToken());
		ArrayList<Long> pprime = new ArrayList<Long>();
		
		for(long i = 5; i < 10; i += 2) {
			if(isPrime(i))
				pprime.add(i);
		}
		
		for(long i = 1; i < 10; i += 2) {
			if(isPrime(10*i + i)) {
				pprime.add(10*i + i);
			}
		}
		
		for(long i = 1; i < 10; i += 2) {
			for(long j = 0; j < 10; j++) {
				if(isPrime(100*i + 10*j + i))
					pprime.add(100*i+10*j+i);
			}
		}
		
		for(long i = 1; i < 10; i += 2) {
			for(long j = 0; j < 10; j++) {
				if(isPrime(1000*i + 100*j + 10*j + i))
					pprime.add(1000*i+100*j+ 10*j + i);
			}
		}
		
		for(long i = 1; i < 10; i += 2) {
			for(long j = 0; j < 10; j++) {
				for(long k = 0; k < 10; k++) {
					if(isPrime(10000*i + 1000*j + 100*k + 10*j + i))
						pprime.add(10000*i + 1000*j + 100*k + 10*j + i);
				}
			}
		}
		
		for(long i = 1; i < 10; i += 2) {
			for(long j = 0; j < 10; j++) {
				for(long k = 0; k < 10; k++) {					
					if(isPrime(100000*i + 10000*j + 1000*k + 100*k + 10*j + i)) 
						pprime.add(100000*i + 10000*j + 1000*k + 100*k + 10*j + i);					
				}
			}
		}
		for(long i = 1; i < 10; i += 2) {
			for(long j = 0; j < 10; j++) {
				for(long k = 0; k < 10; k++) {
					for(long l = 0; l < 10; l++) {
						if(isPrime(1000000L*i + 100000*j + 10000*k + 1000*l + 100*k + 10*j + i)) 
							pprime.add(1000000L*i + 100000*j + 10000*k + 1000*l + 100*k + 10*j + i);	
					}
				}
			}
		}
		for(long i = 1; i < 10; i += 2) {
			for(long j = 0; j < 10; j++) {
				for(long k = 0; k < 10; k++) {
					for(long l = 0; l < 10; l++) {
						if(isPrime(10000000*i + 1000000*j + 100000*k  + 10000*l + 1000*l + 100*k + 10*j + i)) 
							pprime.add(10000000*i + 1000000*j + 100000*k + 10000*l + 1000*l + 100*k + 10*j + i);		
					}
				}
			}
		}
		for(long x: pprime) {
			if(x >= A && x <= B)
				out.println(x);
		}
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}

}
