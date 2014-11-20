package Subjuntivo;

import java.util.Scanner;

import Other.Function;

public class Imperfecto2 {

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
		
		Function.viewArray(imperfect2(a));
	}

	public static String[] imperfect2(String a) {
		type(a);
		String b = a;
		a = Indicativo.Pretérito.preterite(a)[5];
		String[] x = new String[6];
		a = a.substring(0, a.length() - 3);
		if(reflexive == true){
			x[0] = "me " + a + "se";
			x[1] = "te " + a + "ses";
			x[2] = "se " + a + "se";
			x[3] = "nos " + b.substring(0, b.length() - 2) + ((t == type.AR) ? "á" : "ié") + "semos";
			x[4] = "os " + a + "seis";
			x[5] = "se " + a + "sen";
		}else{
			x[0] = a + "se";
			x[1] = a + "ses";
			x[2] = a + "se";
			x[3] = b.substring(0, b.length() - 2) + ((t == type.AR) ? "á" : "ié") + "semos";
			x[4] = a + "seis";
			x[5] = a + "sen";
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
