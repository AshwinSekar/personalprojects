package Perfecto;

import java.util.Scanner;

import Other.Function;

public class Presente {

	private static boolean reflexive = false;

	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		if(a.endsWith("se")){
			a = a.substring(0, a.length() - 2);
			reflexive = true;
		}
		Function.viewArray(present(a));
	}

	public static String[] present(String a) {
		String[] x = new String[6];
		a = Other.Participio.participle(a);
		if(reflexive == true){
			x[0] = "me " + "he " + a;
			x[1] = "te " + "has " + a;
			x[2] = "se " + "ha " + a;
			x[3] = "nos " + "hemos " + a;
			x[4] = "os " + "habéis " + a;
			x[5] = "se " + "han " + a;
		}else{
			x[0] = "he " + a;
			x[1] = "has " + a;
			x[2] = "ha " + a;
			x[3] = "hemos " + a;
			x[4] = "habéis " + a;
			x[5] = "han " + a;
		}
		return x;
	}
}
