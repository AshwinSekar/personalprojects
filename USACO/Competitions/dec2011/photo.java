//TODO
package dec2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: photo
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;



public class photo {
	
	
	static HashSet<String> get(int[] order) {
		HashSet<String> o = new HashSet<String>();
		String s = "";
		for(int i = 0; i < order.length; i++) {
			s += order[i];
		}
		o.add(s);
		char[] a; 
		
		for(int i = 0; i < order.length; i++) {
			for(int j = i+1;j < order.length; j++) {
				a = s.toCharArray();
				char temp = a[i];
				for(int k = i; k < j; k++) {
					a[k] = a[k+1];
				}
				a[j] = temp;
				o.add(new String(a));
			}
		}
		return o;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("photo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));
		int N = Integer.parseInt(f.readLine());
		int[] photo = new int[N];
		
		for(int i = 0; i < N; i++) {
			photo[i] = Integer.parseInt(f.readLine().trim());
		}
		HashSet<String> master = get(photo);
		
		for(int x = 0; x < 4; x++) {
			for(int i = 0; i < N; i++) {
				photo[i] = Integer.parseInt(f.readLine().trim());
			}
			master.retainAll(get(photo));
		}
		
		String i = master.toArray(new String[1])[0];
		for(int j = 0; j < i.length(); j++) {
			out.println(i.charAt(j));
		}
		out.close();  f.close();
		System.exit(0);
	}
}
