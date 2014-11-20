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
			x[0] = "me " + "habría " + a;
			x[1] = "te " + "habrías " + a;
			x[2] = "se " + "habría " + a;
			x[3] = "nos " + "habríamos " + a;
			x[4] = "os " + "habríais " + a;
			x[5] = "se " + "habrían " + a;
		}else{
			x[0] = "habría " + a;
			x[1] = "habrías " + a;
			x[2] = "habría " + a;
			x[3] = "habríamos " + a;
			x[4] = "habríais " + a;
			x[5] = "habrían " + a;
		}
		return x;
	}
}
