package complete_search;
/*
ID: gaurjas1
LANG: JAVA
TASK: transform
*/
import java.io.*;


class transform {
	
	char[][] before;
	char[][] after;
	int n;
	
	public transform(char[][] before, char[][] after, int n) {
		this.before = new char[n][n];
		this.after = new char[n][n];
		this.before = before;
		this.after = after;
		this.n = --n;
	}
	
	public boolean isClockwise() {
		for(int i = 0; i<= n; i++) {
			for(int j = 0; j <=n; j++) {
				
				if (before[i][j] != after[j][n-i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean is180() {
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <=n; j++) {
				if (before[i][j] != after[n-i][n-j]) {
					
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isCClockwise() {
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <=n; j++) {
				if (before[i][j] != after[n-j][i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isReflection() {
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <=n; j++) {
				if (before[i][j] != after[i][n-j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public boolean isCombination() {
		char[][] horizontalFlip = new char[n+1][n+1];
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j<= n; j++) {
				horizontalFlip[i][j] = before[i][n-j];
			}
		}
		transform combination = new transform(horizontalFlip,after,n+1);
		return combination.isClockwise() || combination.isCClockwise() || combination.is180();
	}
	
	public boolean isSame() {
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <=n; j++) {
				if (before[i][j] != after[i][j]) {
					return false;
				}					
			}
		}
		return true;
	}
	
	
  public static void main (String [] args) throws IOException {
    //Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("transform.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
    //Scanner f = new Scanner(System.in);
    int n = Integer.parseInt(f.readLine());
    char before[][] = new char[n][n];
    char after[][] = new char[n][n];
    
    for(int i = 0; i <n; i++) {
    	before[i] = f.readLine().toCharArray();
    }
    for(int i = 0; i <n; i++) {
    	after[i] = f.readLine().toCharArray();
    }
   
    transform square = new transform(before,after,n);
    
    if (square.isClockwise()) {
    	out.println("1");
    } else if (square.is180()) {
    	out.println("2");
    } else if (square.isCClockwise()) {
    	out.println("3");
    } else if (square.isReflection()) {
    	out.println("4");
    } else if (square.isCombination()) {
    	out.println("5");
    } else if (square.isSame()) {
    	out.println("6");
    } else {
    	out.println("7");
    }
    out.close();  f.close();
  	
  }
  
}