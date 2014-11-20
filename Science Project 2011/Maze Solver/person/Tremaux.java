package person;

import generation.DepthFirst;

import java.awt.Point;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

import main.MazeSolver;

public class Tremaux extends MazeSolver implements Runnable{

	public Tremaux(byte[][] toBeSolved) {
		super(toBeSolved);
	}
	
	@Override
	public void solveMaze(boolean pause) throws InterruptedException {
		totalTime = 0;
		startTime = System.nanoTime();
		Point pos = new Point(1,entry); // Position of the person inside maze;
		Point dir[] = {new Point(1,0), new Point(0,1), new Point(-1,0), new Point(0,-1)}; // Direction Vectors;
		solvedMaze[entry][0] = 2;
		int cdir = 0,cin = 0;
		int[] spaces = new int[3];
		byte front,left,right,back;
		Random gen = new Random();
		int choose = 0;
		try {barrier.await(); } catch (BrokenBarrierException e) {};
		
		while((pos.x != width-1) || (pos.y != exit)) {
			
			front = solvedMaze[pos.y + dir[(cdir) % 4].y][pos.x + dir[(cdir) % 4].x];
			right = solvedMaze[pos.y + dir[(cdir+1) % 4].y][pos.x + dir[(cdir+1) % 4].x];
			left = solvedMaze[pos.y + dir[(cdir+3) % 4].y][pos.x + dir[(cdir+3) % 4].x];
			back = solvedMaze[pos.y-dir[cdir].y][pos.x-dir[cdir].x];
			spaces[cin] = (left != 1)?cdir+3+cin++-cin+1:(left = 127)*-5;
			spaces[cin] = (right != 1)?cdir+1+cin++-cin+1:(right = 127)*-5;
			spaces[cin] = (front != 1)?cdir+cin++-cin+1:(front = 127)*-5;
			
			if(cin != 0)
				choose = gen.nextInt(cin);
			
			if(cin == 1 || (solvedMaze[pos.y][pos.x] == 0 && cin != 0)) {
				numTurns += (cdir == spaces[choose])?0:1;
				cdir = spaces[choose] % 4;	
				
			} else if(back == 2 || cin == 0) {
				numTurns += 2;
				cdir = (cdir + 2) % 4;
				solvedMaze[pos.y][pos.x] += 2;
				
			} else {
				if(Math.min(Math.min(front,right),left) == right) {
					numTurns++;
					cdir = (cdir + 1) % 4;
				} else if(Math.min(front,left) == left) {
					numTurns++;
					cdir = (cdir + 3) % 4;
				}
				
			}
			if(solvedMaze[pos.y + dir[cdir].y][pos.x + dir[cdir].x] < 2 && solvedMaze[pos.y][pos.x] == 2)
				solvedMaze[pos.y][pos.x] -= 2;
			
			solvedMaze[pos.y][pos.x] += 2;
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
		DepthFirst his = new DepthFirst(15);
		byte hi[][] = his.generate();
		Tremaux solver = new Tremaux(hi);
		solver.solveMaze(false);
		System.out.println(solver);		
	}

}
