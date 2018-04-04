
/* 
 * @author Shin Kaya (31 March 2018) 
 * REFERENCES: 
 * https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
 * 
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Ref: https://www.youtube.com/watch?v=40ikcEonWng
// https://www.youtube.com/watch?v=Db3cC5iPrOM

public class BButton extends JButton implements ActionListener {
	// parameters for individual instances ------------------------------------
	private ImageIcon[] image; // array of images can be assigned to the Cell for different cases
	private ImageLoader imageLoader; // indicates which image to show at one time
	private int row; // horizontal position: row number in a matrix
	private int col; // vertical position: column number in a matrix
	private CellState cellState; // indicates whether cell is occupied and got hit etc.
	private int imagePointer; // keeps track of which image to show based on cellState

	// class static variables and types ---------------------------------------
	private static GameMode gameMode = new GameMode(GameMode.GAME_SETUP);
	private static boolean imageCovered = false; //
	private static final long serialVersionUID = 1L; // find-out why this is required

	private static String lastMessage; // enabling a verbose feedback/error mechanism

	// Constructor #1 =========================================================
	public BButton() {
		this(0, 0, CellState.STATE_EMPTY, GameMode.GAME_SETUP);
		// invoke the second constructor (with desired row, col, cellStateNum,
		// GameModeNum)
	}

	// Constructor #2 =========================================================
	public BButton(int row, int col, int desiredCellStateNumber, int gameModeNumRequested) { // Constructor #2
		image = new ImageIcon[ImageLoader.IMAGE_NAME.length]; // create the array match the size of the image array
		imageLoader = new ImageLoader(); // create the imageLoader which has the images

		// populating individual image[s] from imagePointer image array - - - - - - - -
		// - -
		for (int i = 0; i < ImageLoader.IMAGE_NAME.length; i++) {
			try {
				image[i] = new ImageIcon(ImageLoader.getImageName(i));
			} catch (Exception e) {
				System.out.println(i + ". " + ImageLoader.getImageName(i) + " not found (folder bin)");
			}

			this.row = row; // grid positioning of row will be retained for BGrid
			this.col = col; // grid positioning of row will be retained for BGrid

			cellState = new CellState();
			if (desiredCellStateNumber != cellState.getCellStateNumber()) {
				cellState.setCellStateNumber(desiredCellStateNumber); // initialize the cellState to desired number(s)
																		// (for testing purposes)
			}

			switch (cellState.getCellStateNumber()) {
			case CellState.STATE_EMPTY:
				setImagePointer(ImageLoader.NUM_IMAGE_EMPTY); // default is empty
				break;

			case CellState.STATE_VESSEL:
				setImagePointer(ImageLoader.NUM_IMAGE_FILLED); // default is empty

			default:
				break;
			}

			setGameMode(GameMode.getGameModeNumber()); // static way of initialization gameMode from the passed
														// parameter

			if (getGameMode() == GameMode.GAME_SETUP) {
				setImageCovered(false); // allow user to see all vessels
			} else
				setImageCovered(true); // cover the vessel images during game

		}
		setIcon(image[ImageLoader.NUM_IMAGE_EMPTY]); // set the BCell (extends JButton) icon for display
		this.addActionListener(this);
		printAttributes();
	}

	public void printAttributes() {
		System.out.println("Print Attribues: " + getClass());
		System.out.println("-------------------------------");
		System.out.println("row, col : " + row + ", " + col);
		System.out.println("cellState: " + cellState.getCellStateNumber());
		System.out.println("gameMode : " + GameMode.getGameModeNumber());
		System.out.println("-------------------------------");
	}

	// =========================================================================
	// Getters and Setters ----------------------------------------------------
	// =========================================================================
	public static ImageIcon getImageIcon() {
		// get the current image that image pointer indicates
		return getImageIcon(ImageLoader.NUM_IMAGE_EMPTY); // invoke with default int
	}

	public static ImageIcon getImageIcon(int selectedImage) {
		return ImageLoader.getImageIcon(selectedImage);
	}

	public static boolean isImageCovered() {
		return imageCovered;
	}

	public static void setImageCovered(boolean covered) {
		imageCovered = covered;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setRowCol(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCellStateNumber() {
		return cellState.getCellStateNumber();
	}

	public void setCellStateNumber(int cellStateNumber) {
		this.cellState.setCellStateNumber(cellStateNumber);
	}

	public int getImagePointer() {
		return imagePointer;
	}

	public void setImagePointer(int imageNumRequested) {
		if ((imageNumRequested >= 0) && (imageNumRequested < ImageLoader.IMAGE_NAME.length))
			this.imagePointer = imageNumRequested;
		else {
			System.out.println("ImagePointer, " + imageNumRequested + " is out of range!");
			this.imagePointer = ImageLoader.NUM_IMAGE_EMPTY; // reset to default
		}
	}

	public static String getLastMessage() {
		return lastMessage;
	}

	public static void setLastMessage(String lastMessage) {
		BButton.lastMessage = lastMessage;
	}

	public int getGameMode() {
		return GameMode.getGameModeNumber(); // invoking the GameMode class method
	}

	public void setGameMode(int gmMode) {
		GameMode.setGameModeNumber(gmMode); // invoking the GameMode class method
	}

	public void setImage(ImageIcon[] img) {
		image = img;
	}

	public String toString() { // toString () override (duplicating the getXY method)
		return "(" + row + "," + col + ")";
	}

	// ActionListener Methods -------------------------------------------------
	public void actionPerformed(ActionEvent e) {
		BButton clicked = (BButton) e.getSource();
		System.out.print("click:row(" + clicked.row + "," + clicked.col + "), gmMode:" + clicked.getGameMode() + " ");
		if (cellState.getCellStateNumber() < CellState.STATE_VESSEL_4)
			cellState.setCellStateNumber(cellState.getCellStateNumber() + 1);
		else
			cellState.setCellStateNumber(CellState.STATE_EMPTY);

		if (getImagePointer() < ImageLoader.IMAGE_NAME.length)
			setImagePointer(getImagePointer() + 1);
		else
			setImagePointer(ImageLoader.NUM_IMAGE_EMPTY);

		setIcon(getImageIcon(getImagePointer()));

	}

	public void actionPerformedOriginal(ActionEvent e) {
		BButton clicked = (BButton) e.getSource();
		System.out.print("click:row(" + clicked.row + "," + clicked.col + "), gmMode:" + clicked.getGameMode() + " ");

		switch (clicked.getGameMode()) {
		case GameMode.GAME_SETUP:
			actionGameSetup(clicked);
			break;

		case GameMode.GAME_USER_TURN:
			actionUsersTurn(clicked);
			break;

		case GameMode.GAME_COMPUTER_TURN:
			System.out.println("Not your turn\n");
			setLastMessage("Not your turn");
			actionComputersTurn(clicked);
			break;
		default:
			System.out.println(",,");
			break;
		}
	}

	public void actionGameSetup(BButton clicked) {

		switch (clicked.cellState.getCellStateNumber()) {

		case CellState.STATE_EMPTY:
			setIcon(getImageIcon(ImageLoader.NUM_IMAGE_FILLED));
			try {
				clicked.cellState.setCellStateNumber(CellState.STATE_VESSEL_1); // toggle between fill and empty
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("cellState = " + cellState.getCellStateNumber());
			// AI --> check surrounding buttons for Vessel

			break;

		case CellState.STATE_VESSEL: // or STATE_VESSEL_1 = STATE_VESSEL = 1
			setIcon(getImageIcon(ImageLoader.NUM_IMAGE_EMPTY));
			clicked.cellState.setCellStateNumber(CellState.STATE_EMPTY); // toggle between fill and empty
			if (cellState.getCellStateNumber() > CellState.STATE_EMPTY)
				; {
			// AI --> reverse STATE for the surrounding buttons
		}
			cellState.setCellStateNumber(CellState.STATE_EMPTY); // toggle between fill and empty
			System.out.println("state= " + cellState.getCellStateNumber());
			break;

		default:
			System.out.println("No other state change allowed this mode" + cellState.getCellStateNumber());
			break;
		}
	}

	public void actionUsersTurn(BButton clicked) {
		// switch (clicked.state) {
		// case STATE_:
		// // TBC
		// break;
		//
		// default:
		// break;
		// }
	}

	public void actionComputersTurn(BButton clicked) {
		// switch (clicked.state) {
		// case STATE_:
		// // TBC
		// break;
		//
		// default:
		// break;
		// }
	}

	// TEST CLIENTS ===========================================================
	public static void SimpleTest() {
		BButton a = new BButton();
		a = new BButton();
		System.out.println(a.toString() + "\n");

		// testing within an array
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			a.setRow(random.nextInt(100));
			a.setCol(random.nextInt(100));
			System.out.println(i + "\t (" + a.getRow() + ", " + a.getCol() + ")");
		}
		System.out.println("\nEND testing class 'Cell'...");
	}

	// TEST CLIENT MAIN =======================================================
	public static void main(String[] args) {

		// SimpleTest(); //

		// TESTING GUI
		int j = 0;
		BButton[] btn;
		btn = new BButton[ImageLoader.IMAGE_NAME.length];
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new BButton(i, j++, i, GameMode.getGameModeNumber());
			btn[i].setGameMode(GameMode.getGameModeNumber());
		}

		ActionListener e = null;

		// create a JFrame (a Window)
		JFrame window = new JFrame("Testing: class BButton"); // + btn.getClass().toString() );
		window.setLayout(new FlowLayout()); // set layout for JFrame
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create a JPanel (a view pane in the Window)
		JPanel guiPanel = new JPanel();
		guiPanel.setLayout(new GridLayout(5, 1)); // set layout (print layout) for JPanel

		// add the desired individual component(s) to the JPanel

		for (int k = 0; k < ImageLoader.IMAGE_NAME.length; k++) {
			System.out.println(/* "i= " + i + ", */"k= " + k);
			btn[k].setIcon(getImageIcon(k)); // set different individual image(s) to individual buttons
			guiPanel.add(btn[k]); // add individual button to the JPanel
			window.add(guiPanel); // add the JPanel to the JFrame
			btn[k].addActionListener(e);
		}
		// window.setSize(100, 200); // size the window at the desired dimentions (x, y)
		window.pack(); // pack the window size to its components size
		window.setVisible(true); // make it visible
	}
}
// END-OF-CODE