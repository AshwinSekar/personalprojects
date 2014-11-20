package helicopter;

import java.applet.Applet;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import vocab.Unit;

import dataproccessing.DatabaseLoader;
import dataproccessing.Score;

/**
 * The class that holds the Helicopter Game applet
 * @author Ashwin Sekar
 * 
 *
 */
@SuppressWarnings("serial")
public class HelicopterGame extends Applet implements Runnable{

	private static final int pixels = 390;
	private static final int pixelWidth = 2;
	private static int wait = 15;

	private double[] walls = new double[pixels];
	private double[] walls2 = new double[pixels];
	
	private double cur;
	private double cur2;
	
	private int change;
	private int change2;
	
	private Image helicopter;
	private Image topCloud;
	private Image bottomCloud;
	private Image topCloudGold;
	private Image bottomCloudGold;
	private Image questionCloud;
	private Image lightning;

	private Graphics2D buffer;
	private Image doubleBuffer;
	private Image doubleBufferBackground;
	private Graphics2D bufferBackground;
	private Image background;
	
	private double pos = 300;
	private long score = 0;
	private double move;
	private double dy = 0;
	
	private Random r;
	private ArrayList<Unit> units;
	private boolean verb;
	
	private boolean up = false;
	
	private int barrier = -1;	
	private double barrierPos;
	
	private String top;
	private String bottom;
	private String question;
	private boolean goUp;
	
	private HelicopterListener listener;
	
	private boolean gameOver = false;
	private boolean submitted = false;
	private Image gameOverScreen;
	private int opacity;
	private Image retry;
	private Image quit;
	private Image save;
	private Timer fade;
	private Polygon retryArea;
	private Polygon quitArea;
	private Polygon saveArea;
	private String name = "";
	
	private Image star;
	private ArrayList<Point> stars;
	private int freq;

	private String[] data;
	
	private volatile boolean done = false;

