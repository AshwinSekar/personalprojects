package burritoninja;

/**
 * 
 * The class that contains all the information about the fruit. It has it's current position, what type of fruit it is
 * the max height etc.
 * @author Ashwin Sekar

 */
public class Fruit {
	int type;
	double posx;
	double posy;
	double angle;
	double dy;
	double tempdy;
	double dx;
	double dangle;
	int maxHeight;
	boolean goingUp;
	boolean hit;
	int separate;
	
	/**
	 * Initializes the fruit
	 * @param t - the type of fruit it is
	 */
	public Fruit(int t) {
		type = t;
		posx = Math.random()*640;
		posy = 0;
		dx = Math.random();
		dy = Math.random() * 5 + 3;
		if(posx > 390) dx = -dx;
		angle = 0;
		dangle = Math.random()*10;
		maxHeight = (int) (Math.random()*200 + 200);
		goingUp = true;
		tempdy = -dy;	
		hit = false;
		separate = 10;
	}
	
	public Fruit(int t, int h, int y, int x) {
		this(t);
		maxHeight = h;
		posy = y;
		posx = x;
		dy = -2;
		goingUp = false;
	}
	
	/**
	 * Updates the fruit's variables. This is called once every 10 milliseconds in the main game class.	
	 */
	public void update() {
		if(hit) separate++;
		posx += dx;
		posy += dy;
		angle += dangle;
		if(posy > maxHeight - 15 && goingUp)  {
			dy = (maxHeight + 15 - posy) / 20;
		}
		if(posy > maxHeight + 14) {
			goingUp = false;
			dy = tempdy;
		}
		if(posy < 0) {
			dx = 0;
			dy = 0;
			dangle = 0;
		}
	}
}
