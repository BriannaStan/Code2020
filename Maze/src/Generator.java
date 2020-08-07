import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class Generator extends Explorer{
//	private MazeCanvas mc;
	public static final Color generatePathColor=Color.PINK;
//	private Maze m;
	public Generator(MazeCanvas mc, Maze m)
	{
		super(mc,m,generatePathColor,null);
	}
	@Override
	protected boolean onEnterCell(Cell cell, Side side)
	{
//		mc.drawPath(cell.getRow(), cell.getCol(), side, generatePathColor);
//		mc.drawCenter(cell.getRow(), cell.getCol(), generatePathColor);
		cell.removeWall(side);
	    return super.onEnterCell(cell, side);
	}
	@Override
	protected ArrayList<Side> onGetNextSteps(Cell cell)
	{
		return shuffle(cell.getWalls());
	}
//	protected void onStepBack(boolean done, Cell cell, Side side)
//	{
//			mc.erasePath(cell.getRow(),cell.getCol(),side);
//	}
	@Override
	protected void onStepForward(Cell cell, Side side)
	{
		//mc.drawPath(cell.getRow(), cell.getCol(), side, generatePathColor);
		  cell.removeWall(side);
		super.onStepForward(cell, side);
	  
	}
//	protected void onExitCell(boolean done, Cell cell, Side side)
//	{
//		mc.erasePath(cell.getRow(),cell.getCol(),side);
//		mc.eraseCenter(cell.getRow(),cell.getCol());
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
//	public boolean run(Cell cell, Side side)
//	{
//		cell.setVisited(true);
//		mc.step(10000);
//		boolean done=onEnterCell(cell,side);
//		ArrayList <Side> listOfWalls=onGetNextSteps(cell);
//		for(Side s:listOfWalls)
//		{
//			if(!done)
//			{
//			    Cell neighbor= m.getNeighbor(cell,s);
//			    if(!neighbor.getVisited())
//			    {
//				onStepForward(cell,s);
//				done=this.run(neighbor, getOpposite(s));
//				onStepBack(done,cell,s);
//			    }
//			}
//				
//		}
//		onExitCell(done,cell,side);
//		return done;
//			
//	}
//	public boolean run()
//	{
//		return run(m.getEntryCell(), Side.Center);
//	}

}