	/**
	 * Initializes the applet
	 */
	@Override
	public void init() {

		try {
			DatabaseLoader.pathToVocab = new FileInputStream(new File(new URL(getCodeBase(), "../data/Vocab.xml").toURI()));
			DatabaseLoader.pathToScores = new FileInputStream(new File(new URL(getCodeBase(), "../data/highscore.xml").toURI()));
			DatabaseLoader.pathToVerbs = new FileInputStream(new File(new URL(getCodeBase(), "../data/Verbs.xml").toURI()));
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		try {
			DatabaseLoader.loadDatabase();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
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
		
		setBackground(new Color(92, 180, 255));
		for(int i = 0; i < pixels/2; i++) {
			walls[i] = (int)((i/(pixels/2.0)) * 250);
			walls2[i] = (int)((i/(pixels/2.0)) * 250);
		}
		for(int i = pixels/2; i < pixels; i++) {
			walls[i] = (int)(((pixels-i)/(pixels/2.0)) * 250);
			walls2[i] = (int)(((pixels-i)/(pixels/2.0)) * 250);
		}
		r = new Random();
		
		change = r.nextInt(250);
		change2 = r.nextInt(250);
		
		cur = r.nextDouble() + 1;
		cur2 = r.nextDouble() + 1;
		
		stars = new ArrayList<Point>();
		freq = r.nextInt(37) + 23;
		
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		listener = new HelicopterListener();		
		this.addKeyListener(listener);
		this.requestFocus();
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		

		doubleBufferBackground = createImage(pixels*pixelWidth,650);
		//doubleBufferBackground = new BufferedImage(pixels*pixelWidth,650,BufferedImage.TYPE_INT_ARGB);
		bufferBackground = (Graphics2D) doubleBufferBackground.getGraphics();
		
		for(int i = 0; i < pixels; i++) {
			bufferBackground.setColor(new Color(50,100,190));
			bufferBackground.fillRect(i*pixelWidth, 0, pixelWidth, (int) walls[i]);
			bufferBackground.setColor(new Color(95,180,32));
			bufferBackground.fillRect(i*pixelWidth,(int)(600-walls2[i]),pixelWidth,(int)walls2[i] + 50);
			bufferBackground.setColor(Color.black);
		}
		
		setSize(pixels*pixelWidth-100,650);
		setVisible(true);
	}
	
	/**
	 * Starts the applet. All main operations occur in a separate thread.
	 */
	@Override
	public void start() {
		run();
	}
	
	/**
	 * Loads all of the images
	 * @throws IOException
	 */
	public void loadImages() throws IOException {
			helicopter = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/helicopter.png"));
			
			topCloud = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/Clouds.png"));
			bottomCloud = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/Clouds.png"));
			topCloudGold = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/GoldenClouds.png"));
			bottomCloudGold = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/GoldenClouds.png"));
			
			questionCloud = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/Clouds.png"));
			
			lightning = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/lightning.png"));
			
			gameOverScreen = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/gameoverscreen.png"));
			retry =ImageIO.read(new URL(getCodeBase(), "helicopter-gr/retry.png"));
			quit = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/quit.png"));
			save = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/save.png"));
			
			star = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/star.png"));
		
			background = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/background.png"));
		
		retryArea = new Polygon(new int[] {200, 282, 282, 200}, new int[] {380, 380, 432, 432}, 4);
		quitArea = new Polygon(new int[] {400, 482, 482, 400}, new int[] {380, 380, 432, 432}, 4);
		saveArea = new Polygon(new int[] {300,382,382,300}, new int[] {230, 230, 282, 282}, 4);
	}
	
	/**
	 * Checks if the helicopter hit a wall, the lightning cloud or went in the wrong path
	 * @return - Whether or not the helicopter hit something
	 */
	public boolean hit() {
		for(int i = 200; i < 227; i+= 10) {
			if(pos + 5 < walls[i/pixelWidth] || pos + 32 > 600-walls2[i/pixelWidth])
				return true;
			if((((pos + 5 < barrierPos + 100 && pos + 5 > barrierPos) || (pos + 32 > barrierPos && pos + 32 < barrierPos + 100)) 
							&& (barrier*pixelWidth > 178 && barrier*pixelWidth < 227)))
				return true;
			if((barrier*pixelWidth > 190 && barrier*pixelWidth < 210 && ((goUp && pos > barrierPos + 100) || (!goUp && pos + 50 <  barrierPos)))) 
				return true;
			
		}
		return false;
	}
	
	/** 
	 * Increments all of the variables 1 clock cycle. Called once every 10 milliseconds
	 */
	public void tick() {
		score++;
		pos += dy;
		if(up) dy -= .098;
		else dy += .098;
		
		for(int i = 0; i < pixels - 1; i++) {
			walls[i] = walls[i+1];
			walls2[i] = walls2[i+1];
		}
		
		walls[pixels-1] += cur;
		walls2[pixels-1] += cur2;
		if(barrier < 0 && 600 - walls[pixels-1] - walls2[pixels-1] > 300) {
			barrier = pixels-1;
			
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

			question = data[0];
			top = data[1];
			bottom = data[2];
			
			goUp = r.nextDouble() < .5;
			if(!goUp) {
				String temp = top;
				top = bottom;
				bottom = temp;
			}
			
			barrierPos = (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0 - 50);
			move = r.nextInt(3) + 1;
			
			topCloud = topCloud.getScaledInstance(7*top.length() + 25 , 47, Image.SCALE_DEFAULT);
			bottomCloud = bottomCloud.getScaledInstance(7*bottom.length() + 25, 50, Image.SCALE_DEFAULT);
			topCloudGold = topCloudGold.getScaledInstance(7*top.length() + 25 , 47, Image.SCALE_DEFAULT);
			bottomCloudGold = bottomCloudGold.getScaledInstance(7*bottom.length() + 25, 50, Image.SCALE_DEFAULT);
			questionCloud = questionCloud.getScaledInstance(7*question.length() + 25, 50, Image.SCALE_DEFAULT);
								
		}
		
		if((cur > 0 && walls[pixels-1] >= change) || (cur < 0 && walls[pixels-1] <= change)) {
			cur = (r.nextDouble() + .1) * -(cur/Math.abs(cur));
			if(cur > 0) change = r.nextInt(Math.max(250-change,1)) + change;
			else change = r.nextInt(Math.max(change,1));
		}
		
		if((cur2 > 0 && walls2[pixels-1] >= change2) || (cur2 < 0 && walls2[pixels-1] <= change2)) {
			cur2 = (r.nextDouble() + .1) * -(cur2/Math.abs(cur2));
			if(cur2 > 0) change2 = r.nextInt(Math.max(250-change2,1)) + change2;
			else change2 = r.nextInt(Math.max(change2,1));
		}
		
		barrierPos += move;
		if(barrier > 0 && (barrierPos > (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0) ||
				barrierPos < (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0 - 100)))
			move = -move;
		
		barrier--;
		
		for(int i = 0; i < stars.size(); i++)
			stars.get(i).x -= pixelWidth;
		if(stars.isEmpty() || (680 - stars.get(stars.size()-1).x >= freq)) {
			freq = r.nextInt(10) + 23;
			stars.add(new Point(779, r.nextInt((int) walls[walls.length - 1]) - 46));
		}		
	}
	
	/**
	 * Paints the applet on the screen
	 * @param g - The graphics
	 */
	public void paintAnimation(Graphics g) {
		Graphics2D pane = (Graphics2D) g;
		doubleBuffer = createImage(pixels*pixelWidth,650);
		buffer = (Graphics2D) doubleBuffer.getGraphics();
		//buffer.drawImage(background, 0, 0, null);
		if(!gameOver) {
			bufferBackground.drawImage(doubleBufferBackground, -pixelWidth, 0, null);
			
			bufferBackground.clearRect((pixels-1)*pixelWidth, 0, pixelWidth, (int) walls[(pixels-2)]);
			bufferBackground.clearRect((pixels-1)*pixelWidth,(int)(600-walls2[(pixels-2)]),pixelWidth,(int)walls2[(pixels-2)] + 50);

			bufferBackground.setColor(new Color(50,100,190));
			bufferBackground.fillRect((pixels-1)*pixelWidth, 0, pixelWidth, (int) walls[(pixels-1)]);
			bufferBackground.setColor(new Color(95,180,32));
			bufferBackground.fillRect((pixels-1)*pixelWidth,(int)(600-walls2[(pixels-1)]),pixelWidth,(int)walls2[(pixels-1)] + 50);
			bufferBackground.drawImage(star, stars.get(stars.size() - 1).x, stars.get(stars.size() - 1).y, null);

			buffer.drawImage(doubleBufferBackground,0,0,null);
			buffer.setFont(new Font("Papyrus", 16, 16));

			if(barrier >= 0) {
				if(pixelWidth*barrier > 200 || !goUp) 
					buffer.drawImage(topCloud, (int) (pixelWidth*barrier - (3.5 * top.length())), (int) (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0 - 150), null);
				else 
					buffer.drawImage(topCloudGold, (int) (pixelWidth*barrier - (3.5 * top.length())), (int) (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0 - 150), null);
				if(pixelWidth*barrier > 200 || goUp)
					buffer.drawImage(bottomCloud, (int) (pixelWidth*barrier - (3.5*bottom.length())), (int) (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0 + 100), null);
				else
					buffer.drawImage(bottomCloudGold, (int) (pixelWidth*barrier - (3.5*bottom.length())), (int) (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0 + 100), null);
				buffer.drawImage(questionCloud, pixelWidth*barrier + 30, (int) (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0 - 25), null);
				
				buffer.drawString(top, (int) (pixelWidth*barrier - (3 * top.length())) + 5, (int) (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0 - 125));
				buffer.drawString(bottom, (int) (pixelWidth*barrier - (3 * bottom.length())) + 5, (int) (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0 + 125));
				buffer.drawString(question, pixelWidth*barrier + 35, (int) (walls[barrier] + (600 - walls[barrier] - walls2[barrier])/2.0) + 5);

				buffer.drawImage(lightning, (int) (pixelWidth*barrier - 12.5),(int) barrierPos, null);
			}		
			
			buffer.setColor(Color.white);
			buffer.drawString("Score: " + score, 100, 645);
			buffer.drawImage(helicopter, 200, (int)pos, null);
			
		} else if(gameOver) {
			buffer.drawImage(doubleBufferBackground,0,0,null);
			buffer.setColor(Color.white);
			buffer.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, (float)(opacity/100.0)));
			buffer.drawImage(gameOverScreen, 100, 0, null);
			buffer.drawImage(save, 400, 230, null);
			buffer.drawImage(retry,300,380,null);
			buffer.drawImage(quit, 500, 380, null);
			buffer.setFont(new Font("Papyrus", 16, 24));
			buffer.drawString("Score:" + score, (int) (441 - ("Score:" + score).length() * 5), 170);
			buffer.setFont(new Font("Papyrus", 16, 16));
			buffer.drawString("Name: " + name, 330, 200);
			buffer.drawString(" ____________________", 375,200);
			buffer.drawString("Highscores", (int)(441 - ("Highscores").length() * 3.5), 460);
			for(int i = 0; i < Math.min(8, DatabaseLoader.highscores.get("Helicopter").size()-1); i++) {
				buffer.drawString(DatabaseLoader.highscores.get("Helicopter").get(i).toString(), (int)(441 - (DatabaseLoader.highscores.get("Helicopter").get(i).toString()).length() * 3.5), 500 + 20*i);
			}
			
		}
		pane.drawImage(doubleBuffer,-100,0,this);
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}	
	
