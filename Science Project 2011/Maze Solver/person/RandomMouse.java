package person;

import generation.DepthFirst;

import java.awt.Point;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

import main.MazeSolver;

public class RandomMouse extends MazeSolver implements Runnable{

	public RandomMouse(byte[][] toBeSolved) {
		super(toBeSolved);
	}

	@Override
	public void solveMaze(boolean pause) throws InterruptedException {
		totalTime = 0;
		startTime = System.nanoTime();
		Point pos = new Point(1,entry); // Position of the person inside maze;
		Point dir[] = {new Point(1,0), new Point(0,1), new Point(-1,0), new Point(0,-1)}; // Direction Vectors;
		solvedMaze[entry][0] = 1;
		int cdir = 0,cin = 0;
		try {barrier.await(); } catch (BrokenBarrierException e) {};
		int[] spaces = new int[3];
		Random gen = new Random();
		int choose = 0;
		
		while(((pos.x != width-1) || (pos.y != exit)) ) {
			
			spaces[cin] = (solvedMaze[pos.y + dir[(cdir+3) % 4].y][pos.x + dir[(cdir+3) % 4].x] != 1)?cdir+3+cin++-cin+1:-5;
			spaces[cin] = (solvedMaze[pos.y + dir[(cdir+1) % 4].y][pos.x + dir[(cdir+1) % 4].x] != 1)?cdir+1+cin++-cin+1:-5;
			spaces[cin] = (solvedMaze[pos.y + dir[(cdir) % 4].y][pos.x + dir[(cdir) % 4].x] != 1)?cdir+cin++-cin+1:-5;
			if(cin != 0)
				choose = gen.nextInt(cin);
			
			if(spaces[choose] == -5) {
				cdir = (cdir+2) % 4;
				numTurns += 2;
		
			} else {
				numTurns += (cdir == spaces[choose])?0:1;
				cdir = spaces[choose] % 4;				
			}
			
			solvedMaze[pos.y][pos.x] = 2;
			pos.x += dir[cdir].x;
			pos.y += dir[cdir].y;
			
			try {barrier.await(); } catch (BrokenBarrierException e) {};
			
			routeLength++;
			cin = 0;
		}
		solvedMaze[entry][0] = 2;
		solvedMaze[exit][width-1] = 2;
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
	
	public static void main(String[] args) throws InterruptedException {
		DepthFirst his = new DepthFirst(7);
		byte hi[][] = his.generate();
		RandomMouse solver = new RandomMouse(hi);
		solver.solveMaze(true);
		System.out.println(solver);
		
	}

}
