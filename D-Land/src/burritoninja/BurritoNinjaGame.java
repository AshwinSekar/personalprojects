package burritoninja;

import java.applet.Applet;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dataproccessing.DatabaseLoader;
import dataproccessing.Score;

import vocab.Unit;

/** 
 * 
 * The applet class for the Burrito Ninja Game
 * @author Ashwin Sekar
 *
 */
@SuppressWarnings("serial")
public class BurritoNinjaGame extends Applet{
	private BufferedImage[] types; 
	private BufferedImage[] slicedLeft;
	private BufferedImage[] slicedRight;
	
	private Rectangle[] typeHitBox;
	private AffineTransform at;
	private Shape rotatedRect;
	private Polygon lineIntersect;

	private BufferedImage background;
	private volatile ArrayList<Fruit> fruits;
	private volatile ArrayList<Point> mouseTrail;
	private Image buffer;
	private Graphics2D g;
	private Image rotate;
	private Graphics2D gr;
	private BurritoNinjaListener listener;

	private Random r;
	private ArrayList<Unit> units;
	private boolean verb;

	private String question = "";
	private String nacho = "";
	private String burrito = "";
	private String taco = "";
	private String data[];
	private int type;

	private int lives;
	private Image hotSauce;

	private boolean start;
	private int time;
	
	private boolean gameOver;
	private String name;

	private int wave;
	private boolean showWave;
	private int curFruits;
	
	private Image sensei;
	
