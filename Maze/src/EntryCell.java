import java.awt.Color;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class EntryCell extends EdgeCell{
	private static final Color entryShadeColor=Color.BLUE;
	public EntryCell(MazeCanvas mazeCanvas, int row, int col)
	{
		super(mazeCanvas, row,col);
		mazeCanvas.drawShade(row, col,entryShadeColor);
	}
	
}
