package Other;

import java.util.Scanner;

public class Gerundio {

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
		}
		System.out.println(gerund(a));
	}

	public static String gerund(String a) {
		if(a.endsWith("ar")){
			t = type.AR;
		}else{
			if(a.endsWith("er")){
				t = type.ER;
			}else{
				if(a.endsWith("ir")){
					t = type.IR;
				}
			}
		}
		a = a.substring(0, a.length() - 2);
		boolean doubleVowel = false;
		if(Other.Function.isVowel(a.substring(a.length() - 1, a.length()))){
			doubleVowel = true;
		}
		switch(t){
		case AR:
			a += "ando";
			break;
		case ER:
			a += ((doubleVowel) ? "yendo" : "iendo" );
			break;
		case IR:
			a += ((doubleVowel) ? "yendo" : "iendo" );
			break;
		}
		return a;
	}
}
