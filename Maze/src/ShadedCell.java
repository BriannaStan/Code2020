import java.awt.Color;
import graphics.MazeCanvas;
public class ShadedCell  extends Cell {
	public ShadedCell(MazeCanvas mazeCanvas, int row, int col, Color shadeColor)
	{
		super(mazeCanvas, row,col);
		mazeCanvas.drawShade(row,col,shadeColor);
	}
}

