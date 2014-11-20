/*
ID: gaurjas1
LANG: JAVA
TASK: beads
*/
import java.io.*;


class beads {
	
  public static void main (String [] args) throws IOException {
    //Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("beads.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
    int length =Integer.parseInt(f.readLine());
    String necklace = f.readLine();
    int beads = 0;
    int currentBeads;
    char letter;
    beads b = new beads();
    for(int i=0;i<length;i++){
    	letter = necklace.charAt(i);
    	currentBeads = b.determineBeads(necklace,letter,i);
    	if (currentBeads>beads){
    		beads=currentBeads;
    	}
    }
    out.println(beads);
    out.close();                                  // close the output file
    f.close();
    System.exit(0);                               // don't omit this!
  }
  public int determineBeads(String necklace, char letter,int i){
	   if (letter!='w')	{
		  int j=0;
		  int k=0;
		  while(((necklace.charAt((i+j)%necklace.length())=='w')||(necklace.charAt((i+j)%necklace.length())==letter))&&((j)<necklace.length())){
			  j++;
		  }
		  System.out.println(j);
		  letter = necklace.charAt(((i-1)%necklace.length()<0)?(i-1)%necklace.length()+necklace.length():(i-1)%necklace.length());
		  System.out.println(letter);
		  if (letter!='w'){
			  while(((necklace.charAt(((i-k-1)%necklace.length()<0)?(i-k-1)%necklace.length()+necklace.length():(i-k-1)%necklace.length())=='w')||(necklace.charAt(((i-k-1)%necklace.length()<0)?(i-k-1)%necklace.length()+necklace.length():(i-k-1)%necklace.length())==letter))&&((k)<necklace.length()-j)){
			  k++;
		  }  
			//k++;
		  } else {
			  letter = 'r';
			  while(((necklace.charAt(((i-k-1)%necklace.length()<0)?(i-k-1)%necklace.length()+necklace.length():(i-k-1)%necklace.length())=='w')||(necklace.charAt(((i-k-1)%necklace.length()<0)?(i-k-1)%necklace.length()+necklace.length():(i-k-1)%necklace.length())==letter))&&((k)<necklace.length()-j)){
				  k++;
			  }  
			  //k--;
			  int temp1 = k;
			  k=0;
			  letter = 'b';
			  while(((necklace.charAt(((i-k-1)%necklace.length()<0)?(i-k-1)%necklace.length()+necklace.length():(i-k-1)%necklace.length())=='w')||(necklace.charAt(((i-k-1)%necklace.length()<0)?(i-k-1)%necklace.length()+necklace.length():(i-k-1)%necklace.length())==letter))&&((k)<necklace.length()-j)){
				  k++;
			  }
			  //k--;
			  k = Math.max(k,temp1);
		  }
		  System.out.println(k);
		  System.out.println(i);
		  System.out.println(" ");
		  return j+k;
	  } else {
		  int temp1 = this.determineBeads(necklace, 'r', i);
		  int temp2 = this.determineBeads(necklace, 'b', i);
		  return Math.max(temp1, temp2);
	  }
	 
  }
}