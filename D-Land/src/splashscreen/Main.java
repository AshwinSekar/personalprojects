package splashscreen;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.GridLayout;
import javax.swing.SwingConstants;

import netscape.javascript.JSObject;

import vocab.Unit;

import java.awt.Font;

/**
 * The splashcreen, the start of the website. It has sections to select which vocab/verb conjugation to be tested on as well as
 * select which game to play
 * @author Ashwin Sekar
 * 
 *
 */
@SuppressWarnings("serial")
public class Main extends JApplet implements MouseMotionListener, MouseListener, ActionListener {
	public Main() {
	}

	JPanel context;
	Image splashscreen;
	Polygon balloon;
	Polygon hut;
	Polygon castle;
	JPanel unitSelection;
	JPanel vocab;
	JCheckBox vocabCheck;
	JPanel verb;
	JCheckBox verbCheck;
	JPanel spanish4;
	JCheckBox world;
	JCheckBox family;
	JCheckBox meeting;
	JCheckBox worldWork;
	JCheckBox history;
	JCheckBox fineArts;
	JPanel spanish5;
	ArrayList<Unit> settings;
	
	private class ImagePanel extends JPanel {
		@Override
		public void paint(Graphics arg0) {
			arg0.drawImage(Main.this.splashscreen, 0, 0, null);
		}
	}
	
