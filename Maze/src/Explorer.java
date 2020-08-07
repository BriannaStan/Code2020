import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class Explorer {
	protected MazeCanvas mc;
	protected Maze m;
	protected Color fwdPathColor=Color.yellow;
	protected Color bktPathColor=Color.cyan;
	public Explorer(MazeCanvas mc, Maze m, Color fwdPathColor, Color bktPathColor)
	{
		this.mc=mc;
		this.m=m;
		this.fwdPathColor=fwdPathColor;
		this.bktPathColor=bktPathColor;
	}
	protected boolean onEnterCell(Cell cell, Side side)
	{
		mc.drawPath(cell.getRow(),cell.getCol(),side,fwdPathColor);
	    mc.drawCenter(cell.getRow(), cell.getCol(), fwdPathColor);
	    return false;
	}
	protected ArrayList<Side> onGetNextSteps(Cell cell)
	{
		return new ArrayList<Side>();
	}
	protected void onStepBack(boolean done, Cell cell, Side side)
	{
		if(!done)
		{
		if(bktPathColor!=null)
		mc.drawPath(cell.getRow(), cell.getCol(), side, bktPathColor);
		else
			mc.erasePath(cell.getRow(), cell.getCol(), side);
		}
	}
	protected void onStepForward(Cell cell, Side side)
	{
		mc.drawPath(cell.getRow(), cell.getCol(), side, fwdPathColor);
	}
	protected void onExitCell(boolean done, Cell cell, Side side)
	{
		if(!done)
		{
		if(bktPathColor!=null)
		{
		mc.drawPath(cell.getRow(),cell.getCol(),side, bktPathColor);
		mc.drawCenter(cell.getRow(),cell.getCol(),bktPathColor);
		}
		else
		{
			mc.erasePath(cell.getRow(),cell.getCol(),side);
			mc.eraseCenter(cell.getRow(),cell.getCol());
		}
		}
	}
	
	protected ArrayList<Side> shuffle(ArrayList<Side> side)
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
	protected Side getOpposite(Side side)
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
		boolean done=onEnterCell(cell,side);
		ArrayList <Side> listOfWalls=onGetNextSteps(cell);
		for(Side s:listOfWalls)
		{
			if(!done)
			{
			    Cell neighbor= m.getNeighbor(cell,s);
			    if(!neighbor.getVisited())
			    {
				onStepForward(cell,s);
				done=this.run(neighbor, getOpposite(s));
				onStepBack(done,cell,s);
			    }
			}
				
		}
		onExitCell(done,cell,side);
		return done;
			
	}
	public boolean run()
	{
		return run(m.getEntryCell(), Side.Center);
	}
	
	
}
