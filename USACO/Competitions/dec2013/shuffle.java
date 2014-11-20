package dec2013;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class shuffle {
	static int N;
	static int M;
	static int Q;
	static ArrayList<Integer> cards;
	static int[] shuffle;
	static int[] shuffledCards;
	
	public static void shuffleCards() {
		for(int i = 0; i < N; i++) {
			if(cards.size() >= M) bessie();
			shuffledCards[cards.size()-1] = cards.remove(0);
			
		}
	}
	
	public static void bessie() {
		Integer temp;
		ArrayList<Integer> t = new ArrayList<Integer>(cards);
		for(int i = 0; i < M; i++) {
			temp = cards.get(i);
			t.set(shuffle[i], temp);
		}
		cards.clear();
		cards.addAll(t);
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long asdjfkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		StringTokenizer s = new StringTokenizer(f.readLine());
		N = Integer.parseInt(s.nextToken());
		M = Integer.parseInt(s.nextToken());
		Q = Integer.parseInt(s.nextToken());
		
		cards = new ArrayList<Integer>();
		for(int i = 1; i <= N; i++) cards.add(i);
		shuffle = new int[M];
		for(int i = 0; i < M; i++) shuffle[i] = Integer.parseInt(f.readLine()) -1;
		shuffledCards = new int[N];
		
		shuffleCards();
		
		for(int i = 0; i < Q; i++) out.println(shuffledCards[Integer.parseInt(f.readLine())-1]);
		out.close();
		f.close();  
		System.out.println((System.currentTimeMillis() - asdjfkl)/1000.0);

	}

}
