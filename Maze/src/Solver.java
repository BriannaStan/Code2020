import graphics.MazeCanvas.Side;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import graphics.MazeCanvas;
public class Solver extends Explorer{
//	public MazeCanvas mc;
	public static final Color fwdPathColor=Color.yellow;
	public static final Color bktPathColor=Color.cyan;
//	private Maze m;
	public Solver(MazeCanvas mc, Maze m)
	{
		super(mc,m,fwdPathColor,bktPathColor);
	}
//	public boolean run(Cell cell, Side side)
//	{
//		return false;
//	}
	@Override
	protected boolean onEnterCell(Cell cell, Side side)
	{
		//mc.drawPath(cell.getRow(),cell.getCol(),side,fwdPathColor);
	    //mc.drawCenter(cell.getRow(), cell.getCol(), fwdPathColor);
		super.onEnterCell(cell, side);
	    if(m.getExitCell()==cell)
	    {
	    	return true;
	    }
	    else
	    	return false;
	}
	@Override
	protected ArrayList<Side> onGetNextSteps(Cell cell)
	{
		return shuffle(cell.getPaths());
	}
//	protected void onStepBack(boolean done, Cell cell, Side side)
//	{
//		if(!done)
//		mc.drawPath(cell.getRow(), cell.getCol(), side, bktPathColor);
//	}
//	protected void onStepForward(Cell cell, Side side)
//	{
//		mc.drawPath(cell.getRow(), cell.getCol(), side, fwdPathColor);
//	}
//	protected void onExitCell(boolean done, Cell cell, Side side)
//	{
//		if(!done)
//		{
//		mc.drawPath(cell.getRow(),cell.getCol(),side, bktPathColor);
//		mc.drawCenter(cell.getRow(),cell.getCol(),bktPathColor);
//		}
//	}
	
//	public boolean run()
//	{
//		for(int i=0;i<this.mc.getRows();i++)
//		{
//			for(int j=0;j<this.mc.getCols();j++)
//			{
//				this.m.getCell(i,j).setVisited(false);
//			}
//		}
//		return run(m.getEntryCell(),Side.Center);
//	}
//	public ArrayList<Side> shuffle(ArrayList<Side> side)
//	{
//		ArrayList<Side> arr = new ArrayList<Side>();
//		arr.addAll(side);
//		Collections.shuffle(arr);
//		return arr;
////		Random rand = new Random();
////		for(int i=0;i<side.size();i++)
////		{
////			int index=rand.nextInt(side.size());
////			Side aux=side.get(i);
////			side.set(index, side.get(index));
////			side.set(index,aux);
////		}
////		return side;
//	}
//	public Side getOpposite(Side side)
//	{
//		if(side==Side.Right)
//			return Side.Left;
//		if(side==Side.Left)
//			return Side.Right;
//		if(side==Side.Top)
//			return Side.Bottom;
//		if(side==Side.Center)
//			return Side.Center;
//		return Side.Top;
//		
//	}
//	public boolean run(Cell cell, Side fromSide)
//	{
//		mc.step(10);
//		cell.setVisited(true);
//		boolean done=onEnterCell(cell,fromSide);
//	    ArrayList <Side> listOfPaths=onGetNextSteps(cell);
//		for(Side s:listOfPaths)
//		{
//			if(!done)
//			{
//			    Cell neighbor= m.getNeighbor(cell,s);
//			    if(!neighbor.getVisited())
//			    {
//				    onStepForward(cell,s);
//					done=this.run(neighbor, getOpposite(s));
//				    onStepBack(done,cell,s);
//			    }
//			}
//		}
//		onExitCell(done,cell,fromSide);
//		return done;
//	}
	
}
