package vocab;

import Main.Main;

/**
 * The class that holds verbs
 * @author Ashwin Sekar
 * 
 *
 */
public class Verb {
	String verb;

	/**
	 * Creates a new verb
	 * @param s - The verb in spanish
	 */
	public Verb(String s) {
		verb = s + " ";
	}
	
	/**
	 * Returns the verb conjugated in the tense and form
	 * @param tense - The tense
	 * @param form - The form
	 * @return - The conjugated verb in spanish
	 */
	public String conjugate(Tense tense, int form) {
		String verbPart = verb.substring(0, verb.indexOf(" "));
		String verbOther = verb.substring(verb.indexOf(" "));
		return Main.conjugate(verbPart, tense, form) + verbOther;
	}
	
	@Override
	public String toString() {
		return verb;
	}

}
