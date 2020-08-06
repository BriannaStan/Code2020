import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class Generator {
	private MazeCanvas mc;
	public static final Color generatePathColor=Color.PINK;
	private Maze m;
	public Generator(MazeCanvas mc, Maze m)
	{
		this.mc=mc;
		this.m=m;
	}
	public ArrayList<Side> shuffle(ArrayList<Side> side)
	{
		ArrayList<Side> arr = new ArrayList<Side>();
		arr.addAll(side);
		Collections.shuffle(arr);
		return arr;
//		Random rand = new Random();
//		for(int i=0;i<side.size();i++)
//		{
//			int index=rand.nextInt(side.size());
//			Side aux=side.get(i);
//			side.set(index, side.get(index));
//			side.set(index,aux);
//		}
//		return side;
	}
	public Side getOpposite(Side side)
	{
		if(side==Side.Right)
			return Side.Left;
		if(side==Side.Left)
			return Side.Right;
		if(side==Side.Top)
			return Side.Bottom;
		if(side==Side.Center)
			return Side.Center;
		return Side.Top;
		
	}
	public boolean run(Cell cell, Side side)
	{
		cell.setVisited(true);
		mc.step(10000);
		mc.drawPath(cell.getRow(), cell.getCol(), side, generatePathColor);
		mc.drawCenter(cell.getRow(), cell.getCol(), generatePathColor);
		cell.removeWall(side);
		ArrayList <Side> listOfWalls=shuffle(cell.getWalls());
		for(Side s:listOfWalls)
		{
			Cell neighbor= m.getNeighbor(cell,s);
			if(!neighbor.getVisited())
			{
				mc.drawPath(cell.getRow(), cell.getCol(), s, generatePathColor);
				cell.removeWall(s);
				this.run(neighbor, getOpposite(s));
				mc.erasePath(cell.getRow(),cell.getCol(),s);
				
			}
				
		}
		mc.erasePath(cell.getRow(),cell.getCol(),side);
		mc.eraseCenter(cell.getRow(),cell.getCol());
		return false;
			
	}
	public boolean run()
	{
		return run(m.getEntryCell(), Side.Center);
	}
}
