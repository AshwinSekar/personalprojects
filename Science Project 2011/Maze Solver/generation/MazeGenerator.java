package generation;
import java.util.Random;

public abstract class MazeGenerator {
	
	protected byte[][] maze;
	protected int height;
	protected int width;
	protected Random generator;
	
	public MazeGenerator(int width, int height) {
		this.width = width;
		this.height = height;
		maze = new byte[height][width];
		generator = new Random();
	}
	
	// Some maze generation algorithms require all the walls to be filled for initialization
	public void fillWalls() {
		for(int i = 0; i < this.height; i ++) {
			for(int j = 0; j < this.width; j ++) {
				maze[i][j] = (byte)(((i % 2 == 0) || (j % 2 == 0))?1:0);
			}
		}
	}
	
	public abstract byte[][] generate();
	
	@Override
	public String toString() {		
		String toString = "";
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				toString += (maze[i][j] == 0)?(" "):("O");
			}
			toString += "\n";
		}
		return toString;
	}
	
}

