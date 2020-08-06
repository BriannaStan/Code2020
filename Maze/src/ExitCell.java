import java.awt.Color;
import graphics.MazeCanvas;
import graphics.MazeCanvas.Side;
public class ExitCell extends EdgeCell{
	private static final Color exitShadeColor=Color.GREEN;
	public ExitCell(MazeCanvas mazeCanvas, int row, int col)
	{
		super(mazeCanvas, row, col);
		mazeCanvas.drawShade(row, col,exitShadeColor);
	}
	
}
