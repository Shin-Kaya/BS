import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/* 
 * Author: Shin Kaya (March 2018) 
 * the author used the information from the following links to write this code:
	 * udemy course: www.udemy.com/java-swing-complete/
	 * https://stackoverflow.com/questions/6877373/write-a-2d-array-on-a-jframe-java
	 * https://www.youtube.com/watch?v=Db3cC5iPrOM
	 * https://www.youtube.com/watch?v=NWhkCZZumAE
	 * https://coderanch.com/t/557220/java/array-battleship
 */

public class BGrid extends JPanel implements KeyListener {

	// individual instance variables ------------
	private BButton[][] button;
	private static GameMode gameMode = new GameMode(GameMode.GAME_SETUP);

	// class variables --------------------------
	static final int GRID_SIZE = 10;
	private static final long serialVersionUID = 1L;
	// private static GridLayout gridlayout;

	// gameMode values
	static final int GAME_SETUP = 0;
	static final int GAME_USER_TURN = 1;
	static final int GAME_COMPUTER_TURN = 2;

	// STATE values for individual Cells ------------------------------------
	public static final int STATE_EMPTY = 00; // cell is not a part of vessel
	public static final int STATE_VESSEL = 01; // cell consists of one cell vessel
	public static final int STATE_VESSEL_1 = 01; // cell consists of one cell vessel
	public static final int STATE_VESSEL_2 = 02; // cell is one of cells of vessel size 2
	public static final int STATE_VESSEL_3 = 03; // cell is one of cells of vessel size 3
	public static final int STATE_VESSEL_4 = 04; // cell is one of cells of vessel size 4
	public static final int STATE_EMPTY_HIT = 10; // fired to an empty cell (miss)
	public static final int STATE_VESSEL_1_HIT = 11; // fired and hit cell consisting of one cell vessel (sunk)
	public static final int STATE_VESSEL_2_HIT = 12; // fired and hit cell part of two cell vessel (wounded)
	public static final int STATE_VESSEL_3_HIT = 13; // fired and hit cell part of three cell vessel (wounded)
	public static final int STATE_VESSEL_4_HIT = 14; // fired and hit cell part of four cell vessel (wounded)

	public BGrid() {
		super(); // extending from patent class: JFrame
		this.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

		button = new BButton[GRID_SIZE][GRID_SIZE];
		// setGridlayout(new GridLayout(GRID_SIZE, GRID_SIZE));

		// creating individual array labels and adding to the JPanel
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				// create individual instances with row, col
				button[row][col] = new BButton(row, col, CellState.STATE_EMPTY, GameMode.GAME_SETUP);
				// set the image to the component
				button[row][col].setIcon(ImageLoader.getImageIcon(ImageLoader.NUM_IMAGE_EMPTY));
				// add it to the JPanel (which is extended by this class)
				this.add(button[row][col]);
			}
		}
	}

	public void compileVessels() {
		int[][] arr = new int[BGrid.GRID_SIZE][BGrid.GRID_SIZE];

		// transfer all CellState array to a temp array
		for (int i = 0; i < BGrid.GRID_SIZE; i++) {
			for (int j = 0; j < BGrid.GRID_SIZE; j++) {
				arr[i][j] = this.button[i][j].getCellStateNumber();
			}
		}

		for (int i = 0; i < BGrid.GRID_SIZE; i++) {
			for (int j = 0; j < BGrid.GRID_SIZE; j++) {
				if (arr[i][j] > CellState.STATE_EMPTY) {
					if (arr[i][j + 1] > CellState.STATE_EMPTY) {
						if (arr[i][j + 2] > CellState.STATE_EMPTY) {
							if (arr[i][j + 3] > CellState.STATE_EMPTY) {
								for (int jj = j; jj < j + 3; jj++) {
									arr[i][jj] = CellState.STATE_VESSEL_4;
								}
							} else {
								for (int jj = j; jj < j + 2; jj++) {
									arr[i][jj] = CellState.STATE_VESSEL_3;
								}
							}
						} else {
							for (int jj = j; jj < j + 1; jj++) {
								arr[i][jj] = CellState.STATE_VESSEL_2;
							}
						}
					} else {
						arr[i][j] = CellState.STATE_VESSEL_1;
					}
				}
			}
		}
		// transfer the values from compiled temp array to the individual cellState(s)
		for (int i = 0; i < BGrid.GRID_SIZE; i++) {
			for (int j = 0; j < BGrid.GRID_SIZE; j++) {
				System.out.print(arr[i][j] + " ");
				this.button[i][j].setCellStateNumber(arr[i][j]);
			}
			System.out.println("");
		}
	}

	// ActionListener Method(s) -----------------------------------------------
	public void actionPerformed(ActionEvent e) {
	}

	// Main TEST CLIENT -------------------------------------------------------
	public static void main(String[] args) {
		JFrame window = new JFrame("BGrid (March 31)");
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BGrid game = new BGrid();

		KeyListener ke = null;

		game.compileVessels();

		// game.gridlayout = gridlayout;
		// game.setLayout(new GridLayout(10, 10));

		System.out.println("path = " + game.getClass().getName());

		// setGridlayout(new GridLayout(10, 10));
		// BGrid.gridlayout = gridlayout;

		game.addKeyListener(ke);
		window.add(game);
		window.addKeyListener(ke);
		window.pack();
		window.setVisible(true);

	}

	@Override
	public void keyPressed(KeyEvent ke) {
		this.compileVessels();
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub

	}

}