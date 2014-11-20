package maze;

import main.MazeSolver;
import generation.DepthFirst;

import java.awt.Point;
import java.util.concurrent.BrokenBarrierException;

public class DeadEndFiller extends MazeSolver implements Runnable{

	public DeadEndFiller(byte[][] toBeSolved) {
		super(toBeSolved);
	}
	
	public void fill() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < height; j++) {
				solvedMaze[i][j] = (byte)((solvedMaze[i][j] == 0)?2:1);
			}
		}
	} 
	
	public boolean isDeadEnd(Point pos) {
		if(solvedMaze[pos.y][pos.x] == 1 || solvedMaze[pos.y][pos.x] == 0) return false;
		
		int freeSpaces = 0;
		if(solvedMaze[pos.y+1][pos.x] == 2) freeSpaces++;
		if(solvedMaze[pos.y-1][pos.x] == 2) freeSpaces++;
		if(solvedMaze[pos.y][pos.x+1] == 2) freeSpaces++;
		if(solvedMaze[pos.y][pos.x-1] == 2) freeSpaces++;
		
		return (freeSpaces > 1)?false:true;
	}
	
	@Override
	public void solveMaze(boolean pause) throws InterruptedException {
		startTime = System.nanoTime();
		this.fill();
		int numOfDeadEnds;
		do {
			numOfDeadEnds = 0;
			for(int i =1; i < height-1; i++) {
				for(int j = 1; j < width-1; j++) {
					if(this.isDeadEnd(new Point(j,i))) {
						solvedMaze[i][j] = 0;
						numOfDeadEnds++;
					}
				}
			}
			try {barrier.await(); } catch (BrokenBarrierException e) {};
		} while(numOfDeadEnds != 0);
		endTime = System.nanoTime();
		totalTime = endTime-startTime;
		while(numThreadsdone < 7) {
			try {barrier.await(); } catch (BrokenBarrierException e) {};
		}
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
		DepthFirst his = new DepthFirst(15);
		byte hi[][] = his.generate();
		DeadEndFiller solver = new DeadEndFiller(hi);
		solver.solveMaze(true);
		System.out.println(solver);
	}

}
