import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import gameBoards.SudokuGUI;

public class SudokuBoard {
	private SudokuGUI gui;
	private int[][] board;

	public SudokuBoard() {
		this.gui = new SudokuGUI();
		this.board = new int[9][9];
	}

	public void loadPuzzle(String p) {
		Scanner scnr;
		try {
			scnr = new Scanner(new File(p));
			int row = 0;
			while (scnr.hasNextLine()) {
				String text = scnr.nextLine();
				if (!text.isEmpty()) {
					int col = 0;
					for (int i = 0; i < text.length(); i++) {
						if (text.charAt(i) >= 48 && text.charAt(i) <= 57)
							board[row][col] = text.charAt(i) - 48;
						else
							board[row][col]=0;
						if (text.charAt(i) != ' ')
							col++;

					}
					row++;
				}
//			gui.draw(board);

			}
			scnr.close();
			gui.draw(board, new Color(232, 232, 232));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean solvePuzzle() {
		gui.pause();
		boolean f = solvePuzzle(0, 0);
		gui.draw(board);
		return f;
	}

	public boolean solvePuzzle(int row, int col) {
		int nextrow = (row + ((col + 1) / 9));
		int nextcol = (col + 1) % 9;
		System.out.println(row+","+col+"->"+nextrow+","+ nextcol+" b:"+board[row][col]);		
		if(board[row][col]!=0)
		{
			if((!(nextrow>=0 && nextrow<=8 && nextcol>=0 && nextcol<=8)) 
					|| solvePuzzle(nextrow,nextcol)==true)
			{
				return true;
			}
		}
		for(int i=1;i<=9;i++)
		{
			if(isValid(i,row,col))
			{
				board[row][col]=i;
				System.out.println(row+","+col+":"+i);
				if((!(nextrow>=0 && nextrow<=8 && nextcol>=0 && nextcol<=8)) 
						|| solvePuzzle(nextrow,nextcol)==true)
				{
					return true;
				}
				board[row][col]=0;
			}
		}
		return false;
	}

	public void drawPuzzle() {
		gui.draw(board);
	}
	public boolean isValid(int number,int row,int col)
	{
		boolean valid=true;
		for(int r=0;r<=8 && valid;r++)
		{
			valid=(number!=board[r][col]);
		}
		for(int c=0;c<=8 && valid;c++)
		{
			valid=(number!=board[row][c]);
		}
		for(int r=row/3*3;r<=row/3*3+2 && valid;r++)
		{
			for(int c=col/3*3;c<=col/3*3+2 && valid;c++)
			{
				valid=(number!=board[r][c]);
			}
		}
		return valid;
	}
}
