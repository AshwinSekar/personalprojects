package highscorepalace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dataproccessing.DatabaseLoader;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class HighscorePalace extends JApplet {
	public HighscorePalace() {
	}

	private JScrollPane p; 
	private JScrollPane r;
	private JTable t;
	private JTable q;
	private int a;
	private int b;
	private JPanel panel;
	private JPanel panel_1;
	private Image background;
	private JTabbedPane tabbedPane;

	@Override
	public void init() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		try {
			DatabaseLoader.pathToVocab = new FileInputStream(new File(new URL(getCodeBase(), "../data/Vocab.xml").toURI()));
			DatabaseLoader.pathToScores = new FileInputStream(new File(new URL(getCodeBase(), "../data/highscore.xml").toURI()));
			DatabaseLoader.pathToVerbs = new FileInputStream(new File(new URL(getCodeBase(), "../data/Verbs.xml").toURI()));			DatabaseLoader.loadDatabase();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a = DatabaseLoader.highscores.get("Helicopter").size();
		b = DatabaseLoader.highscores.get("Burrito").size();
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(150, 10));
		add(panel);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);

		p = new JScrollPane();
		p.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		p.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tabbedPane.addTab("Hot Air Balloon", null, p, null);
		t = new JTable(new TableModel() {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}

			@Override
			public Object getValueAt(int arg0, int arg1) {
				if(arg1 == 0 && arg0 < a-1) return DatabaseLoader.highscores.get("Helicopter").get(arg0).name;
				if(arg1 == 1 && arg0 < a-1) return DatabaseLoader.highscores.get("Helicopter").get(arg0).score;
				return "";
			}

			@Override
			public int getRowCount() {
				return a-1;
			}

			@Override
			public String getColumnName(int arg0) {
				if(arg0 == 0) return "Player";
				if(arg0 == 1) return "Score";
				return "YOU FAILED";
			}

			@Override
			public int getColumnCount() {
				return 2;
			}

			@Override
			public void addTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub

			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if(columnIndex == 0) return String.class;
				return Long.class;
			}

			@Override
			public void removeTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub

			}

		});
		t.setEnabled(true);
		t.setRowSelectionAllowed(false);
		t.setShowGrid(false);
		t.getColumnModel().getColumn(0).setPreferredWidth(200);
		t.getColumnModel().getColumn(1).setPreferredWidth(200);
		t.setFillsViewportHeight(true);
		t.setAutoCreateRowSorter(true);
		p.setViewportView(t);		

		r = new JScrollPane();
		r.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		r.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tabbedPane.addTab("Burrito Ninja", null, r, null);

		q = new JTable(new TableModel() {

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}

			@Override
			public Object getValueAt(int arg0, int arg1) {
				if(arg1 == 0 && arg0 < b-1) return DatabaseLoader.highscores.get("Burrito").get(arg0).name;
				if(arg1 == 1 && arg0 < b-1) return DatabaseLoader.highscores.get("Burrito").get(arg0).score;
				return "";
			}

			@Override
			public int getRowCount() {
				return b-1;
			}

			@Override
			public String getColumnName(int arg0) {
				if(arg0 == 0) return "Player";
				if(arg0 == 1) return "Score";
				return "YOU FAILED";
			}

			@Override
			public int getColumnCount() {
				return 2;
			}

			@Override
			public void addTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub

			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if(columnIndex == 0) return String.class;
				return Long.class;
			}

			@Override
			public void removeTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub

			}

		});

		q.setEnabled(true);
		q.setRowSelectionAllowed(false);
		q.setShowGrid(false);
		q.getColumnModel().getColumn(0).setPreferredWidth(200);
		q.getColumnModel().getColumn(1).setPreferredWidth(200);
		q.setFillsViewportHeight(true);
		q.setAutoCreateRowSorter(true);
		r.setViewportView(q);		
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(150, 10));
		getContentPane().add(panel_1);
		try {
			background = ImageIO.read(new URL(getCodeBase(), "palace-gr/Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.setOpaque(false);
		panel_1.setOpaque(false);
		setSize(680,650);
		setVisible(true);
	}

	@Override
	public void paint(Graphics arg0) {
		Graphics2D g = (Graphics2D) arg0;
		g.drawImage(background,0,0,null);
		tabbedPane.repaint();
	}
}
