package vocab;

/**
 * The class that holds vocab words
 * @author Ashwin Sekar
 * 
 *
 */
public class VocabWord {
	String spanish;
	String english;
	Unit unit;
	
	/**
	 * Creates a new vocab word
	 * @param s - The word in spanish
	 * @param e - The word in english
	 * @param u - The unit
	 */
	public VocabWord(String s, String e, Unit u) {
		spanish = s;
		english = e;
		unit = u;
	}
	
	@Override
	public String toString() {
		return spanish + "-" + english;
	}
	
	public String getSpanish() {
		return spanish;
	}

	public void setSpanish(String spanish) {
		this.spanish = spanish;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

}
