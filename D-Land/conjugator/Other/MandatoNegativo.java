package Other;

import java.util.Scanner;

public class MandatoNegativo {
	
	public static void main(String[]args){
		Scanner sb = new Scanner(System.in);
		System.out.println("Input a verb");
		String a = sb.nextLine();
		Function.viewArray(negativeCommand(a));
	}
	public static String[] negativeCommand(String a) {
		String[] x = new String[5];
		x[0] = "no " + Subjuntivo.Presente.present(a)[1];
		x[1] = "no " + Subjuntivo.Presente.present(a)[0];
		x[2] = "no " + Subjuntivo.Presente.present(a)[3];
		x[3] = "no " + Subjuntivo.Presente.present(a)[4];
		x[4] = "no " + Subjuntivo.Presente.present(a)[5];
		return x;
	}
}
