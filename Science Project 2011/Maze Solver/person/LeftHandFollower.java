package person;

import main.MazeSolver;
import generation.DepthFirst;
import java.awt.Point;
import java.util.concurrent.BrokenBarrierException;

public class LeftHandFollower extends MazeSolver implements Runnable{
	
	public LeftHandFollower(byte maze[][]) {
		super(maze);
	}
	
	@Override
	public void solveMaze(boolean pause) throws InterruptedException {
		
		totalTime = 0;
		startTime = System.nanoTime();
		Point pos = new Point(0,entry); // Position of the person inside maze;
		Point dir[] = {new Point(1,0), new Point(0,1), new Point(-1,0), new Point(0,-1)}; // Direction Vectors;
		int cdir = 0;
		solvedMaze[pos.y][pos.x] = 2;
		try {barrier.await(); } catch (BrokenBarrierException e) {};
		Point positionLeft, positionRight, positionFront;
		
		while((pos.x != width-1) || (pos.y != exit)) {
			
			positionLeft = new Point(pos.x + dir[(cdir+3) % 4].x, pos.y + dir[(cdir+3) % 4].y);
			positionRight = new Point(pos.x + dir[(cdir+1) % 4].x, pos.y + dir[(cdir+1) % 4].y);
			positionFront = new Point(pos.x + dir[(cdir) % 4].x, pos.y + dir[(cdir) % 4].y);
			
			if((positionLeft.y != height) && (positionLeft.y != -1) && 
					(solvedMaze[positionLeft.y][positionLeft.x] != 1)) {
				cdir = (cdir+3) % 4;
				numTurns++;
			
			} else if((positionFront.y != height) && (positionFront.y != -1) && 
					(solvedMaze[positionFront.y][positionFront.x] != 1)) {
				cdir = cdir % 4;
				
				
			} else if((positionRight.y != height) && (positionRight.y != -1) && 
					(solvedMaze[positionRight.y][positionRight.x] != 1)) {
				cdir = (cdir+1) % 4;
				numTurns++;
						
			} else {
				cdir = (cdir+2) % 4;
				numTurns += 2;
				solvedMaze[pos.y][pos.x] += 2;
				
			}
			
			if(solvedMaze[pos.y + dir[cdir].y][pos.x + dir[cdir].x] < 2 && solvedMaze[pos.y][pos.x] == 2)
				solvedMaze[pos.y][pos.x] -= 2;
			
			solvedMaze[pos.y][pos.x] += 2;
			pos.x += dir[cdir].x;
			pos.y += dir[cdir].y;
			
			try {barrier.await(); } catch (BrokenBarrierException e) {};
			
			routeLength++;
		}
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
		
		DepthFirst hi = new DepthFirst(7);
		LeftHandFollower solver = new LeftHandFollower(hi.generate());
		solver.solveMaze(false);
		System.out.println(solver.toString());
				
	}

}
