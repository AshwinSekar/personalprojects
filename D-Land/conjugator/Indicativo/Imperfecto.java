package Indicativo;

import java.util.Scanner;

import Other.Function;

public class Imperfecto {

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
		Function.viewArray(imperfect(a));
	}

	public static String[] imperfect(String a) {
		type(a);
		String[] x = new String[6];
		if(a.equals("ser")){
			x[0] = "era";
			x[1] = "eras";
			x[2] = "era";
			x[3] = "�ramos";
			x[4] = "erais";
			x[5] = "eran";
			return x;
		}
		if(a.equals("ir")){
			x[0] = "iba";
			x[1] = "ibas";
			x[2] = "iba";
			x[3] = "�bamos";
			x[4] = "ibais";
			x[5] = "iban";
			return x;
		}
		if(a.equals("ver")){
			x[0] = "ve�a";
			x[1] = "ve�as";
			x[2] = "ve�a";
			x[3] = "ve�amos";
			x[4] = "ve�ais";
			x[5] = "ve�an";
			return x;
		}
		a = a.substring(0, a.length() - 2);
		switch(t){
		case AR:
			if(reflexive == true){
				x[0] = "me " + a + "aba";
				x[1] = "te " + a + "abas";
				x[2] = "se " + a + "aba";
				x[3] = "nos " + a + "�bamos";
				x[4] = "os " + a + "abais";
				x[5] = "se " + a + "aban";
			}else{
				x[0] = a + "aba";
				x[1] = a + "abas";
				x[2] = a + "aba";
				x[3] = a + "�bamos";
				x[4] = a + "abais";
				x[5] = a + "aban";
			}
			break;
		case ER:
			if(reflexive == true){
				x[0] = "me " + a + "�a";
				x[1] = "te " + a + "�as";
				x[2] = "se " + a + "�a";
				x[3] = "nos " + a + "�amos";
				x[4] = "os " + a + "�ais";
				x[5] = "se " + a + "�an";
			}else{
				x[0] = a + "�a";
				x[1] = a + "�as";
				x[2] = a + "�a";
				x[3] = a + "�amos";
				x[4] = a + "�ais";
				x[5] = a + "�an";
			}
			break;
		case IR:
			if(reflexive == true){
				x[0] = "me " + a + "�a";
				x[1] = "te " + a + "�as";
				x[2] = "se " + a + "�a";
				x[3] = "nos " + a + "�amos";
				x[4] = "os " + a + "�ais";
				x[5] = "se " + a + "�an";
			}else{
				x[0] = a + "�a";
				x[1] = a + "�as";
				x[2] = a + "�a";
				x[3] = a + "�amos";
				x[4] = a + "�ais";
				x[5] = a + "�an";
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
