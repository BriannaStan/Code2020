import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class Program {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MazeCanvas mc = new MazeCanvas(20,20,30);
		Maze maze=new Maze(mc);
		maze.mc.open();
		//maze.genSnake();
		maze.initialize();
		Generator generator = new Generator(mc,maze);
		generator.run();
		Solver solver= new Solver(mc,maze);
		solver.run();
		maze.mc.pause();
		maze.mc.close();
	}

}
