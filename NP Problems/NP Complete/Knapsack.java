import java.util.*;

public class Knapsack {
	Item items[];
	static int table[];

	public static class Item {
		int nutrients;
		int price;
		
		public Item(int p, int n) {
			nutrients = n;
			price = p;
		}
	}
	
	public Knapsack(Item i[]) {
		items = i.clone();
	}
	
	private int optimize(int totalPrice) {
		if(totalPrice < 0) {
			return 0;
		}
		if(table[totalPrice] >= 0) {
			return table[totalPrice];
		}
		int j = 0;
		for(int i =0; i < items.length; i++) {
			if(totalPrice-items[i].price >= 0 && (items[i].nutrients + optimize(totalPrice-items[i].price)) > j) {
				j = items[i].nutrients + optimize(totalPrice-items[i].price);
			}
		}
		table[totalPrice] =  Math.max(optimize(totalPrice-1), j);
		return table[totalPrice];
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("How many items on the menu?:");
		int N = Integer.parseInt(in.nextLine());
		
		Item i[] = new Item[N];
		for(int j =0; j < N; j++) {
			System.out.print("Entree number " + (j + 1) + ". Price Nutrients:");
			i[j] = new Item(in.nextInt(), in.nextInt());
		}
		System.out.println();
		in.nextLine();
		while(true) {
		System.out.print("Budget?:");
		int price = Integer.parseInt(in.nextLine());
		
		Knapsack.table = new int[price+1];
		Arrays.fill(Knapsack.table, -1);
		
		Knapsack k = new Knapsack(i);
		System.out.println(k.optimize(price) + " mg is the maximum nutrients possible");
		}

	}

}
