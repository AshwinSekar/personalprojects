package Indicativo;

import java.util.Scanner;

import Other.Function;

public class Pretérito {

	private static boolean reflexive = false;
	public enum type{
		AR, ER, IR
	}
	private static type t;

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
		type(a);
		String[] x = new String[6];
		String d = a;
		if(a.endsWith("ver")){
			d = d.substring(0, d.length() - 2);
			if(reflexive == true){
				x[0] = "me " + d + "ice";
				x[1] = "te " + d + "iciste";
				x[2] = "se " + d + "izo";
				x[3] = "nos " + d + "icimos";
				x[4] = "os " + d + "icisteis";
				x[5] = "se " + d + "icieron";
			}else{
				x[0] = d + "ice";
				x[1] = d + "iciste";
				x[2] = d + "izo";
				x[3] = d + "icimos";
				x[4] = d + "icisteis";
				x[5] = d + "icieron";
			}
			return x;
		}
		if(a.endsWith("hacer") && t == type.ER){
			d = d.substring(0, d.length() - 2);
			if(reflexive == true){
				x[0] = "me " + d + "ice";
				x[1] = "te " + d + "iciste";
				x[2] = "se " + d + "izo";
				x[3] = "nos " + d + "icimos";
				x[4] = "os " + d + "icisteis";
				x[5] = "se " + d + "icieron";
			}else{
				x[0] = d + "ice";
				x[1] = d + "iciste";
				x[2] = d + "izo";
				x[3] = d + "icimos";
				x[4] = d + "icisteis";
				x[5] = d + "icieron";
			}
			return x;
		}
		a = a.substring(0, a.length() - 2);
		String b = a;
		if(a.endsWith("c") && t == type.AR){
			b = a.substring(0, b.length() - 1) + "qu";
		}
		if(a.endsWith("z") && t == type.AR){
			b = a.substring(0, b.length() - 1) + "c";
		}
		if(a.endsWith("g") && t == type.AR){
			b = a.substring(0, b.length() - 1) + "gu";
		}
		boolean bakers = false;
		if(a.endsWith("and") && t == type.AR){
			b = a.substring(0, b.length() - 3) + "anduv";
			bakers = true;
		}
		if(a.endsWith("est") && t == type.AR){
			b = a.substring(0, b.length() - 3) + "estuv";
			bakers = true;
		}
		if(a.endsWith("ten") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "tuv";
			bakers = true;
		}
		if(a.endsWith("cab") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "cup";
			bakers = true;
		}
		if(a.endsWith("hab") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "hub";
			bakers = true;
		}
		if(a.endsWith("pod") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "pud";
			bakers = true;
		}
		if(a.endsWith("pon") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "pus";
			bakers = true;
		}
		if(a.endsWith("sab") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "sup";
			bakers = true;
		}
		if(a.endsWith("quer") && t == type.ER){
			b = a.substring(0, b.length() - 4) + "quis";
			bakers = true;
		}
		if(a.endsWith("ven") && t == type.IR){
			b = a.substring(0, b.length() - 3) + "vin";
			bakers = true;
		}
		if(a.endsWith("dec") && t == type.IR){
			b = a.substring(0, b.length() - 3) + "dij";
			bakers = true;
		}
		if(a.endsWith("tra") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "traj";
			bakers = true;
		}
		if(a.endsWith("sab") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "sup";
			bakers = true;
		}
		boolean doubleVowel = false;
		String c = a;
		if(Other.Function.isVowel(a.substring(a.length() - 1, a.length()))){
			c += "y";
			doubleVowel = true;
		}
		if(bakers == true){
			return bakers(a, b, x);
		}
		switch(t){
		case AR:
			if(reflexive == true){
				x[0] = "me " + b + "é";
				x[1] = "te " + a + "aste";
				x[2] = "se " + a + "ó";
				x[3] = "nos " + a + "amos";
				x[4] = "os " + a + "asteis";
				x[5] = "se " + a + "aron";
			}else{
				x[0] = b + "é";
				x[1] = a + "aste";
				x[2] = a + "ó";
				x[3] = a + "amos";
				x[4] = a + "asteis";
				x[5] = a + "aron";
			}
			break;
		case ER:
			if(reflexive == true){
				x[0] = "me " + a + "í";
				x[1] = "te " + a + "iste";
				x[2] = "se " + c + ((doubleVowel) ? "ó" : "ió" );
				x[3] = "nos " + a + "imos";
				x[4] = "os " + a + "isteis";
				x[5] = "se " + c + ((doubleVowel) ? "eron" : "ieron" );
			}else{
				x[0] = a + "í";
				x[1] = a + "iste";
				x[2] = c + ((doubleVowel) ? "ó" : "ió" );
				x[3] = a + "imos";
				x[4] = a + "isteis";
				x[5] = c + ((doubleVowel) ? "eron" : "ieron" );
			}
			break;
		case IR:
			if(reflexive == true){
				x[0] = "me " + a + "í";
				x[1] = "te " + a + "iste";
				x[2] = "se " + c + ((doubleVowel) ? "ó" : "ió" );
				x[3] = "nos " + a + "imos";
				x[4] = "os " + a + "isteis";
				x[5] = "se " + c + ((doubleVowel) ? "eron" : "ieron" );
			}else{
				x[0] = a + "í";
				x[1] = a + "iste";
				x[2] = c + ((doubleVowel) ? "ó" : "ió" );
				x[3] = a + "imos";
				x[4] = a + "isteis";
				x[5] = c + ((doubleVowel) ? "eron" : "ieron" );
			}
			break;
		}
		return x;
	}
	
	private static String[] bakers(String a, String b, String[] x) {
		if(reflexive == true){
			x[0] = "me " + b + "e";
			x[1] = "te " + b + "iste";
			x[2] = "se " + b + "o";
			x[3] = "nos " + b + "imos";
			x[4] = "os " + b + "isteis";
			x[5] = "se " + b + "ieron";
		}else{
			x[0] = b + "e";
			x[1] = b + "iste";
			x[2] = b + "o";
			x[3] = b + "imos";
			x[4] = b + "isteis";
			x[5] = b + "ieron";
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
