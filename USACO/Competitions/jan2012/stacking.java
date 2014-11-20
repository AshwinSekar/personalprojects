package jan2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
ID: gaurjas1
LANG: JAVA
TASK: gifts
*/
public class stacking {

	static class Range {
		int low;
		int high;
		int times;
		
		public Range(int l,int h, int t) {
			low = l;
			high = h;
			times = t;
		}
		
		@Override
		public String toString() {
			return low + "-" + high + "x" + times;
		}
	}
	public static void main(String[] args) throws IOException {
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("stacking.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stacking.out")));
		StringTokenizer in = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(in.nextToken());
		int K = Integer.parseInt(in.nextToken());
		//int stacks[] = new int[N];
		ArrayList<Range> stacks = new ArrayList<Range>();
		int freq[] = new int[K+1];
		int a;
		int b;
		in = new StringTokenizer(f.readLine());
		a = Integer.parseInt(in.nextToken());
		b = Integer.parseInt(in.nextToken());
		boolean add;
		stacks.add(new Range(a,b,1));
		for(int i = 1; i < K; i++) {
			in = new StringTokenizer(f.readLine());
			a = Integer.parseInt(in.nextToken());
			b = Integer.parseInt(in.nextToken());
			add = true;
			for(int j = 0; j < stacks.size(); j++) {			
				//System.out.println(a + " " + b);
				if(stacks.get(j).low == b + 1 && stacks.get(j).times == 1) {
					b = stacks.get(j).high;
					stacks.remove(j);
					j--;
				} else if(stacks.get(j).high == a - 1 && stacks.get(j).times == 1) {
					a = stacks.get(j).low;;
					stacks.remove(j);
					j--;
				} else if(stacks.get(j).low == a && stacks.get(j).high == b) {
					stacks.add(new Range(stacks.get(j).low , stacks.get(j).high, stacks.get(j).times + 1));
					stacks.remove(j);
					j--;
					add = false;
					break;
				} else if(stacks.get(j).low < a && stacks.get(j).high > a && stacks.get(j).low  < b && stacks.get(j).high > b) {
					stacks.add(new Range(stacks.get(j).low , a , stacks.get(j).times));
					stacks.add(new Range(stacks.get(j).high , b , stacks.get(j).times));
					stacks.add(new Range(a , b , stacks.get(j).times + 1));
					stacks.remove(j);
					j--;
					add = false;
					break;					
				} else if(stacks.get(j).low <= a && stacks.get(j).high >= a) {
					stacks.add(new Range(stacks.get(j).low , a-1 , stacks.get(j).times));
					stacks.add(new Range(a , stacks.get(j).high , stacks.get(j).times + 1));
					a = stacks.get(j).high + 1;
					stacks.remove(j);
					j--;
				} else if(stacks.get(j).low <= b && stacks.get(j).high >= b) {
					stacks.add(new Range(b + 1 , stacks.get(j).high , stacks.get(j).times));
					stacks.add(new Range(stacks.get(j).low , b , stacks.get(j).times + 1));
					b = stacks.get(j).low - 1;
					stacks.remove(j);
					j--;
				}				
			}
			if(add)
				stacks.add(new Range(a , b ,1));

		}
		//System.out.println(stacks);
		int sum = 0;
		for(Range c : stacks) {
			sum += c.high - c.low + 1;
			freq[c.times] += c.high - c.low + 1; 
		}
		freq[0] = N - sum;
		//System.out.println(Arrays.toString(freq));
		sum = 0;
		int i;
		for(i = 0; sum <= N/2; i++) {
			sum += freq[i];
		}
		out.println(i - 1);
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl) / 1000.0);

	}

}
