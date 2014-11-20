package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.concurrent.CyclicBarrier;

public class MazeSolver implements Runnable{

	protected static CyclicBarrier barrier;
	protected static int numThreadsdone = 0;
	static int N = 7;
	
	public int width;
	public int height;
	public int entry;
	public int exit;
	public byte maze[][];
	public byte solvedMaze[][];
	public long routeLength;
	public long numTurns;
	public long startTime;
	public long endTime;
	public long totalTime;
	
	public MazeSolver(byte toBeSolved[][]) {
		
		this.height = toBeSolved.length;
		this.width = toBeSolved[0].length;
		this.maze = MazeSolver.clone(toBeSolved);
		this.solvedMaze = MazeSolver.clone(toBeSolved);
		this.findEntry();
		this.findExit();
		routeLength = 0;
		numTurns = 0;
	}
	
	public void run() {
		
	}
	
	public static BufferedImage toPng(MazeSolver mazes[], int trial, String generation, boolean save) {
		int size = mazes[0].width;
		int curr = 0;
		BufferedImage buffImage = new BufferedImage(5*(4*size+10), 8*size+40, BufferedImage.TYPE_INT_RGB);
		Graphics2D image = buffImage.createGraphics();
		image.setBackground(new Color(16777215));
		image.clearRect(0, 0,5*(4*size+10), 8*size+40);
		image.setFont(new Font("Comic Sans MS",Font.BOLD,12));
		
		//First row
		for(int x = 0; x < 5; x++) {
			image.setColor(new Color(4210752));
			image.drawString(mazes[x].getClass().getSimpleName(), curr, 15);
			for(int i = 0; i < 4*size; i+=4) {
				for(int j = 0; j < 4*size; j+=4) {
					int pixel;
					
					if(mazes[x].solvedMaze[j/4][i/4] == 1) {
						pixel = 4210752;
					} else if(mazes[x].solvedMaze[j/4][i/4] == 0) {
						pixel = 16777215;
					} else if(mazes[x].solvedMaze[j/4][i/4] == 2) {
						pixel = 2003199;
					} else {
						pixel = 12500670;
					}
					image.setColor(new Color(pixel));
					image.fillRect(i+curr, j+20, 4, 4);
				}
			}
			curr += 4* size+10;
		}
		curr = 0;
		//Second Row
		for(int x = 5; x < 7; x++) {
			image.setColor(new Color(4210752));
			image.drawString(mazes[x].getClass().getSimpleName(), curr, 4*size+35);
			for(int i = 0; i < 4*size; i+=4) {
				for(int j = 0; j < 4*size; j+=4) {
					int pixel;
					
					if(mazes[x].solvedMaze[j/4][i/4] == 1) {
						pixel = 4210752;
					} else if(mazes[x].solvedMaze[j/4][i/4] == 0) {
						pixel = 16777215;
					} else if(mazes[x].solvedMaze[j/4][i/4] == 2) {
						pixel = 2003199;
					} else {
						pixel = 12500670;
					}
					image.setColor(new Color(pixel));
					image.fillRect(i+ curr, j+4*size + 40 , 4, 4);
				}
			}
			curr += 4* size+10;
		}
		image.dispose();
		if (save) {
		try {
		    // Save as PNG
		    File file = new File("Data/Solved Mazes/"+generation+"Trial"+trial+".png");
		    ImageIO.write(buffImage, "png", file);
		} catch (IOException e) {} 
		}
		return buffImage;
	}
	
	public void findEntry(){
		
		for(entry = 0; entry < height; entry++) {
			if (maze[entry][0] == 0)
				return;
		}		
		
		System.err.println("Error: This maze has no entry point");
		System.err.println(this.toString());
		entry = -1;
	}
	
	public void findExit(){
		
		for(exit = 0; exit < height; exit++) {
			if (maze[exit][width-1] == 0)
				return;
		}
		
		System.err.println("Error: This maze has no exit point");
		System.err.println(this.toString());
		exit = -1;
	}
	
	public static byte[][] clone(byte[][] orig) {
		
		byte[][] result = new byte[orig.length][orig[0].length];
		
		for(int i = 0; i < orig.length; i++) {
			for(int j = 0; j < orig[0].length; j++) {
				result[i][j] = orig[i][j];
			}
		}
		return result;
	}
	
	public void solveMaze(boolean pause) throws InterruptedException { };

	// For Debugging
	@Override
	public String toString() {
		String toString = "";
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				toString += " O+*".substring(Math.min(Math.abs(solvedMaze[i][j]),3), Math.min(Math.abs(solvedMaze[i][j]),3)+1);
			}
			toString += "\n";
		}
		toString+="The "+this.getClass().getSimpleName()+" Maze Solving Algorithm \n";
		toString+= "Number of steps:" + routeLength + " Number of turns:" + numTurns+ " Time taken:" + Double.toString(totalTime/Math.pow(10,-9))  + "\n";
		return toString;
	}
	
	public String analysis() {
		String toString = "";
		toString+="The "+this.getClass().getSimpleName()+" Maze Solving Algorithm \n";
		toString+= "Number of steps:" + routeLength + " Number of turns:" + numTurns+ " Time taken:" + totalTime  + "\n";
		return toString;
	}
	
}
