import java.awt.Color;
import java.util.ArrayList;

import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;

public class EdgeCell extends ShadedCell{
	private ArrayList <Side> listOfEdges=new ArrayList<Side>();
	public EdgeCell(MazeCanvas mazeCanvas, int row, int col)
	{
		super(mazeCanvas, row,col,Color.RED.brighter());
		if(row==0)
		{
			listOfEdges.add(Side.Top);
		}
		else
		{
			if(row==mazeCanvas.getRows()-1)
			{
				listOfEdges.add(Side.Bottom);
			}
		}
		
		if(col==0)
		{
			listOfEdges.add(Side.Left);
		}
		else
		{
			if(col==mazeCanvas.getCols()-1)
			{
				listOfEdges.add(Side.Right);
			}
		}
	}
	@Override
	public ArrayList<Side> getWalls()
	{
		ArrayList<Side> s=super.getWalls();
		for(int i=0;i<listOfEdges.size();i++)
		{
			s.remove(listOfEdges.get(i));
		}
		return s;
    }
	@Override
	public ArrayList<Side> getPaths()
	{
		ArrayList <Side> edges=super.getPaths();
		edges.removeAll(listOfEdges);
		return edges;
	}
}
