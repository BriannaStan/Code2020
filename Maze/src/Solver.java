import graphics.MazeCanvas.Side;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import graphics.MazeCanvas;
public class Solver {
	public MazeCanvas mc;
	public static final Color fwdPathColor=Color.yellow;
	public static final Color bktPathColor=Color.cyan;
	private Maze m;
	public Solver(MazeCanvas mc, Maze m)
	{
		this.mc=mc;
		this.m=m;
	}
//	public boolean run(Cell cell, Side side)
//	{
//		return false;
//	}
	public boolean run()
	{
		for(int i=0;i<this.mc.getRows();i++)
		{
			for(int j=0;j<this.mc.getCols();j++)
			{
				this.m.getCell(i,j).setVisited(false);
			}
		}
		return run(m.getEntryCell(),Side.Center);
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
	public boolean run(Cell cell, Side fromSide)
	{
		cell.setVisited(true);
	    mc.drawPath(cell.getRow(),cell.getCol(),fromSide,fwdPathColor);
	    mc.drawCenter(cell.getRow(), cell.getCol(), fwdPathColor);
	    if(cell==m.getExitCell())
	    {
	    	return true;
	    }
	    ArrayList <Side> listOfPaths=shuffle(cell.getPaths());
		for(Side s:listOfPaths)
		{
			Cell neighbor= m.getNeighbor(cell,s);
			if(!neighbor.getVisited())
			{
				mc.drawPath(cell.getRow(), cell.getCol(), s, fwdPathColor);
				if(this.run(neighbor, getOpposite(s)))
					return true;
				mc.drawPath(cell.getRow(), cell.getCol(), s, bktPathColor);
			}
		}
		mc.drawPath(cell.getRow(),cell.getCol(),fromSide, bktPathColor);
		mc.drawCenter(cell.getRow(),cell.getCol(),bktPathColor);
		return false;
	}
}
