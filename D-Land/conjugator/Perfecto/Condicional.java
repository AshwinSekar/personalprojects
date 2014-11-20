package Perfecto;

import java.util.Scanner;

import Other.Function;

public class Condicional {

	private static boolean reflexive = false;

	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		if(a.endsWith("se")){
			a = a.substring(0, a.length() - 2);
			reflexive = true;
		}
		Function.viewArray(conditional(a));
	}

	public static String[] conditional(String a) {
		String[] x = new String[6];
		a = Other.Participio.participle(a);
		if(reflexive == true){
			x[0] = "me " + "habr�a " + a;
			x[1] = "te " + "habr�as " + a;
			x[2] = "se " + "habr�a " + a;
			x[3] = "nos " + "habr�amos " + a;
			x[4] = "os " + "habr�ais " + a;
			x[5] = "se " + "habr�an " + a;
		}else{
			x[0] = "habr�a " + a;
			x[1] = "habr�as " + a;
			x[2] = "habr�a " + a;
			x[3] = "habr�amos " + a;
			x[4] = "habr�ais " + a;
			x[5] = "habr�an " + a;
		}
		return x;
	}
}
