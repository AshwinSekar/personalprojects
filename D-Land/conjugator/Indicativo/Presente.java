package Indicativo;

import java.util.Scanner;

import Other.Function;

public class Presente {

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
		Function.viewArray(present(a));
	}

	public static String[] present(String a) {
		type(a);
		String[] x = new String[6];
		if(a.endsWith("tener")){
			x[0] = "tengo";
			x[1] = "tienes";
			x[2] = "tiene";
			x[3] = "tenemos";
			x[4] = "tenéis";
			x[5] = "tienen";
			return x;
		}
		if(a.endsWith("poner")){
			x[0] = "pongo";
			x[1] = "pones";
			x[2] = "pone";
			x[3] = "ponemos";
			x[4] = "ponéis";
			x[5] = "ponen";
			return x;
		}
		if(a.endsWith("probar")){
			x[0] = "pruebo";
			x[1] = "pruebas";
			x[2] = "prueba";
			x[3] = "probamos";
			x[4] = "probáis";
			x[5] = "prueban";
			return x;
		}
		if(a.endsWith("perder")){
			x[0] = "pierdo";
			x[1] = "pierdas";
			x[2] = "pierda";
			x[3] = "perdemos";
			x[4] = "perdéis";
			x[5] = "pierdan";
			return x;
		}
		if(a.endsWith("gobernar")){
			x[0] = "gobierno";
			x[1] = "gobiernas";
			x[2] = "gobierna";
			x[3] = "gobernamos";
			x[4] = "gobernáis";
			x[5] = "gobiernan";
			return x;
		}
		if(a.endsWith("elegir")){
			x[0] = "elijo";
			x[1] = "eliges";
			x[2] = "elige";
			x[3] = "elegimos";
			x[4] = "elegís";
			x[5] = "eligen";
			return x;
		}
		a = a.substring(0, a.length() - 2);
		String b = a;
		String c = a;
		if(a.endsWith("c") && (t == type.ER || t == type.IR)){
			if(Other.Function.isVowel("" + a.charAt(a.length() - 2))){
				b = a.substring(0, b.length() - 1) + "zc";
			}else{
				b = a.substring(0, b.length() - 1) + "z";
			}
		}
		if(a.endsWith("g") && (t == type.ER || t == type.IR)){
			b = a.substring(0, b.length() - 1) + "j";
		}
		if(a.endsWith("gu") && (t == type.ER || t == type.IR)){
			b = a.substring(0, b.length() - 2) + "g";
		}
		if(a.endsWith("cab") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "quep";
		}
		if(a.endsWith("ca") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "caig";
		}
		if(a.endsWith("tra") && t == type.ER){
			b = a.substring(0, b.length() - 3) + "trai";
		}
		if(a.equals("v") && t == type.ER){
			b = a.substring(0, b.length() - 1) + "ve";
		}
		boolean doubleVowel = false;
		if("u".equals(a.substring(a.length() - 1, a.length()))){
			c += "y";
			doubleVowel = true;
		}
		switch(t){
		case AR:
			if(reflexive == true){
				x[0] = "me " + ((doubleVowel) ? c : b ) + "o";
				x[1] = "te " + c + "as";
				x[2] = "se " + c + "a";
				x[3] = "nos " + a + "amos";
				x[4] = "os " + a + "áis";
				x[5] = "se " + c + "an";
			}else{
				x[0] = ((doubleVowel) ? c : b ) + "o";
				x[1] = c + "as";
				x[2] = c + "a";
				x[3] = a + "amos";
				x[4] = a + "áis";
				x[5] = c + "an";
			}
			break;
		case ER:
			if(reflexive == true){
				x[0] = "me " + ((doubleVowel) ? c : b ) + "o";
				x[1] = "te " + c + "es";
				x[2] = "se " + c + "e";
				x[3] = "nos " + a + "emos";
				x[4] = "os " + a + "éis";
				x[5] = "se " + c + "en";
			}else{
				x[0] = ((doubleVowel) ? c : b ) + "o";
				x[1] = c + "es";
				x[2] = c + "e";
				x[3] = a + "emos";
				x[4] = a + "éis";
				x[5] = c + "en";
			}
			break;
		case IR:
			if(reflexive == true){
				x[0] = "me " + ((doubleVowel) ? c : b ) + "o";
				x[1] = "te " + c + "es";
				x[2] = "se " + c + "e";
				x[3] = "nos " + a + "imos";
				x[4] = "os " + a + "ís";
				x[5] = "se " + c + "en";
			}else{
				x[0] = ((doubleVowel) ? c : b ) + "o";
				x[1] = c + "es";
				x[2] = c + "e";
				x[3] = a + "imos";
				x[4] = a + "ís";
				x[5] = c + "en";
			}
			break;
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
