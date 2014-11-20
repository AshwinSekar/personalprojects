package Perfecto;

import java.util.Scanner;

import Other.Function;

public class Imperfecto {

	private static boolean reflexive = false;

	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		if(a.endsWith("se")){
			a = a.substring(0, a.length() - 2);
			reflexive = true;
		}
		Function.viewArray(imperfect(a));
	}

	public static String[] imperfect(String a) {
		String[] x = new String[6];
		a = Other.Participio.participle(a);
		if(reflexive == true){
			x[0] = "me " + "había " + a;
			x[1] = "te " + "habías " + a;
			x[2] = "se " + "había " + a;
			x[3] = "nos " + "habíamos " + a;
			x[4] = "os " + "habíais " + a;
			x[5] = "se " + "habían " + a;
		}else{
			x[0] = "había " + a;
			x[1] = "habías " + a;
			x[2] = "había " + a;
			x[3] = "habíamos " + a;
			x[4] = "habíais " + a;
			x[5] = "habían " + a;
		}
		return x;
	}
}
