package Other;

import java.util.Scanner;

public class Participio {

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
		System.out.println(participle(a));
	}

	public static String participle(String a) {
		if(a.endsWith("brir")){
			return a.substring(0, a.length() - 4) + "bierto";
		}
		if(a.endsWith("escribir")){
			return a.substring(0, a.length() - 8) + "escrito";
		}
		if(a.endsWith("decir")){
			return a.substring(0, a.length() - 5) + "dicho";
		}
		if(a.endsWith("hacer")){
			return a.substring(0, a.length() - 5) + "hecho";
		}
		if(a.endsWith("morir")){
			return a.substring(0, a.length() - 5) + "muerto";
		}
		if(a.endsWith("poner")){
			return a.substring(0, a.length() - 5) + "puesto";
		}
		if(a.endsWith("resolver")){
			return a.substring(0, a.length() - 5) + "uelto";
		}
		if(a.endsWith("volver")){
			return a.substring(0, a.length() - 6) + "vuelto";
		}
		if(a.endsWith("ver")){
			return a.substring(0, a.length() - 3) + "visto";
		}
		if(a.endsWith("romper")){
			return a.substring(0, a.length() - 6) + "roto";
		}
		if(a.endsWith("freír")){
			return a.substring(0, a.length() - 5) + "frito";
		}
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
		switch(t){
		case AR:
			a += "ado";
			break;
		case ER:
			a += "ido";
			break;
		case IR:
			a += "ido";
			break;
		}
		return a;
	}
}
