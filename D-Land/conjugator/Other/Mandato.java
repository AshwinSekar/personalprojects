package Other;

import java.util.Scanner;

public class Mandato {

	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		Function.viewArray(command(a));
	}
	public static String[] command(String a) {
		String[] x = new String[5];
		x[0] = Indicativo.Presente.present(a)[2];
		x[1] = Subjuntivo.Presente.present(a)[0];
		x[2] = nosotros(a);
		x[3] = a.substring(0, a.length() - 1) + "d";
		x[4] = Subjuntivo.Presente.present(a)[5];
		if(a.equals("decir")){
			x[0] = "di";
		}
		if(a.equals("hacer")){
			x[0] = "haz";
		}
		if(a.equals("ir")){
			x[0] = "ve";
		}
		if(a.equals("poner")){
			x[0] = "pon";
		}
		if(a.equals("salir")){
			x[0] = "sal";
		}
		if(a.equals("ser")){
			x[0] = "sé";
		}
		if(a.equals("tener")){
			x[0] = "ten";
		}
		if(a.equals("venir")){
			x[0] = "ven";
		}
		return x;
	}
	private static String nosotros(String a) {
		String x = new String();
		if(a.endsWith("ar")){
			a = a.substring(0, a.length() - 2);
			x = a + "émonos";
		}else{
			a = a.substring(0, a.length() - 2);
			if(Other.Function.isVowel(a.substring(a.length() - 1, a.length()))){
				x = a + "yámonos";
			}else{
				x = a + "ámonos";
			}
		}
		return x;
	}

}
