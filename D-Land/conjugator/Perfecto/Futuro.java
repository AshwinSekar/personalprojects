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
			x[0] = "me " + "habr� " + a;
			x[1] = "te " + "habr�s " + a;
			x[2] = "se " + "habr� " + a;
			x[3] = "nos " + "habr�mos " + a;
			x[4] = "os " + "habr�is " + a;
			x[5] = "se " + "habr�n " + a;
		}else{
			x[0] = "habr� " + a;
			x[1] = "habr�s " + a;
			x[2] = "habr� " + a;
			x[3] = "habr�mos " + a;
			x[4] = "habr�is " + a;
			x[5] = "habr�n " + a;
		}
		return x;
	}
}
