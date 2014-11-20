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
			x[0] = "me " + "hab�a " + a;
			x[1] = "te " + "hab�as " + a;
			x[2] = "se " + "hab�a " + a;
			x[3] = "nos " + "hab�amos " + a;
			x[4] = "os " + "hab�ais " + a;
			x[5] = "se " + "hab�an " + a;
		}else{
			x[0] = "hab�a " + a;
			x[1] = "hab�as " + a;
			x[2] = "hab�a " + a;
			x[3] = "hab�amos " + a;
			x[4] = "hab�ais " + a;
			x[5] = "hab�an " + a;
		}
		return x;
	}
}
