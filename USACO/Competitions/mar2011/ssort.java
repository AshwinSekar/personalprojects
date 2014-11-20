package mar2011;
/*
ID: gaurjas1
LANG: JAVA
TASK: ssort
*/
import java.io.*;

class ssort{
	int sum;
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("ssort.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ssort.out")));
    int n = (int)Math.pow((double)2,(double)(Integer.parseInt(f.readLine())));
    //out.println(n);
    //int n=8;
    int cows[] = new int[n+1];
    //int cows[] = {0,8,5,2,3,4,7,1,6};
    for (int i=1;i<n+1;i++){
    	cows[i]=Integer.parseInt(f.readLine());
    }
    //int sum = 0;
    cows = sort(cows,1,8,n);
    //out.println(n);
    out.println(cows[0]);
    for(int i=1;i<n+1;i++){
    	out.println(cows[i]);
    }
    out.close();  f.close();                                // close the output file
    System.exit(0);                               // don't omit this!
  }
  public static int[] sort(int[] cows,int i,int j,int n){
	  if (n!=1){
		  cows=sort(cows,i,i+(n/2)-1,n/2);
		  cows=sort(cows,i+(n/2),i+n-1,n/2);
		  int k =0;
		  while(cows[i+k]==cows[i+(n/2)+k]&&(i+k!=i+(n/2))){
			  k++;
		  }
		  //System.out.println(i+" "+j+" "+n);
		  /*for(int l=i;l<j+1;l++){
		    	System.out.println(cows[l]);
		  }*/
		  
		  boolean sort = (cows[i+k]>cows[i+(n/2)+k]);  
		  //System.out.println(sort);
		  if (sort){
			  int temp[] = new int[i+(n/2)+k];
			  for(int x=0;x<i+(n/2)+k-i;x++){
				  temp[x]=cows[i+x];
				  cows[i+x]=cows[i+(n/2)+k+x];
				  cows[(i+(n/2)+k+x)]=temp[x];
			  }
			  cows[0] += (n*n)/2;
			  //System.out.println(n+" "+cows[0]);
		  }
		  /*for(int l=i;l<j+1;l++){
		    	//System.out.println(cows[l]);
		  }*/
	  } else {
		  return cows;
	  }
	  return cows;
  }
}