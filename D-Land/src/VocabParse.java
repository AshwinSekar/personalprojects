import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class VocabParse {
	public static void main(String[] args) throws IOException {
		BufferedReader s = new BufferedReader(new FileReader(new File("Spanish.txt")));
		String span = s.readLine().trim().replaceAll("( )+", " ");
		int words = 0;
		while(span != null && !span.isEmpty()) {
			words++;
			span = span.trim().replaceAll("( )+", " ").replaceAll("\t","");
			System.out.println("		<verb>" + span + "</verb>");
			span = s.readLine();			
		}
	}
}
