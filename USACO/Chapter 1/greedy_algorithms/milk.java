package greedy_algorithms;
/*
ID: gaurjas1
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;


class milk {
	
	int price;
	int totalMilk;
	
	public milk(String price,String totalMilk) {
		this.price = Integer.parseInt(price);
		this.totalMilk = Integer.parseInt(totalMilk);
	}
	public static milk[] sortByPrice(milk farmers[]) {
		double n = farmers.length;
		//System.out.println(n);
		if(n == 1) {
			return farmers;
		} else {
			milk left[] = new milk[(int)Math.ceil(n/2)];
			milk right[] = new milk[(int)Math.floor(n/2)];
			for(int i = 0; i < n/2; i++)
				left[i] = farmers[i];
			for(int i = (int)Math.ceil(n/2); i < n; i++) {
				right[i-(int)Math.ceil(n/2)] = farmers[i];
			}

			left = sortByPrice(left);
			right = sortByPrice(right);
			/*for(int i = 0; i < left.length; i++) {
				System.out.print(left[i].price + " ");
			}
			System.out.print("      ");
			for(int i = 0; i < right.length; i++) {
				System.out.print(right[i].price + " ");
			}
			System.out.println();*/
			int current = 0;
			int cLeft = 0;
			int cRight = 0;
			
			while(cLeft < left.length || cRight < right.length) {
		        if(cLeft < left.length && cRight < right.length) {
		            if(left[cLeft].price <= right[cRight].price) {
		                farmers[current] = left[cLeft];
		                current++;
		                cLeft++;
		            } else {
		                farmers[current] = right[cRight];
		                current++;
		                cRight++;
		            }
		        } else if (cLeft < left.length) {
		            farmers[current] = left[cLeft];
		            current++;
		            cLeft++;
		        } else if (cRight < right.length){
		            farmers[current] = right[cRight];
		            current++;
		            cRight++;
		        }
			}
			/*for(int i = 0; i < farmers.length; i++) {
				System.out.print(farmers[i].price + " ");
			}
			System.out.println();*/
			return farmers;
		}		
	}
	public static void main (String [] args) throws IOException {
    //Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("milk.in"));
                                                  // input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		//Scanner f = new Scanner(System.in);
		StringTokenizer in = new StringTokenizer(f.readLine());
		
		int totalMilk = Integer.parseInt(in.nextToken());
		int currentMilk = 0;
		int totalPrice = 0;
		int currentFarmer = 0;
		int numFarm = Integer.parseInt(in.nextToken());
		milk farmers[] = new milk[numFarm];
		
		for(int i = 0; i < numFarm; i++) {
			in = new StringTokenizer(f.readLine());
			farmers[i] = new milk(in.nextToken(),in.nextToken());
		}
		if (numFarm > 0)
			farmers = sortByPrice(farmers);
		
		while(currentMilk < totalMilk) {
			int gallons = Math.min(farmers[currentFarmer].totalMilk,totalMilk-currentMilk);
			currentMilk += gallons;
			totalPrice += farmers[currentFarmer++].price*gallons;			
		}
		out.println(totalPrice);
		out.close();  f.close();
	}
}