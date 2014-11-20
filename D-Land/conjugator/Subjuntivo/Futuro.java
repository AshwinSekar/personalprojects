package Subjuntivo;

import java.util.Scanner;

import Other.Function;

public class Futuro {

	public enum type{
		AR, ER, IR
	}
	private static type t;
	
	private static boolean reflexive = false;;

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
		type(a);
		String b = a;
		a = Indicativo.Pretérito.preterite(a)[5];
		String[] x = new String[6];
		a = a.substring(0, a.length() - 3);
		if(reflexive == true){
			x[0] = "me " + a + "re";
			x[1] = "te " + a + "res";
			x[2] = "se " + a + "re";
			x[3] = "nos " + b.substring(0, b.length() - 2) + ((t == type.AR) ? "á" : "ié") + "remos";
			x[4] = "os " + a + "reis";
			x[5] = "se " + a + "ren";
		}else{
			x[0] = a + "re";
			x[1] = a + "res";
			x[2] = a + "re";
			x[3] = b.substring(0, b.length() - 2) + ((t == type.AR) ? "á" : "ié") + "remos";
			x[4] = a + "reis";
			x[5] = a + "ren";
		}
		return x;
	}
	
	private static void type(String a) {
		if(a.endsWith("ar")){
			t = type.AR;
		}else{
			if(a.endsWith("er")){
				t = type.ER;
			}else{
				if(a.endsWith("ir") || a.endsWith("ír")){
					t = type.IR;
				}
			}
		}
	}
}
