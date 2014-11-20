package greedy_algorithms;
/*
ID: gaurjas1
LANG: JAVA
TASK: calfflac
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class calfflac {
	static int hashtable[];
	
	public static String removePunc(String str) {
		String text = str.replaceAll("[^\\p{L}]", "");
		int index = 0;
		int i = 0;
		for(i = 0; i < text.length(); i++) {
			while(text.charAt(i) != str.charAt(index)) {
				index++;
			}
			hashtable[i] = index;
			index++;
		}
		hashtable[i] = index;
		return text.toLowerCase();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("calfflac.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
		char in[] = new char[20000];
		f.read(in);
		String text = String.valueOf(in);
		int max = 1;
		int length = 0;
		int index = 1;
		hashtable = new int[text.length()];
		String str = text;
		text = removePunc(text);

		for (int i = 0; i < text.length(); i++) {
			while(i-length >= 0 && i+length < text.length() && text.charAt(i - length) == text.charAt(i+length)) {
				length++;				
			}
			length--;
			if(2*length+1 > max) {
				index = i-length;
				max = length*2+1;
			}
			length = 1;
			while(i-length >= 0 && i+length - 1 < text.length() && text.charAt(i - length) == text.charAt(i+length - 1)) {
				length++;
			}
			length--;
			if(2*length > max) {
				index = i-length;
				max = length*2;
			}
			length = 0;
		}
		out.println(max);
		out.println(str.substring(hashtable[index], hashtable[index + max-1]+1));
		out.close();  f.close();
	}
}