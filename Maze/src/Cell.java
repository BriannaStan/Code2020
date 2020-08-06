import java.util.ArrayList;
import java.util.Collections;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class Cell {
	private MazeCanvas mc;
	private int row;
	private int col;
	private boolean visited=false;
	private ArrayList <Side> listOfWalls;
	
	public Cell(MazeCanvas mc,int row, int col)
	{
		this.mc=mc;
		this.row=row;
		this.col=col;
		listOfWalls= new ArrayList<Side>();
		listOfWalls.add(Side.Top);
		listOfWalls.add(Side.Bottom);
		listOfWalls.add(Side.Left);
		listOfWalls.add(Side.Right);
		mc.drawCell(row, col);
	}
	
	public int getRow()
	{
		return this.row;
	}
	public int getCol()
	{
		return this.col;
	}
	public ArrayList<Side> getWalls()
	{
		ArrayList<Side> clona = new ArrayList<Side>();
		clona.addAll(listOfWalls);
		return clona;
    }
	public void removeWall(Side side)
	{
		if(listOfWalls.remove(side))
			mc.eraseWall(row, col, side);
	}
	public void setVisited(boolean visited)
	{
		this.visited=visited;
	}
	public boolean getVisited()
	{
		return this.visited;
	}
	public ArrayList<Side> getPaths()
	{
		ArrayList<Side> pat = new ArrayList<Side> ();
		Collections.addAll(pat, new Side[] {Side.Top, Side.Bottom, Side.Left, Side.Right});
		pat.removeAll(listOfWalls);
		return pat;
		
	}
}
