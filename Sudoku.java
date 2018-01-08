
import java.util.*;

/**
 * Sudoku class, which will have a Sudoku board with a 9x9 grid and each cell
 * in the grid is filled with a single number(from 1-9). Moreover, each
 * column,row,and 3x3 subgrid must contain exactly one single number with no
 * repeats.
 *
 */
public class Sudoku {

	private final int SIZE = 9;
	private int[][] board;

	/**
	 * Constructor, initialize int[][] board with size of '9x9'.
	 */
	public Sudoku() {
		board = new int[SIZE][SIZE];
	}

	/**
	 * Call fillin() function to fill the board.
	 */
	public void fillBoard() {
		fillIn(0, 0);
	}

	/**
	 * printing the Sudoku board.
	 */
	public void printBoard() {

		System.out.println(" -----------------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("|");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(" " + board[i][j]);
				if ((j + 1) % 3 == 0 && j != board.length - 1) {
					System.out.print(" |");
				}
				if (j == board.length - 1) {
					System.out.print(" |");
				}
			}
			System.out.println();
			if ((i + 1) % 3 == 0 && i != board.length - 1) {
				System.out.println(" -----------------------");
			}
		}
		System.out.println(" -----------------------");
	}

	/**
	 * Generating a random number.
	 * 
	 * @return a random number.
	 */
	public int number_generator() {
		Random rand = new Random();
		return rand.nextInt(8);
	}

	/**
	 * Reset order of numbers in a array, and number on the board will be taken
	 * from the array returned by this function.
	 * 
	 * @return the array with different order.
	 */
	public int[] resetNumber() {

		int[] numberTank = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int tmp = 0;
		int randNum = 0;

		for (int i = 8; i > 0; i--) {

			// getting a random number from 'number_generator()'
			// as an index number.
			randNum = number_generator();

			tmp = numberTank[randNum];
			numberTank[randNum] = numberTank[i];
			numberTank[i] = tmp;
		}
		return numberTank;
	}

	/**
	 * Filling each cell vertically(column by column) and recursively.
	 *
	 * @param row
	 * @param col
	 * @return true if fill in all cells, false otherwise.
	 */
	public boolean fillIn(int row, int col) {
		int new_row = row;
		int new_col = col;

		// generate a new array with different order.
		int[] testNum = resetNumber();

		for (int i = 0; i < testNum.length; i++) {
			if (safeToPlace(row, col, testNum[i])) {
				board[row][col] = testNum[i];
				if (row == board.length - 1) {
					if (col == board.length - 1)
						return true; // base case
					else {
						new_row = 0;
						new_col = col + 1;
					}
				} else {
					new_row = row + 1;
				}
				if (fillIn(new_row, new_col))
					return true;
			}
		}
		board[row][col] = -1;
		return false;
	}

	/**
	 * Checking row, column,and sub-grid to see if it is a safe place to put the
	 * number.
	 * 
	 * @param row
	 * @param col
	 * @param target
	 * @return true if it is safe, false otherwise.
	 */
	public boolean safeToPlace(int row, int col, int target) {

		// check subgrid(3x3)
		int row_start = row - (row % 3);
		int row_end = row_start + 3;
		int col_start = col - (col % 3);
		int col_end = col_start + 3;

		for (int i = row_start; i < row_end; i++) {
			for (int j = col_start; j < col_end; j++) {
				if (board[i][j] == target) {
					return false;
				}
			}
		}

		// check row
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == target) {
				return false;
			}
		}

		// check column
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == target) {
				return false;
			}

		}

		return true;
	}

	/**
	 * main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		sudoku.fillBoard();
		sudoku.printBoard();
	}

}

