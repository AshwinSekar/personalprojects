package Main;

import static vocab.Tense.*;
import vocab.Tense;


public class Main {
	
	public static String conjugate(String v, Tense t, int x) {
		if(t.equals(Present)){
			return Indicativo.Presente.present(v)[x];
		}
		if(t.equals(Preterite)){
			return Indicativo.Pretérito.preterite(v)[x];
		}
		if(t.equals(Imperfect)){
			return Indicativo.Imperfecto.imperfect(v)[x];
		}
		if(t.equals(Future)){
			return Indicativo.Futuro.future(v)[x];
		}
		if(t.equals(Conditional)){
			return Indicativo.Condicional.conditional(v)[x];
		}
		if(t.equals(Present_Participle)){
			return Other.Gerundio.gerund(v);
		}
		if(t.equals(Present_Perfect)){
			return Perfecto.Presente.present(v)[x];
		}
		if(t.equals(Pluperfect)){
			return Perfecto.Imperfecto.imperfect(v)[x];
		}
		if(t.equals(Future_Perfect)){
			return Perfecto.Futuro.future(v)[x];
		}
		if(t.equals(Conditional_Perfect)){
			return Perfecto.Condicional.conditional(v)[x];
		}
		if(t.equals(Present_Subjunctive)){
			return Subjuntivo.Presente.present(v)[x];
		}
		if(t.equals(Imperfect_Subjunctive)){
			return Subjuntivo.Imperfecto1.imperfect1(v)[x];
		}
		if(t.equals(Future_Subjunctive)){
			return Subjuntivo.Futuro.future(v)[x];
		}
		if(t.equals(Pluperfect_Subjunctive)){
			return SubjuntivoPerfecto.Imperfecto1.imperfect1(v)[x];
		}
		return null;
	}
}