	/**
	 * Shows the game over screen and performs the game over functions
	 */
 	public void gameOver() {
		try {
			helicopter = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/gameover.png"));
		} catch (IOException e) {e.printStackTrace();}
		paintAnimation(this.getGraphics());
		
		opacity = 0;
		fade = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameOver = true;
				opacity += 10;
				if(opacity > 100) {
					opacity = 100;
					fade.stop();
					return;
				}
				paintAnimation(getGraphics());
			}
		});
		fade.setInitialDelay(1000);
		fade.start();
		while(!done) {}
	}

 	/**
 	 * The main loop of the applet. Calls all of the functions. Runs at 10 fps
 	 */
	@Override
	public void run() {
		while(true) {
			long asdfjkl;
			while(!hit()) {
				asdfjkl = System.currentTimeMillis();
				tick();
				paintAnimation(getGraphics());
				asdfjkl += wait;
				try {
					if(asdfjkl - System.currentTimeMillis() < 0)
							System.out.println(asdfjkl - System.currentTimeMillis());
					Thread.sleep(Math.max(asdfjkl - System.currentTimeMillis(), 0));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			gameOver();
			done = false;
			gameOver = false;
			pos = 300;
			score = 0;
			barrier = -1;
			for(int i = 0; i < pixels/2; i++) {
				walls[i] = (int)((i/(pixels/2.0)) * 250);
				walls2[i] = (int)((i/(pixels/2.0)) * 250);
			}
			for(int i = pixels/2; i < pixels; i++) {
				walls[i] = (int)(((pixels-i)/(pixels/2.0)) * 250);
				walls2[i] = (int)(((pixels-i)/(pixels/2.0)) * 250);
			}
			r = new Random();
			
			change = r.nextInt(250);
			change2 = r.nextInt(250);
			
			cur = r.nextDouble() + 1;
			cur2 = r.nextDouble() + 1;
			
			stars.clear();
			submitted = false;
			
			try {
				helicopter = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/helicopter.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			doubleBufferBackground = createImage(pixels*pixelWidth,650);
			bufferBackground = (Graphics2D) doubleBufferBackground.getGraphics();
			for(int i = 0; i < pixels; i++) {
				bufferBackground.setColor(new Color(50,100,190));
				bufferBackground.fillRect(i*pixelWidth, 0, pixelWidth, (int) walls[i]);
				bufferBackground.setColor(new Color(95,180,32));
				bufferBackground.fillRect(i*pixelWidth,(int)(600-walls2[i]),pixelWidth,(int)walls2[i] + 50);
				bufferBackground.setColor(Color.black);
			}
			
			dy = 0;
		}
	}
	
	/**
	 * 
	 * @author Ashwin Sekar
	 * The class that listens on the Helicopter Applet
	 *
	 */
 	private class HelicopterListener implements MouseListener, MouseMotionListener, KeyListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(gameOver) {
				if(quitArea.contains(arg0.getPoint())) {
					try {
						HelicopterGame.this.getAppletContext().showDocument(new URL(HelicopterGame.this.getCodeBase().toString() + "../index.html"));
					} catch (MalformedURLException e) {	e.printStackTrace();}
				} else if(retryArea.contains(arg0.getPoint())) {
					done = true;
				} else if(saveArea.contains(arg0.getPoint()) && !submitted && !name.isEmpty()) {
					try {
						DatabaseLoader.pathToScores = new FileInputStream(new File(new URL(getCodeBase(), "../data/highscore.xml").toURI()));
						DatabaseLoader.addHighScore(new Score(score,name), "Helicopter", new File(new URL(getCodeBase(), "../data/highscore.xml").toURI()));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
					submitted = true;
				
					try {
						save = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/saveSubmit.png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					paintAnimation(getGraphics());
				}				
			}			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {
			HelicopterGame.this.up = true;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			HelicopterGame.this.up = false;
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {	}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			if(gameOver) {
				if(retryArea.contains(arg0.getPoint())) {
					try {
						retry = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/retryIn.png"));
					} catch (IOException e) {e.printStackTrace();}
				} else {
					try {						
						retry = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/retry.png"));
					} catch (IOException e) {e.printStackTrace();}
				}
				
				if(quitArea.contains(arg0.getPoint())) {
					try {						
						quit = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/quitIn.png"));
					} catch (IOException e) {e.printStackTrace();}
				} else {
					try {						
						quit = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/quit.png"));
					} catch (IOException e) {e.printStackTrace();}
				}
				if(saveArea.contains(arg0.getPoint()) && !submitted) {
					try {
						save = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/saveIn.png"));
					} catch (IOException e) {e.printStackTrace();}
				} else if(submitted) {
					try {
						save = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/saveSubmit.png"));
					} catch (IOException e) {e.printStackTrace();}
				} else {
					try {						
						save = ImageIO.read(new URL(getCodeBase(), "helicopter-gr/save.png"));
					} catch (IOException e) {e.printStackTrace();}
				}
				paintAnimation(getGraphics());
			}			
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyChar() == KeyEvent.VK_SPACE) {
				HelicopterGame.this.up = true;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) { 
			if(arg0.getKeyChar() == KeyEvent.VK_SPACE) {
				HelicopterGame.this.up = false;
			}
		}
		
		@Override
		public void keyTyped(KeyEvent arg0) {
			if(!HelicopterGame.this.gameOver) return;
			if(arg0.getKeyChar() == KeyEvent.VK_SPACE) HelicopterGame.this.name += " ";
			else if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE && HelicopterGame.this.name.length() > 0) HelicopterGame.this.name = HelicopterGame.this.name.substring(0,HelicopterGame.this.name.length() - 1); 
			else if(Character.isLetterOrDigit(arg0.getKeyChar())) HelicopterGame.this.name += arg0.getKeyChar();
			paintAnimation(HelicopterGame.this.getGraphics());
		}
		
	}

}
