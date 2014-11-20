package Subjuntivo;

import java.util.Scanner;

import Other.Function;

public class Presente {

	private static boolean reflexive = false;;
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
		if(a.equals("dar")){
			if(reflexive == true){
				x[0] = "me " + "d�";
				x[1] = "te " + "des";
				x[2] = "se " + "d�";
				x[3] = "nos " + "demos";
				x[4] = "os " + "deis";
				x[5] = "se " + "den";
			}else{
				x[0] = "d�";
				x[1] = "des";
				x[2] = "d�";
				x[3] = "demos";
				x[4] = "deis";
				x[5] = "den";
			}
			return x;
		}
		if(a.equals("estar")){
			if(reflexive == true){
				x[0] = "me " + "est�";
				x[1] = "te " + "est�s";
				x[2] = "se " + "est�";
				x[3] = "nos " + "est�mos";
				x[4] = "os " + "est�is";
				x[5] = "se " + "est�n";
			}else{
				x[0] = "est�";
				x[1] = "est�s";
				x[2] = "est�";
				x[3] = "est�mos";
				x[4] = "est�is";
				x[5] = "est�n";
			}
			return x;
		}
		if(a.equals("haber")){
			if(reflexive == true){
				x[0] = "me " + "haya";
				x[1] = "te " + "hayas";
				x[2] = "se " + "haya";
				x[3] = "nos " + "hayamos";
				x[4] = "os " + "hay�is";
				x[5] = "se " + "hayan";
			}else{
				x[0] = "haya";
				x[1] = "hayas";
				x[2] = "haya";
				x[3] = "hayamos";
				x[4] = "hay�is";
				x[5] = "hayan";
			}
			return x;
		}
		if(a.equals("ir")){
			if(reflexive == true){
				x[0] = "me " + "vaya";
				x[1] = "te " + "vayas";
				x[2] = "se " + "vaya";
				x[3] = "nos " + "vayamos";
				x[4] = "os " + "vay�is";
				x[5] = "se " + "vayan";
			}else{
				x[0] = "vaya";
				x[1] = "vayas";
				x[2] = "vaya";
				x[3] = "vayamos";
				x[4] = "vay�is";
				x[5] = "vayan";
			}
			return x;
		}
		if(a.equals("saber")){
			if(reflexive == true){
				x[0] = "me " + "sepa";
				x[1] = "te " + "sepas";
				x[2] = "se " + "sepa";
				x[3] = "nos " + "sepamos";
				x[4] = "os " + "sep�is";
				x[5] = "se " + "sepan";
			}else{
				x[0] = "sepa";
				x[1] = "sepas";
				x[2] = "sepa";
				x[3] = "sepamos";
				x[4] = "sep�is";
				x[5] = "sepan";
			}
			return x;
		}
		if(a.equals("saber")){
			if(reflexive == true){
				x[0] = "me " + "sea";
				x[1] = "te " + "seas";
				x[2] = "se " + "sea";
				x[3] = "nos " + "seamos";
				x[4] = "os " + "se�is";
				x[5] = "se " + "sean";
			}else{
				x[0] = "sea";
				x[1] = "seas";
				x[2] = "sea";
				x[3] = "seamos";
				x[4] = "se�is";
				x[5] = "sean";
			}
			return x;
		}
		a = Indicativo.Presente.present(a)[0];
		a = a.substring(0, a.length() - 1);
		if(a.endsWith("z") && t == type.AR){
			a = a.substring(0, a.length() - 1) + "c";
		}
		if(a.endsWith("z") && t == type.AR){
			a = a.substring(0, a.length() - 1) + "c";
		}
		if(a.endsWith("c") && t == type.AR){
			a = a.substring(0, a.length() - 1) + "qu";
		}
		if(a.endsWith("g") && t == type.AR){
			a = a.substring(0, a.length() - 1) + "gu";
		}
		if(a.endsWith("u") && t == type.IR){
			a += "y";
		}
		switch(t){
		case AR:
			if(reflexive == true){
				x[0] = "me " + a + "e";
				x[1] = "te " + a + "es";
				x[2] = "se " + a + "e";
				x[3] = "nos " + a + "emos";
				x[4] = "os " + a + "�is";
				x[5] = "se " + a + "en";
			}else{
				x[0] = a + "e";
				x[1] = a + "es";
				x[2] = a + "e";
				x[3] = a + "emos";
				x[4] = a + "�is";
				x[5] = a + "en";
			}
			break;
		case ER:
			if(reflexive == true){
				x[0] = "me " + a + "a";
				x[1] = "te " + a + "as";
				x[2] = "se " + a + "a";
				x[3] = "nos " + a + "amos";
				x[4] = "os " + a + "�is";
				x[5] = "se " + a + "an";
			}else{
				x[0] = a + "a";
				x[1] = a + "as";
				x[2] = a + "a";
				x[3] = a + "amos";
				x[4] = a + "�is";
				x[5] = a + "an";
			}
			break;
		case IR:
			if(reflexive == true){
				x[0] = "me " + a + "a";
				x[1] = "te " + a + "as";
				x[2] = "se " + a + "a";
				x[3] = "nos " + a + "amos";
				x[4] = "os " + a + "�is";
				x[5] = "se " + a + "an";
			}else{
				x[0] = a + "a";
				x[1] = a + "as";
				x[2] = a + "a";
				x[3] = a + "amos";
				x[4] = a + "�is";
				x[5] = a + "an";
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
				if(a.endsWith("ir") || a.endsWith("�r")){
					t = type.IR;
				}
			}
		}
	}
}
