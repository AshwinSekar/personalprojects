package dataproccessing;

/**
 * A highscore entry
 * @author Ashwin Sekar
 * 
 *
 */
public class Score implements Comparable<Score>{
	public long score;
	public String name;
	
	public Score(long s, String n) {
		score = s;
		name = n;
	}

	@Override
	public int compareTo(Score o) {
		return (int) (o.score - this.score);
	}
	
	@Override
	public String toString() {
		return name + " : " + score;
	}

}
