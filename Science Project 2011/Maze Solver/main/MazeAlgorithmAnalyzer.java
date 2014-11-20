package main;
import java.awt.Component;
import java.awt.image.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import generation.*;
import maze.*;
import person.*;

import javax.swing.*;

import java.awt.*;

public class MazeAlgorithmAnalyzer {
	
	final static MazeSolver mazes[] = new MazeSolver[7];
	public static class loadImage extends Component {
		private static final long serialVersionUID = 1L;
		BufferedImage img;
		public loadImage() {
			img = MazeSolver.toPng(mazes, 0, "DepthFirst", false);
		       
		}
	    @Override
		public Dimension getPreferredSize() {
	        if (img == null) {
	             return new Dimension(100,100);
	        } else {
	           return new Dimension(img.getWidth(null), img.getHeight(null));
	       }
	    }
	    @Override
		public void paint(Graphics g) {
	    	img = MazeSolver.toPng(mazes, 0, "DepthFirst", false);
	        g.drawImage(img, 0, 0, null);
	    }

	} 
	
	public static void main(String[] args) throws InterruptedException{
		DepthFirst generator = new DepthFirst(30);
		byte[][] hi = generator.generate();
 		final JFrame frame = new JFrame("DepthFirstTrial0");
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 		
		mazes[0] = new RandomMouse(hi);
		mazes[1] = new LeftHandFollower(hi);
		mazes[2] = new RightHandFollower(hi);
		mazes[3] = new Pledge(hi);
		mazes[4] = new Tremaux(hi);
		mazes[5] = new RecursiveBacktracker(hi);
		mazes[6] = new DeadEndFiller(hi);
 		frame.getContentPane().add(new MazeAlgorithmAnalyzer.loadImage());
	  	frame.pack();
 		frame.setVisible(true);
	   MazeSolver.barrier = new CyclicBarrier(MazeSolver.N);/*,
                 new Runnable() {
		   		   @Override
                   public void run() {
		   			   frame.getContentPane().repaint();
                   }
                 });*/

		Thread mazeThreads[] = new Thread[7];
		for(int i = 0; i < 7; i++) {
			mazeThreads[i] = new Thread(mazes[i]);
		}
						
		for(int i = 0; i < 7; i++) {
			mazeThreads[i].start();
		}
		
		
			/*while(MazeSolver.barrier.getNumberWaiting() != MazeSolver.N) {}
			//Thread.sleep(100);
			//frame.repaint();
			try {MazeSolver.barrier.await(); } catch (BrokenBarrierException e) {};*/
		Thread.sleep(1000);
		frame.repaint();
		MazeSolver.toPng(mazes, 0, "DepthFirst", true);

 		

	}

}