	@Override
	public void init() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}  catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}
		try {
			splashscreen = ImageIO.read(new URL(getCodeBase(),"main-gr/startScreen.png"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		settings = new ArrayList<Unit>();
		context = new ImagePanel();
		getContentPane().add(context, BorderLayout.CENTER);
	
		unitSelection = new JPanel();
		getContentPane().add(unitSelection, BorderLayout.SOUTH);
		unitSelection.setLayout(new GridLayout(2, 2, 0, 0));
		
		vocab = new JPanel();
		unitSelection.add(vocab);
		vocab.setLayout(new BorderLayout(0, 0));
		
		vocabCheck = new JCheckBox("Vocab");
		vocabCheck.setVerticalAlignment(SwingConstants.BOTTOM);
		vocabCheck.setHorizontalAlignment(SwingConstants.CENTER);
		vocabCheck.setActionCommand("Vocab");
		vocabCheck.addActionListener(this);
		vocab.add(vocabCheck, BorderLayout.CENTER);
		
		verb = new JPanel();
		unitSelection.add(verb);
		verb.setLayout(new BorderLayout(0, 0));
		
		verbCheck = new JCheckBox("Verb Conjugation");
		verbCheck.setVerticalAlignment(SwingConstants.BOTTOM);
		verbCheck.setHorizontalAlignment(SwingConstants.CENTER);
		verb.add(verbCheck, BorderLayout.CENTER);
		
		spanish4 = new JPanel();
		unitSelection.add(spanish4);
		spanish4.setLayout(new GridLayout(3, 1, 0, 0));
		
		world = new JCheckBox("World");
		world.setEnabled(false);
		world.addActionListener(this);
		world.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		spanish4.add(world);
		
		worldWork = new JCheckBox("World of Work");
		worldWork.setEnabled(false);
		worldWork.addActionListener(this);
		worldWork.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		spanish4.add(worldWork);
		
		history = new JCheckBox("History, Politics, and Social Issues");
		history.setEnabled(false);
		history.addActionListener(this);
		history.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		spanish4.add(history);
		
		spanish5 = new JPanel();
		unitSelection.add(spanish5);
		spanish5.setLayout(new GridLayout(3, 1, 0, 0));
		
		family = new JCheckBox("Family and Home");
		spanish5.add(family);
		family.setEnabled(false);
		family.addActionListener(this);
		family.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		
		meeting = new JCheckBox("Meeting and Personal Needs");
		spanish5.add(meeting);
		meeting.setEnabled(false);
		meeting.addActionListener(this);
		meeting.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		
		fineArts = new JCheckBox("Fine Arts");
		spanish5.add(fineArts);
		fineArts.setEnabled(false);
		fineArts.addActionListener(this);
		fineArts.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		
		balloon = new Polygon(new int[] {20,17,18,21,26,32,39,45,53,61,70,77,78,81,86,87,90,92,92,96,103,107,107,105,103,103,102,101,103,106,107,109,110,110,109,108,106,102,99,98,97,98}, 
				new int[] {0,18,29,39,46,50,55,60,65,69,74,76,79,82,84,87,92,97,101,105,105,102,92,90,78,77,70,65,62,59,53,49,40,40,35,25,16,9,2,2,1,0}, 42);
		hut = new Polygon(new int[] {0,5,9,13,18,23,30,34,39,42,49,53,54,59,62,66,71,77,83,87,92,100,106,114,119,125,132,136,142,149,154,160,167,173,178,185,190,188,186,183,181,178,174,176,175,173,175,174,175,175,174,175,176,174,170,166,162,156,151,144,141,46,40,37,34,33,30,30,30,31,28,17,11,4,1,1},
				new int[] {453,448,445,443,442,441,443,446,446,450,451,455,456,451,447,444,440,437,435,433,431,429,428,430,431,431,434,438,442,448,451,457,462,465,471,476,491,495,498,497,500,505,510,515,520,526,533,542,548,557,562,568,576,581,588,593,598,604,608,610,612,612,608,605,601,597,593,590,572,568,567,570,570,571,571,571},76);
		castle = new Polygon(new int[] {-1,26,46,46,62,63,82,100,100,136,149,162,163,158,139,138,123,120,107,103,104,93,75,67,59,58,48,28,21,3,2,-1,-1}, new int[] {57,0,58,98,97,90,61,89,98,102,69,103,167,167,185,191,190,185,179,181,192,196,196,202,202,210,217,211,216,214,214,214,57}, 33);
		castle.translate(0, 175);
		this.setSize(480,812);
		this.setVisible(true);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if(balloon.contains(arg0.getPoint())) {
			try {
				splashscreen = ImageIO.read(new URL(getCodeBase(),"main-gr/balloon.png"));
				context.repaint();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(hut.contains(arg0.getPoint())) {
			try {
				splashscreen = ImageIO.read(new URL(getCodeBase(),"main-gr/hut.png"));
				context.repaint();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(castle.contains(arg0.getPoint())) {
			try {
				splashscreen = ImageIO.read(new URL(getCodeBase(),"main-gr/castle.png"));
				context.repaint();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				splashscreen = ImageIO.read(new URL(getCodeBase(),"main-gr/startScreen.png"));
				context.repaint();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(balloon.contains(arg0.getPoint())) {
			try {
				if(saveSettings())
					this.getAppletContext().showDocument(new URL(this.getCodeBase().toString() + "helicopter/index.html"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if(hut.contains(arg0.getPoint())) {
			try {
				if(saveSettings())
					this.getAppletContext().showDocument(new URL(this.getCodeBase().toString() + "burritoninja/index.html"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if(castle.contains(arg0.getPoint())) {
			try {
				this.getAppletContext().showDocument(new URL(this.getCodeBase().toString() + "castle/index.html"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Vocab") && vocabCheck.isSelected()) {
			world.setEnabled(true);
			family.setEnabled(true);
			meeting.setEnabled(true);
			worldWork.setEnabled(true);
			history.setEnabled(true);
			fineArts.setEnabled(true);
			world.setSelected(true);
			family.setSelected(true);
			meeting.setSelected(true);
			worldWork.setSelected(true);
			history.setSelected(true);
			fineArts.setSelected(true);
			for(Unit u : Unit.values()) settings.add(u);
		} else if(arg0.getActionCommand().equals("Vocab") && !vocabCheck.isSelected()) {
			world.setEnabled(false);
			family.setEnabled(false);
			meeting.setEnabled(false);
			worldWork.setEnabled(false);
			history.setEnabled(false);
			fineArts.setEnabled(false);
			world.setSelected(false);
			family.setSelected(false);
			meeting.setSelected(false);
			worldWork.setSelected(false);
			history.setSelected(false);
			fineArts.setSelected(false);
			for(Unit u : Unit.values()) settings.remove(u);
		} else if(arg0.getSource().equals(world)) {
			if(world.isSelected()) {
				settings.add(Unit.World);
			} else {
				settings.remove(Unit.World);
			}
		} else if(arg0.getSource().equals(family)) {
			if(family.isSelected()) {
				settings.add(Unit.Family_and_Home);
			} else {
				settings.remove(Unit.Family_and_Home);
			}
		} else if(arg0.getSource().equals(meeting)) {
			if(meeting.isSelected()) {
				settings.add(Unit.Meetings_and_Personal_Needs);
			} else {
				settings.remove(Unit.Meetings_and_Personal_Needs);
			}
		} else if(arg0.getSource().equals(worldWork)) {
			if(worldWork.isSelected()) {
				settings.add(Unit.World_of_Work);
			} else {
				settings.remove(Unit.World_of_Work);
			}
		} else if(arg0.getSource().equals(history)) {
			if(history.isSelected()) {
				settings.add(Unit.History_Politics_and_Social_Issues);
			} else {
				settings.remove(Unit.History_Politics_and_Social_Issues);
			}
		} else if(arg0.getSource().equals(fineArts)) {
			if(fineArts.isSelected()) {
				settings.add(Unit.Fine_Arts);
			} else {
				settings.remove(Unit.Fine_Arts);
			}
		}
	}

	public boolean saveSettings() {
		if(!verbCheck.isSelected() && settings.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please select a vocab unit or verb conjugation below");
			return false;
		}
		try {
			PrintWriter out = new PrintWriter(new File(new URL(getCodeBase(), "data/Settings.txt").toURI()));
			out.println(verbCheck.isSelected());
			for(Unit u : settings) 
				out.println(u);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}		
		return true;
	}

}
