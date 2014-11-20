package data_structures;

/*
ID: gaurjas1
LANG: JAVA
TASK: concom
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class concom {

	public static void main(String args[]) throws IOException {
		System.out.println("6508c93188da36bdc107ceacd0040ff7".length());
		long asdfjkl = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		
		out.close();  f.close();
		System.out.println((System.currentTimeMillis() - asdfjkl)/1000.0);
	}	
	
}
