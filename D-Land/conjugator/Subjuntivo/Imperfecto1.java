package Subjuntivo;

import java.util.Scanner;

import Other.Function;

public class Imperfecto1 {

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
		Function.viewArray(imperfect1(a));
	}

	public static String[] imperfect1(String a) {
		type(a);
		String b = a;
		a = Indicativo.Pretérito.preterite(a)[5];
		String[] x = new String[6];
		a = a.substring(0, a.length() - 3);
		if(reflexive == true){
			x[0] = "me " + a + "ra";
			x[1] = "te " + a + "ras";
			x[2] = "se " + a + "ra";
			x[3] = "nos " + b.substring(0, b.length() - 2) + ((t == type.AR) ? "á" : "ié") + "ramos";
			x[4] = "os " + a + "rais";
			x[5] = "se " + a + "ran";
		}else{
			x[0] = a + "ra";
			x[1] = a + "ras";
			x[2] = a + "ra";
			x[3] = b.substring(0, b.length() - 2) + ((t == type.AR) ? "á" : "ié") + "ramos";
			x[4] = a + "rais";
			x[5] = a + "ran";
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