	/**
	 * The initializing method of the applet. Loads all of the necessary images, and also calls the loadDatabse() method of the DatabaseLoader method.
	 * It also reads the unit and verb conjugation settings from the Settings.txt file.
	 */
	@Override
	public void init() {
		lives = 3;
		start = false;
		gameOver = false;
		wave = 1;
		showWave = false;
		curFruits = 0;
		name = "";
		try {
			DatabaseLoader.pathToVocab = new FileInputStream(new File(new URL(getCodeBase(), "../data/Vocab.xml").toURI()));
			DatabaseLoader.pathToScores = new FileInputStream(new File(new URL(getCodeBase(), "../data/highscore.xml").toURI()));
			DatabaseLoader.pathToVerbs = new FileInputStream(new File(new URL(getCodeBase(), "../data/Verbs.xml").toURI()));
			DatabaseLoader.loadDatabase();
		} catch (ParserConfigurationException e2) {
			e2.printStackTrace();
		} catch (SAXException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		fruits = new ArrayList<Fruit>();
		mouseTrail = new ArrayList<Point>();
		types = new BufferedImage[3];
		typeHitBox = new Rectangle[3];
		slicedLeft = new BufferedImage[3];
		slicedRight = new BufferedImage[3];
		try {
			types[0] = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/burrito.png"));
			types[1] = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/taco.png"));
			types[2] = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/nacho.png"));
			background = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/background.png"));
			hotSauce = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/hotsauce.png"));
			slicedLeft[0] = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/burritoLeft.png"));
			slicedLeft[1] = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/tacoLeft.png"));
			slicedLeft[2] = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/nachoLeft.png"));
			slicedRight[0] = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/burritoRight.png"));
			slicedRight[1] = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/tacoRight.png"));
			slicedRight[2] = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/nachoRight.png"));
			sensei = ImageIO.read(new URL(getCodeBase(), "burritoninja-gr/sensei.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		units = new ArrayList<Unit>();
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL(getCodeBase(),"../data/Settings.txt").openStream()));
			verb = Boolean.parseBoolean(in.readLine());
			String s = in.readLine();
			while(s != null && !s.isEmpty()) {
				units.add(Unit.valueOf(s));
				s = in.readLine();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(int i = 0; i < types.length; i++)
			typeHitBox[i] = new Rectangle(types[i].getWidth(),types[i].getHeight());

		r = new Random();
		type = 0;	

		listener = new BurritoNinjaListener();
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.addKeyListener(listener);
		setBackground(Color.white);
		setSize(640,500);
		setVisible(true);
		
	}

	/**
	 * Reads a question and 3 answer choices from the DatabaseLoader and randomly assigns them to a Spanish food.
	 * Also pauses the game for 4 seconds so the user can read the answer choices
	 */
	public void loadQuestion() {
		if(verb == false && units.size() > 0) {
			data = DatabaseLoader.getVocab(units);
		} else if(verb == true && units.size() == 0) {
			data = DatabaseLoader.getVerbs();
		} else if(verb == true && units.size() > 0) {
			if(r.nextDouble() > .5) {
				data = DatabaseLoader.getVocab(units);
			} else {
				data = DatabaseLoader.getVerbs();
			}
		}
		type = r.nextInt(types.length);
		question = data[0];
		if(type  == 0) {
			burrito = data[1];
			nacho = data[2];
			taco = data[3];
		} else if(type == 1) {
			taco = data[1];
			burrito = data[2];
			nacho = data[3];
		} else if(type == 2) {
			nacho = data[1];
			burrito = data[2];
			taco = data[3];
		}
	}

	/**
	 * The main loop of the applet. Performs tasks such as incrementing score and erasing the end part of the mouse trail.
	 * Calls repaint and updates the state of the fruits at 10fps
	 */
	@Override
	public void start() {
		while(true) {
		loadQuestion();
		showStart();
		int i = 0;
		fruits.add(new Fruit(r.nextInt(types.length)));
		fruits.add(new Fruit(r.nextInt(types.length)));
		while(!fruits.isEmpty() || lives > 0) {
			i = (i + 1) % 4;
			if(i == 0 && !mouseTrail.isEmpty()) mouseTrail.remove(0);
			
			if(curFruits > wave*3 && fruits.isEmpty()) {
				curFruits = 0;
				wave++;
				loadQuestion();
				waveStart();
				for(int j = 0; j < Math.ceil(wave/2.0) + 1; j++) {
					fruits.add(new Fruit(r.nextInt(types.length)));
				}
			}
			for(int j = 0; j < fruits.size(); j++) {
				fruits.get(j).update();
				if(fruits.get(j).posy < 0) {
					curFruits++;
					if(!fruits.get(j).hit && fruits.get(j).type == type) lives--;
					if(lives > 0 && curFruits <=  wave*3) fruits.add(new Fruit(r.nextInt(types.length)));
					fruits.remove(j);
					j--;
				}
			}
			
			repaint();
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		gameOver = true;
		fruits.add(new Fruit(0,540-425,520-425,320 - types[0].getWidth()/2));
		fruits.add(new Fruit(1,540-425,520-425,50));
		fruits.add(new Fruit(2,540-425,520-425,500));
		while(fruits.size() == 3) {
			i = (i + 1) % 4;
			if(i == 0 && !mouseTrail.isEmpty()) mouseTrail.remove(0);
			if(fruits.get(0).hit  && name.isEmpty()) fruits.get(0).hit = false;
			for(int j = 0; j < fruits.size(); j++) {
				if(fruits.get(j).hit) fruits.get(j).update();
			}
			if(fruits.get(1).posy < 0) {
				fruits.remove(1);
			} else if(fruits.get(2).posy < 0) {
				fruits.remove(2);
			}
			if(fruits.get(0).posy < 0 && fruits.get(0).hit) {
				fruits.get(0).hit = false;
				try {
					DatabaseLoader.pathToScores = new FileInputStream(new File(new URL(getCodeBase(), "../data/highscore.xml").toURI()));
					DatabaseLoader.addHighScore(new Score(wave,name), "Burrito", new File(new URL(getCodeBase(), "../data/highscore.xml").toURI()));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}	
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(fruits.get(1).type != 1) {
			lives = 3;
			start = false;
			gameOver = false;
			wave = 1;
			showWave = false;
			curFruits = 0;
			name = "";
			fruits = new ArrayList<Fruit>();
			mouseTrail = new ArrayList<Point>();
			type = 0;	
			continue;
		} else {
			try {
				getAppletContext().showDocument(new URL(getCodeBase().toString() + "../index.html"));
			} catch (MalformedURLException e) {	e.printStackTrace();}
			break;
		}
		}
	}

	/**
	 * Paints the game on the screen
	 */
	@Override
	public void paint(Graphics g1) {
		Graphics2D pane = (Graphics2D) g1;
		buffer = createImage(640,500);
		g = (Graphics2D) buffer.getGraphics();
		g.drawImage(background,0,100,null);
		g.setColor(Color.black);
		g.fillRect(0, 0, 640, 100);

		g.setColor(Color.white);
		g.setFont(new Font("Trebuchet MS", 0, 16));
		g.drawString(question, (int) ((640 - 7.5*question.length())/2), 16);
		g.drawImage(types[0],320 - types[0].getWidth()/2, 20 + (30 - types[0].getHeight()/2),null);
		g.drawImage(types[1],140 - types[1].getWidth()/2, 20 + (30 - types[1].getHeight()/2),null);
		g.drawImage(types[2],500 - types[2].getWidth()/2, 20 + (30 - types[2].getHeight()/2),null);
		g.drawString(burrito,(int)(320 - 7.5*(burrito.length()/2)), 96);
		g.drawString(taco,(int)(140 - 7.5*(taco.length()/2)), 96);
		g.drawString(nacho,(int)(500 - 7.5*(nacho.length()/2)), 96);	

		for(int i = 0; i < lives; i++)
			g.drawImage(hotSauce,5 + 17*i,445,null);

		if(start) {
			g.setFont(new Font("Trebuchet MS",0, 48));
			if(time == 3)
				g.setColor(Color.blue);
			else if(time == 2)
				g.setColor(Color.red);
			else if(time == 1)
				g.setColor(Color.green);
			else
				g.setColor(Color.orange);
			if(time != 0) {
				g.drawString(time + "",315,274);
			} else {
				g.drawString("Start!", 274, 274);
			}
		} else if(showWave) {
			g.setFont(new Font("Trebuchet MS",0, 48));
			g.setColor(Color.red);
			g.drawString("Wave " + wave, 254,274);
		} else {
			for(Fruit f : fruits) {
				if(f.hit) {
					rotate = new BufferedImage( 
							Math.max(slicedLeft[f.type].getWidth(),slicedLeft[f.type].getHeight()), 
							Math.max(slicedLeft[f.type].getWidth(),slicedLeft[f.type].getHeight()), 
							BufferedImage.TYPE_INT_ARGB); 
					gr = (Graphics2D) rotate.getGraphics();
					gr.setColor(new Color(255,255,255,0)); 
					gr.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
					gr.fillRect(0, 0, Math.max(slicedLeft[f.type].getWidth(),slicedLeft[f.type].getHeight()), Math.max(slicedLeft[f.type].getWidth(),slicedLeft[f.type].getHeight()));
					gr.translate(Math.max(slicedLeft[f.type].getWidth(),slicedLeft[f.type].getHeight())/2, Math.max(slicedLeft[f.type].getWidth(),slicedLeft[f.type].getHeight())/2);
					gr.rotate(Math.toRadians(f.angle % (360)));
	
					gr.drawImage(slicedLeft[f.type], -slicedLeft[f.type].getWidth()/2, -slicedLeft[f.type].getHeight()/2, null);
					g.drawImage(rotate,(int)f.posx - f.separate, (int)(500-f.posy), null);
					
					rotate = new BufferedImage( 
							Math.max(slicedRight[f.type].getWidth(),slicedRight[f.type].getHeight()), 
							Math.max(slicedRight[f.type].getWidth(),slicedRight[f.type].getHeight()), 
							BufferedImage.TYPE_INT_ARGB); 
					gr = (Graphics2D) rotate.getGraphics();
					gr.setColor(new Color(255,255,255,0)); 
					gr.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
					gr.fillRect(0, 0, Math.max(slicedRight[f.type].getWidth(),slicedRight[f.type].getHeight()), Math.max(slicedRight[f.type].getWidth(),slicedRight[f.type].getHeight()));
					gr.translate(Math.max(slicedRight[f.type].getWidth(),slicedRight[f.type].getHeight())/2, Math.max(slicedRight[f.type].getWidth(),slicedRight[f.type].getHeight())/2);
					gr.rotate(Math.toRadians(f.angle % (360)));
	
					gr.drawImage(slicedRight[f.type], -slicedRight[f.type].getWidth()/2, -slicedRight[f.type].getHeight()/2, null);
					g.drawImage(rotate,(int)f.posx + f.separate, (int)(500-f.posy), null);
				} else {
					rotate = new BufferedImage( 
							types[f.type].getWidth(), 
							types[f.type].getWidth(), 
							BufferedImage.TYPE_INT_ARGB); 
					gr = (Graphics2D) rotate.getGraphics();
					gr.setColor(new Color(255,255,255,0)); 
					gr.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
					gr.fillRect(0, 0, types[f.type].getWidth(), types[f.type].getWidth());
					gr.translate(types[f.type].getWidth()/2, types[f.type].getWidth()/2);
					gr.rotate(Math.toRadians(f.angle % (360)));
	
					gr.drawImage(types[f.type], -types[f.type].getWidth()/2, -types[f.type].getHeight()/2, null);
					g.drawImage(rotate,(int)f.posx, (int)(500-f.posy), null);
				}
			}
		}
		if(gameOver) {
			g.setColor(new Color(0,157,217));
			g.setFont(new Font("Trebuchet MS", 16, 36));
			g.drawString("You survived to wave " + wave, 75, 150);
			rotate = new BufferedImage( 
					sensei.getWidth(null), 
					sensei.getHeight(null), 
					BufferedImage.TYPE_INT_ARGB); 
			gr = (Graphics2D) rotate.getGraphics();
			gr.setColor(new Color(255,255,255,0)); 
			gr.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
			gr.fillRect(0, 0, sensei.getWidth(null), sensei.getHeight(null));
			gr.drawImage(sensei,0, 0, null);
			g.drawImage(rotate,15,170,null);
			g.setFont(new Font("Trebuchet MS", 16, 18));
			g.drawString("Name: " + name, 180 + 26, 400);
			g.drawString(" ____________________", 225 + 26,400);
			//g.drawImage(types[0],320 - types[0].getWidth()/2,445,null);
			g.setColor(Color.white);
			g.drawString("Save Highscore",300 - types[0].getWidth()/2,495);
			//g.drawImage(types[1],50,425,null);
			g.drawString("Retry", 70,495);
			//g.drawImage(types[2],500,425,null);
			g.drawString("Quit", 530, 495);
			g.setColor(Color.red);
			g.setFont(new Font("Trebuchet MS", 16, 24));
			g.drawString("Highscores", 500, 150);
			g.setFont(new Font("Trebuchet MS", 16, 18));
			for(int i = 0; i < Math.min(10, DatabaseLoader.highscores.get("Burrito").size()-1); i++) {
				g.drawString(DatabaseLoader.highscores.get("Burrito").get(i).toString(), (int)(550 - (DatabaseLoader.highscores.get("Burrito").get(i).toString()).length() * 3.5), 200 + 20*i);
			}
		}
		g.setColor(Color.red);
		g.setStroke(new BasicStroke(3F));
		for(int i = 0; i < mouseTrail.size()-1; i++) {
			g.drawLine(mouseTrail.get(i).x, mouseTrail.get(i).y, mouseTrail.get(i+1).x, mouseTrail.get(i+1).y);
		}
		pane.drawImage(buffer,0,0,null);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}	

	/**
	 * Shows the 3-2-1-Start animation
	 */
	public void showStart() {
		start = true;
		int i = 0;
		long startTime = System.currentTimeMillis();
		for(time = 3; time >= 0;) {
			i = (i + 1) % 4;
			if(i == 0 && !mouseTrail.isEmpty()) mouseTrail.remove(0);
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if((System.currentTimeMillis() - startTime) > 1000) {
				time--;
				startTime = System.currentTimeMillis();
			}
		}
		start = false;
	}

	public void waveStart() {
		showWave = true;
		repaint();
		int i = 0;
		long startTime = System.currentTimeMillis();
		while((System.currentTimeMillis() - startTime) < 4000) {
			i = (i + 1) % 4;
			if(i == 0 && !mouseTrail.isEmpty()) mouseTrail.remove(0);
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		showWave = false;
	}
	
	/**
	 * 
	 * @author Ashwin Sekar
	 * The class that listens to Mouse actions on the Burrito Ninja applet. It uses the mouse dragged to create the mouse trail.
	 * It also does the hit detection and updates the fruit and lives as necessary.
	 *
	 */
	private class BurritoNinjaListener implements MouseListener, MouseMotionListener, KeyListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			for(int i = 0; i < fruits.size(); i++) {
				if(fruits.get(i).hit) continue;
				at = new AffineTransform(); 
				at.rotate(Math.toRadians(fruits.get(i).angle % 360), (int)(types[fruits.get(i).type].getWidth()/2),(int)(types[fruits.get(i).type].getHeight()/2));
				rotatedRect = at.createTransformedShape(typeHitBox[fruits.get(i).type]); 
				at = new AffineTransform();
				at.translate((int)(fruits.get(i).posx),(int)(500-fruits.get(i).posy + types[fruits.get(i).type].getWidth()/2 - types[fruits.get(i).type].getHeight()/2)); 
				rotatedRect = at.createTransformedShape(rotatedRect); 
				if(!mouseTrail.isEmpty())
					lineIntersect = new Polygon(new int[] {e.getX(), mouseTrail.get(mouseTrail.size()-1).x}, new int[] {e.getY(), mouseTrail.get(mouseTrail.size()-1).y},2);
				if(rotatedRect.contains(e.getPoint()) || ((!mouseTrail.isEmpty() && lineIntersect.intersects(rotatedRect.getBounds2D())))) {  
					fruits.get(i).hit = true;
					if(fruits.get(i).type != type) lives--;
					i--;
				}
			}
			if(mouseTrail.size() > 5) {
				mouseTrail.remove(0);
				mouseTrail.add(e.getPoint());
			} else {
				mouseTrail.add(e.getPoint());
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			mouseTrail.clear();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent arg0) {

		}
		
		@Override
		public void keyReleased(KeyEvent arg0) { 

		}
		
		@Override
		public void keyTyped(KeyEvent arg0) {
			if(!BurritoNinjaGame.this.gameOver) return;
			if(arg0.getKeyChar() == KeyEvent.VK_SPACE && name.length() < 12) BurritoNinjaGame.this.name += " ";
			else if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE && BurritoNinjaGame.this.name.length() > 0) BurritoNinjaGame.this.name = BurritoNinjaGame.this.name.substring(0,BurritoNinjaGame.this.name.length() - 1); 
			else if(Character.isLetterOrDigit(arg0.getKeyChar()) && name.length() < 12) BurritoNinjaGame.this.name += arg0.getKeyChar();
			repaint();
		}

	}

}
