package maze;

import generation.DepthFirst;

import java.awt.Point;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;

import main.MazeSolver;

public class RecursiveBacktracker extends MazeSolver implements Runnable {

	public RecursiveBacktracker(byte[][] toBeSolved) {
		super(toBeSolved);
	}
	
	@Override
	public void solveMaze(boolean pause) throws InterruptedException {
		totalTime = 0;
		startTime = System.nanoTime();
		
		Point pos = new Point(1,entry); // Position of the person inside maze;
		solvedMaze[entry][0] = 1;
		
		recurse(pause,pos);
		
		solvedMaze[entry][0] = 2;
		endTime = System.nanoTime();
		totalTime = endTime-startTime;
		while(numThreadsdone < 7) {
			try {barrier.await(); } catch (BrokenBarrierException e) {};
		}
	}
	
	public int recurse(boolean pause, Point pos) throws InterruptedException {
		solvedMaze[pos.y][pos.x] = 2;
 		if (pos.y == exit && pos.x == width-1) {
 			routeLength++;
 			return 0;
 		}
		
 		try {barrier.await(); } catch (BrokenBarrierException e) {};
		
		ArrayList<Point> spaces = new ArrayList<Point>(4);
		Random generator = new Random();
		if (solvedMaze[pos.y+1][pos.x] == 0) spaces.add(new Point(pos.x,pos.y+1));
		if (solvedMaze[pos.y-1][pos.x] == 0) spaces.add(new Point(pos.x,pos.y-1));
		if (solvedMaze[pos.y][pos.x+1] == 0) spaces.add(new Point(pos.x+1,pos.y));
		if (solvedMaze[pos.y][pos.x-1] == 0) spaces.add(new Point(pos.x-1,pos.y));
		
		while(!spaces.isEmpty()) {
			int choice = generator.nextInt(spaces.size());
			if (recurse(pause,spaces.get(choice)) == 0) {
				routeLength++;
				numTurns += (solvedMaze[2*pos.y - spaces.get(choice).y][2*pos.x - spaces.get(choice).x] == 2)?0:1;
				return 0;
			}
			spaces.remove(choice);
		}
		
		solvedMaze[pos.y][pos.x] = 0;
		return -1;
	}
	
	public void run() {
		try{
		this.solveMaze(false);
		} catch (InterruptedException e) {};
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException{ 
		DepthFirst his = new DepthFirst(50);
		byte hi[][] = his.generate();
		RecursiveBacktracker solver = new RecursiveBacktracker(hi);
		solver.solveMaze(false);
		System.out.println(solver);
	}

}
