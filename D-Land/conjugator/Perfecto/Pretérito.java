package Perfecto;

import java.util.Scanner;

import Other.Function;

public class Pretérito {

	private static boolean reflexive = false;
	
	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		if(a.endsWith("se")){
			a = a.substring(0, a.length() - 2);
			reflexive = true;
		}
		Function.viewArray(preterite(a));
	}

	public static String[] preterite(String a) {
		String[] x = new String[6];
		a = Other.Participio.participle(a);
			if(reflexive == true){
				x[0] = "me " + "hube " + a;
				x[1] = "te " + "hubiste " + a;
				x[2] = "se " + "hubo " + a;
				x[3] = "nos " + "hubimos " + a;
				x[4] = "os " + "hubisteis " + a;
				x[5] = "se " + "hubieron " + a;
			}else{
				x[0] = "hube " + a;
				x[1] = "hubiste " + a;
				x[2] = "hubo " + a;
				x[3] = "hubimos " + a;
				x[4] = "hubisteis " + a;
				x[5] = "hubieron " + a;
			}
		return x;
	}
}
