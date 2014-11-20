package person;

import main.MazeSolver;
import generation.DepthFirst;
import java.awt.Point;
import java.util.concurrent.BrokenBarrierException;

public class Pledge extends MazeSolver implements Runnable {

	public Pledge(byte[][] toBeSolved) {
		super(toBeSolved);
		
	}

	@Override
	public void solveMaze(boolean pause) throws InterruptedException {
		
		totalTime = 0;
		startTime = System.nanoTime();
		Point pos = new Point(1,entry); // Position of the person inside maze 
		Point dir[] = {new Point(1,0), new Point(0,1), new Point(-1,0), new Point(0,-1)}; // Direction Vectors;
		int cdir = 0;
		solvedMaze[pos.y][0] = 1;
		solvedMaze[pos.y][pos.x] = 2;
		try {barrier.await(); } catch (BrokenBarrierException e) {};
		
		Point positionLeft, positionRight, positionFront, positionInit;
		int turns = 0;
		boolean right = true;
		
		while((pos.x != width-1) || (pos.y != exit)) {
			
			positionInit = new Point(pos.x + dir[0].x, pos.y + dir[0].y);
			
			if((turns == 0) && (solvedMaze[positionInit.y][positionInit.x] != 1)) {
				cdir = 0;
				right = false;
			} else if ((turns == 0) && (solvedMaze[positionInit.y][positionInit.x] == 1)){
					right = true;
					cdir = 3;
					turns--;
					numTurns++;
			}
			positionLeft = new Point(pos.x + dir[(cdir+3) % 4].x, pos.y + dir[(cdir+3) % 4].y);
			positionRight = new Point(pos.x + dir[(cdir+1) % 4].x, pos.y + dir[(cdir+1) % 4].y);
			positionFront = new Point(pos.x + dir[(cdir) % 4].x, pos.y + dir[(cdir) % 4].y);
			
			if(right) {
				if((positionRight.y != height) && (positionRight.y != -1) && 
						(solvedMaze[positionRight.y][positionRight.x] != 1)) {
					cdir = (cdir+1) % 4;
					turns++;
					numTurns++;
					
				} else if((positionFront.y != height) && (positionFront.y != -1) && 
						(solvedMaze[positionFront.y][positionFront.x] != 1)) {
					cdir = cdir % 4;
										
				} else if((positionLeft.y != height) && (positionLeft.y != -1) && 
						(solvedMaze[positionLeft.y][positionLeft.x] != 1)) {
					cdir = (cdir+3) % 4;
					turns--;
					numTurns++;
					
				} else {
					cdir = (cdir+2) % 4;
					turns-=2;
					numTurns += 2;
					
				}
			} 
			
			solvedMaze[pos.y][pos.x] = 2;
			pos.x += dir[cdir].x;
			pos.y += dir[cdir].y;

			try {barrier.await(); } catch (BrokenBarrierException e) {};
			
			routeLength++;
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
		
		DepthFirst his = new DepthFirst(70);
		byte hi[][] = his.generate();
		Pledge solver = new Pledge(hi);
		RightHandFollower rightSolver = new RightHandFollower(hi);
		LeftHandFollower leftSolver = new LeftHandFollower(hi);
		solver.solveMaze(false);
		rightSolver.solveMaze(false);
		leftSolver.solveMaze(false);
		System.out.println(solver.toString());
		System.out.println(rightSolver);
		System.out.println(leftSolver);
		
	}

}
