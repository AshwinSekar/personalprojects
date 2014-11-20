package Perfecto;

import java.util.Scanner;

import Other.Function;

public class Futuro {

	private static boolean reflexive = false;

	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		if(a.endsWith("se")){
			a = a.substring(0, a.length() - 2);
			reflexive = true;
		}
		Function.viewArray(future(a));
	}

	public static String[] future(String a) {
		String[] x = new String[6];
		a = Other.Participio.participle(a);
		if(reflexive == true){
			x[0] = "me " + "habré " + a;
			x[1] = "te " + "habrás " + a;
			x[2] = "se " + "habrá " + a;
			x[3] = "nos " + "habrémos " + a;
			x[4] = "os " + "habréis " + a;
			x[5] = "se " + "habrán " + a;
		}else{
			x[0] = "habré " + a;
			x[1] = "habrás " + a;
			x[2] = "habrá " + a;
			x[3] = "habrémos " + a;
			x[4] = "habréis " + a;
			x[5] = "habrán " + a;
		}
		return x;
	}
}
