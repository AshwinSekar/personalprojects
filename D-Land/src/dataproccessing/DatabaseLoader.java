package dataproccessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import vocab.*;
import static vocab.Tense.*;
import static vocab.Form.*;

/**
 * 
 * The class that interacts with the vocab, verb and Highscore xml database.
 * It keeps the HashMaps of vocab words separated by unit as well as the highscores by game.
 * It also keeps an arraylist of verbs
 * @author Ashwin Sekar
 */
public class DatabaseLoader extends DefaultHandler {
	public static InputStream pathToVocab;
	public static InputStream pathToScores;
	public static InputStream pathToVerbs;
	public static HashMap<String, ArrayList<VocabWord>> vocabList;
	public static HashMap<String, ArrayList<Score>> highscores;
	public static ArrayList<Verb> verbs;
	private static ArrayList<VocabWord> cur;
	private static ArrayList<Score> curScore;
	private static StringBuffer s;
	private static String curSpan = "";
	private static String curEng = "";
	private static String unit = "";
	private static String game = "";
	private static String curS = "";
	private static String curName = "";
	private static String curVerb = "";
	private static Random r = new Random();
	private static int rand;
	private static VocabWord tempVocab;
	
	private static Tense[] tenses = {Present,
		Preterite,
		Imperfect,
		Future,
		Conditional,
		Present_Participle,
		Present_Perfect,
		Pluperfect,
		Future_Perfect,
		Conditional_Perfect,
		Present_Subjunctive,
		Imperfect_Subjunctive,
		Future_Subjunctive,
		Pluperfect_Subjunctive};
	private static Form[] forms = {	Yo,
		Tu,
		El_Ella_Usted,
		Nosotros,
		Vosotros,
		Ustedes_Ellos_Ellas};
	
	/**
	 * Loads all of the variables from the Vocab, Verbs and highscore databases
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void loadDatabase() throws ParserConfigurationException, SAXException, IOException {
		vocabList = new HashMap<String, ArrayList<VocabWord>>();
		highscores = new HashMap<String, ArrayList<Score>>();
		verbs = new ArrayList<Verb>();
		cur = new ArrayList<VocabWord>();
		curScore = new ArrayList<Score>();
		s = new StringBuffer();
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
	    XMLReader xmlReader = saxParser.getXMLReader();
	    xmlReader.setContentHandler(new DatabaseLoader());
	    xmlReader.parse(new InputSource(pathToVocab));
	    xmlReader.parse(new InputSource(pathToScores));
	    xmlReader.parse(new InputSource(pathToVerbs));
	}
	
	/**
	 * Part of the SAX XML parser
	 */
	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
		if(arg1.equals("vocablist")) {
			cur = new ArrayList<VocabWord>();
			unit = arg3.getValue("Unit");
		}
		if(arg1.equals("word")) {
			curSpan = "";
			curEng = "";
		}
		if(arg1.equals("player")) {
			curS = "";
			curName = "";
		}
		if(arg1.equals("spanish") || arg1.equals("english") || arg1.equals("score") || arg1.equals("name") || arg1.equals("verb"))
			s.setLength(0);
		if(arg1.equals("game")) {
			curScore = new ArrayList<Score>();
			game = arg3.getValue("name");
		}
		if(arg1.equals("verb"))
			curVerb = "";			
	}
	
	/**
	 * Part of the SAX XML parser
	 */
	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		if(arg1.equals("spanish"))
			curSpan = s.toString();
		if(arg1.equals("english"))
			curEng = s.toString();
		if(arg1.equals("score"))
			curS = s.toString();
		if(arg1.equals("name"))
			curName = s.toString();
		if(arg1.equals("verb")) {
			curVerb = s.toString();
			verbs.add(new Verb(curVerb));
		}
		if(arg1.equals("word"))  
			cur.add(new VocabWord(curSpan, curEng, Unit.valueOf(unit)));			
		if(arg1.equals("player")) 
			curScore.add(new Score(Long.parseLong(curS), curName));
		if(arg1.equals("vocablist")) {
			vocabList.put(new String(unit), cur);
		}
		if(arg1.equals("game"))
			highscores.put(game, curScore);
	}
	
	/**
	 * Part of the SAX XML parser
	 */
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		s.append(arg0,arg1,arg2);
	}
	
	/**
	 * Adds a highscore entry to the appropriate game
	 * @param c - The highscore entry to add
	 * @param game - The game to add it to
	 * @throws IOException
	 */
	public static void addHighScore(Score c, String game, File path) throws IOException {
		StringBuffer out = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(pathToScores));
		
		String s,temp;
		while(!(s = in.readLine()).trim().equals("<game name=\"" + game + "\">")) {
			out.append(s + "\n");
		}
		out.append(s + "\n");
		while(true) {
			s = in.readLine();
			
			temp = in.readLine();
			if(Long.parseLong(temp.trim().substring(7,7 + temp.trim().length() - 15)) < c.score) {
				out.append("		<player>\r\n" + 
						"			<score>" + c.score + "</score>\r\n" + 
						"			<name>" + c.name + "</name>\r\n" + 
						"		</player>\n");
				out.append(s + "\n");
				out.append(temp + "\n");
				break;
			}
			out.append(s + "\n");
			out.append(temp + "\n");
			out.append(in.readLine() + "\n");
			out.append(in.readLine() + "\n");
		}
		while((s = in.readLine()) != null) {
			out.append(s + "\n");
		}
		in.close();
		PrintWriter o = new PrintWriter(path);
		o.println(out);
		o.close();		
	}

	/**
	 * Returns a 4 random vocab entries for use in the games
	 * @param units - The list of units from which these entries come from
	 * @return A list of 4 random vocab entries
	 */
	public static String[] getVocab(ArrayList<Unit> units) {
		String[] ans = new String[4];
		rand = r.nextInt(units.size());
		tempVocab = vocabList.get(units.get(rand).toString()).get(r.nextInt(vocabList.get(units.get(rand).toString()).size()));
		ans[0] = tempVocab.getEnglish();
		ans[1] = tempVocab.getSpanish();
		do {
			ans[2] = vocabList.get(units.get(rand).toString()).get(r.nextInt(vocabList.get(units.get(rand).toString()).size())).getSpanish();
		} while(ans[1].equals(ans[2]));
		do {
			ans[3] = vocabList.get(units.get(rand).toString()).get(r.nextInt(vocabList.get(units.get(rand).toString()).size())).getSpanish();
		} while(ans[1].equals(ans[3])  || ans[2].equals(ans[3]));
		return ans;
	}

	/**
	 * Returns a 4 random verb entries for use in the games
	 * @return A list of 4 random verb entries
	 */
	public static String[] getVerbs() {
		String[] ans = new String[4];
		Tense t = tenses[r.nextInt(tenses.length)];
		Tense otherTense;
		Tense otherTense2;
		do {
			otherTense = tenses[r.nextInt(tenses.length)];
		} while(otherTense.equals(t));
		do {
			otherTense2 = tenses[r.nextInt(tenses.length)];
		} while(otherTense2.equals(t) || otherTense2.equals(otherTense));
		int f = r.nextInt(forms.length);
		Verb v = verbs.get(r.nextInt(verbs.size()));
		ans[0] = t.toString().replaceAll("_", " ");
		ans[1] = v.conjugate(t, f);
		ans[2] = v.conjugate(otherTense, f);
		ans[3] = v.conjugate(otherTense, f);
		return ans;
	}
}
