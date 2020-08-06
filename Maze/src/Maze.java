import java.awt.Color;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class Maze{
	public MazeCanvas mc;
	private Cell[][] gridOfCells;
	private Cell entryCell, exitCell;
	public Maze(MazeCanvas mazeCanvas)
	{
		 this.mc=mazeCanvas;
		 this.gridOfCells=new  Cell[mc.getRows()][mc.getCols()];
	}
	public void initialize()
	{
		int count=(mc.getRows()*mc.getCols())/20;
//		double r=Math.random();
		int nPerim=2*mc.getRows()+2*mc.getCols()-4;
	    //int iEntry=(int)(Math.random()*(nPerim+1));
		int iEntry = (int)(Math.random() * nPerim);
		//int iExit=(int)(Math.random()*(nPerim+1));
		int iExit = (iEntry + 1 + (int)(Math.random() * (nPerim - 1))) % nPerim;
		System.out.printf("iEntry=%d, iExit=%d count=%d, cols=%d rows=%d", iEntry, iExit, count,mc.getCols(), mc.getRows());
		int edgeCount=0;
		for(int i=0;i<mc.getRows();i++)
		{
			for(int j=0;j<mc.getCols();j++)
			{
				if(i==0 || i==mc.getRows()-1 || j==0 || j==mc.getCols()-1)
				{
				
					if(edgeCount==iEntry)
					{
						System.out.println("entry:"+i+","+j);
						gridOfCells[i][j]=new EntryCell(mc,i,j);
						entryCell=gridOfCells[i][j];
					}
					else if(edgeCount==iExit)
					{
						System.out.println("exit:"+i+","+j);
						gridOfCells[i][j]=new ExitCell(mc,i,j);
						exitCell=gridOfCells[i][j];
					}
					else
						gridOfCells[i][j]=new EdgeCell(mc,i,j);
					edgeCount++;
				       
				}
				else if(count>0 && Math.random()<0.05)
				{
					gridOfCells[i][j]= new BlockCell(mc,i,j);
					count=count-1;
				}
				else
					gridOfCells[i][j]= new Cell(mc,i,j);
			}
		}
	}
	public Cell getCell(int row, int col)
	{
		return this.gridOfCells[row][col];
	}
	public void genSnake()
	{
		Color lightYellow=new Color(255,255,161);
		int nrows=mc.getRows();
		int ncols=mc.getCols();
		
		for(int i=0;i<nrows;i++)
		{
			for(int j=0;j<ncols;j++)
			{
				mc.drawCell(i, j);
				mc.drawShade(i,j,lightYellow);
				if(i==0)
				{
					if(j%2==0)
					{
						mc.drawPath(i, j, Side.Bottom,Color.RED);
						mc.drawCenter(i, j, Color.RED.darker());
						mc.drawPath(i, j, Side.Left,Color.RED);
						mc.eraseWall(i, j, Side.Left);
						mc.eraseWall(i, j, Side.Bottom);
					}
					else
					{
						mc.drawPath(i, j, Side.Bottom,Color.RED);
						mc.drawCenter(i, j, Color.RED.darker());
						mc.drawPath(i, j, Side.Right,Color.RED);
						mc.eraseWall(i, j, Side.Right);
						mc.eraseWall(i, j, Side.Bottom);
					}
				}
				else
					if(i==(nrows-1))
					{
						if(j%2==1)
						{
							mc.drawPath(i, j, Side.Top,Color.RED);
							mc.drawCenter(i, j, Color.RED.darker());
							mc.drawPath(i, j, Side.Left,Color.RED);
							mc.eraseWall(i, j, Side.Left);
							mc.eraseWall(i, j, Side.Top);
						}
						else
						{
							mc.drawPath(i, j, Side.Top,Color.RED);
							mc.drawCenter(i, j, Color.RED.darker());
							mc.drawPath(i, j, Side.Right,Color.RED);
							mc.eraseWall(i, j, Side.Right);
							mc.eraseWall(i, j, Side.Top);
						}
					}
					else
					{
						mc.eraseWall(i, j, Side.Top);
						mc.eraseWall(i, j, Side.Bottom);
						mc.drawPath(i, j, Side.Top,Color.RED);
						mc.drawPath(i, j, Side.Bottom, Color.RED);
						mc.drawCenter(i, j, Color.RED);
					}
			} 
		}
	}
	public Cell getEntryCell()
	{
		return entryCell;
	}
	public Cell getExitCell()
	{
		return exitCell;
	}
	public Cell getNeighbor(Cell cell, Side side)
	{
		int row=cell.getRow();
		int col = cell.getCol();
		switch(side)
		{
		case Top:
			if(row==0)
				return null;
			else
			    return gridOfCells[row-1][col];
		case Bottom:
			if(row==mc.getRows()-1)
				return null;
			else
			    return gridOfCells[row+1][col];
		case Left:
			if(col==0)
				return null;
			else
			    return gridOfCells[row][col-1];
		default:
			if(col==mc.getCols()-1)
				return null;
			else
				return gridOfCells[row][col+1];
			
		}
	}
}