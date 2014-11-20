package complete_search;
/*
ID: gaurjas1
LANG: JAVA
TASK: namenum
*/
import java.io.*;


class namenum{
	static BufferedReader g;
	static PrintWriter out;
	static boolean print;
	static String number;
	static String letters = "ABCDEFGHIJKLMNOPRSTUVWXY";
	
	static boolean[] isValid(String checkA,String checkB, String checkC) throws IOException {
		boolean valid[] = {false,false,false};
		g = new BufferedReader(new FileReader("dict.txt"));
		String currentWord = g.readLine();
		while(currentWord != null) {			
			if (currentWord.startsWith(checkA)&&(currentWord.length() == number.length())) {
				valid[0] = true;				
			} else if (currentWord.startsWith(checkB)&&(currentWord.length() == number.length())) {
				valid[1] = true;
			} else if (currentWord.startsWith(checkC)&&(currentWord.length() == number.length())) {
				valid[2] = true;
			}
			currentWord = g.readLine();			
		}
		return valid;
	}
	static void find(String soFar, int in) throws IOException{
		if(in == number.length()) {
				namenum.print = true;
				out.println(soFar);
		} else {
			char currentChar = namenum.number.charAt(in);
			int currentNum = Integer.parseInt(Character.toString(currentChar));
			boolean check[] = isValid(soFar+letters.charAt(3*(currentNum-2)), soFar+letters.charAt(3*(currentNum-2)+1), soFar+letters.charAt(3*(currentNum-2)+2));
			//System.out.println(soFar+" "+check[0]+" "+check[1]+" "+check[2]);
			if (check[0])
				find(soFar+letters.charAt(3*(currentNum-2)),in+1);
			if (check[1])
				find(soFar+letters.charAt(3*(currentNum-2)+1),in+1);
			if (check[2])
				find(soFar+letters.charAt(3*(currentNum-2)+2),in+1);
		}
	}
  public static void main (String [] args) throws IOException {
    //Use BufferedReader rather than RandomAccessFile; it's much faster
	BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
	//Scanner f = new Scanner(System.in);
	String number = f.readLine();
    namenum.number = number;
    namenum.out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
    namenum.print = false;
    namenum.find("", 0);
    if (!namenum.print)
    	out.println("NONE");
    out.close();  f.close();
  	
  }
  
}