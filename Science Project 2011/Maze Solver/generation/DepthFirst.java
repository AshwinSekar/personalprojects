package generation;
import java.awt.Point;

public class DepthFirst extends MazeGenerator {
	
	boolean visited[][];
		
	public DepthFirst(int w) {
		super(2*w+1,2*w+1);
		this.fillWalls();
		visited = new boolean[this.height+2][this.width+2];
				
		for(int i = 0; i < this.height+2; i++) {
			for(int j = 0; j < this.width+2; j++) {
				visited[i][j] = (j == 0) || (i == 0) || (j == this.width+1) || (i == this.height+1);
				
			}
		}
	}
	
	@Override
	public byte[][] generate() {
		visited[this.height-1][2] = true;
		generate(new Point(1,this.height-2));
		maze[2*generator.nextInt(this.height/2)+1][0] = 0;
		maze[2*generator.nextInt(this.height/2)+1][this.width-1] = 0;
		return maze;
	}
	
	private void generate(Point pos) {
		boolean done = visited[pos.y+1][pos.x+3] && 
		visited[pos.y+1][pos.x-1] && visited[pos.y-1][pos.x+1] && visited[pos.y+3][pos.x+1];
		double choose;
		
		while(!done) {
			choose = generator.nextDouble();
			if(choose < .25 && !visited[pos.y+1][pos.x+3]) {				
				visited[pos.y+1][pos.x+3] = true;
				maze[pos.y][pos.x+1] = 0;
				generate(new Point(pos.x+2,pos.y));
				
			} else if (choose < .5 && !visited[pos.y+1][pos.x-1]){
				visited[pos.y+1][pos.x-1] = true;
				maze[pos.y][pos.x-1] = 0;
				generate(new Point(pos.x-2,pos.y));
				
			} else if (choose < .75 && !visited[pos.y+3][pos.x+1]){
				visited[pos.y+3][pos.x+1] = true;
				maze[pos.y+1][pos.x] = 0;
				generate(new Point(pos.x,pos.y+2));
				
			} else if (!visited[pos.y-1][pos.x+1]){
				visited[pos.y-1][pos.x+1] = true;
				maze[pos.y-1][pos.x] = 0;
				generate(new Point(pos.x,pos.y-2));
			}
			
			done = visited[pos.y+1][pos.x+3] && 
			visited[pos.y+1][pos.x-1] && visited[pos.y-1][pos.x+1] && visited[pos.y+3][pos.x+1];
		}
		
	}
	
	public static void main(String args[]) {
		DepthFirst hi = new DepthFirst(15);
		hi.generate();
		System.out.println(hi.toString());
	}

}
