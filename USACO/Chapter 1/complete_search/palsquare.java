package complete_search;
/*
ID: gaurjas1
LANG: JAVA
TASK: palsquare
*/
import java.io.*;


class palsquare {
	
	public static boolean isPalindrome(String pal) {
		for(int i = 0; i < Math.floor(pal.length()/2); i++) {
			if(pal.charAt(i) != pal.charAt(pal.length()-i-1))
				return false;				
		}
		return true;
	}
	public static void main (String [] args) throws IOException {
    //Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
                                                  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		//Scanner f = new Scanner(System.in);
		int base = Integer.parseInt(f.readLine());
		for(int n = 1; n <= 300; n++) {
			String squareBase = Integer.toString(n*n,base);
			if(isPalindrome(squareBase)) {
				out.println(Integer.toString(n,base).toUpperCase() + " " + (squareBase.toUpperCase()));
			}
		}
		out.close();  f.close();
	}
}