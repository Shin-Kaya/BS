import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

public class BVessel extends JPanel implements KeyListener {
	// instantiation variables
	private BButton[] button;
	private Pair vesselStartXY; // pair array for keeping individual vessel start positions
	private Orientation orientation; // orientation variable declaration

	// Class variables
	private static final int[] VESSEL_SIZE = { 1, 2, 3, 4 };
	private static final int[] VESSEL_NUMBER = { 4, 3, 2, 1 };
	private static int[] randomArray;
	private static int num;
	private static int lastNum;
	private static Pair lastPair;
	private static Random random;

	private static enum Orientation { // "Orientation Type" declaration
		HORIZONTAL, VERTICAL // Vessel's orientation can be horizontal (along any row)
	}; // or vertical along any column

	static int[][] stateArray;

	public BVessel() {
		this(1, 0, 0, Orientation.HORIZONTAL); // default vessel orientation
	}

	public BVessel(int vesselSize, int row, int col, Orientation orientation) {
		
		vesselStartXY = new Pair(row, col);
		if (vesselSize < VESSEL_SIZE[VESSEL_SIZE.length-1])
		for (int i = 0; i < VESSEL_SIZE.length; i++) {
			
			// create individual instances with row, col
			button[i] = new BButton(i, 0, CellState.STATE_EMPTY, GameMode.GAME_SETUP);
			// set the image to the component
			button[i].setIcon(ImageLoader.getImageIcon(ImageLoader.NUM_IMAGE_EMPTY));
			// add it to the JPanel (which is extended by this class)
			this.add(button[i]);
		}

//		vesselStartXY = new Pair[numberOfVessels]; // final pairs of row col for individual vessels
//		for (int i = 0; i < vesselStartXY.length; i++) {
//			vesselStartXY[i] = new Pair(0, 0); // initialize the array with row=0, col=0
//		}
//
//		randomArray = new int[gridRow * gridCol]; // 1Dim array matching member of gridRow x gridCol
//		for (int i = 0; i < randomArray.length; i++) {
//			randomArray[i] = i; // initialize the array for generating unique random numbers
//		}
//
//		stateArray = new int[gridRow][gridCol]; // 2Dim array corresponding BGrid.CellState[][]
//		for (int i = 0; i < gridRow; i++) {
//			for (int j = 0; j < gridCol; j++) {
//				// initialize the array with CellState.STATE_EMPTY before setting up vessels
//				stateArray[i][j] = CellState.STATE_EMPTY;
//			}
//		}

		lastNum = numberOfVessels - 1; // initialize the lastNum for unique consecutive random numbers
		random = new Random();
		// int k = 0; // index for vesselStartXY positioning array
		// for (int i = 0; i < VESSEL_NUMBER.length; i++) {
		// for (int j = 0; j < VESSEL_SIZE[i]; j++) {
		// vesselStartXY[k] = getRandomRowCol(VESSEL_SIZE[j], VESSEL_NUMBER[i]);
		// System.out.println("i:" + i + ", j:" + j + ", k:" + k + ", num:" + num + ",
		// row:" + vesselStartXY[k].getRow()
		// + ", col:" + vesselStartXY[k].getCol());
		// k++;
		// }
		// }
	}

	public void makeVessels() {

		// XXXXX check and update cellState with the lastPair
		// if cellstate = 0
		// set to Occupied
		// else
		//
		// switch (k) {
		// case value:
		//
		// break;
		//
		// default:
		// break;
		// }
		// }
	}

	// Working progress AI not completed: needs to be re-worked
	// ------------------------------
	public static Pair methodX(int vesselSize, int VesselNum) {
		int n = getNextNumber(vesselSize, VesselNum);
		// Check CelState
		for (int i = 0; i < VesselNum; i++) {
			if (vesselSize > 1) {

			}
		}
		return convertRowCol(num);
		// return convertRowCol( random.nextInt( gridRow * gridCol ) )
	}

	public static int getNextNumber(int vesselSize, int VesselNum) {
		if (lastNum < 0) {
			System.out.println("Logic Error: There is only one cell left (no need to call this) ");
			return randomArray[0];
		} else {
			int num = random.nextInt(lastNum); // generate a new random number between 0 to last
			System.out.println("num:" + num);

			// swap array instance contents at 'num' and 'lastNum' and
			// also decrement lastNum to avoid regenerating the same random number
			int tempInt = -randomArray[num]; // save the value at the random number where it points to (with negation)
			randomArray[num] = randomArray[lastNum]; // override the content of the array at the random number
			randomArray[lastNum] = tempInt; // assign the last member to random number with negative sign indicating
											// that
			// member is used and not to be generated again
			lastNum--; // decrement the last number to avoid repeating previously generated number
			return num;
		}
	}

	public static int convertInteger(Pair pair) {
		return (pair.getCol() * 10 + pair.getRow());
	}

	public static Pair convertRowCol(int number) {
		Pair retPair = new Pair();
//		retPair.setRow(number / gridRow); // calculate the 2D-row number based on gridRow using integer division
//		retPair.setCol(number % gridCol); // calculate the 2D-col number based on gridCol using the MODE(%) operator
		return retPair; // return the array containing (1) ROW (2) COLOUMN numbers
	}

	public static void main(String[] args) {
		BVessel ves = new BVessel();

		int k = 0; // index for vesselStartXY positioning array
		int n = 0; // random number

		for (int i = 0; i < VESSEL_NUMBER.length; i++) {
			for (int j = 0; j < VESSEL_NUMBER[i]; j++) {
				try {
					// n = getNextNumber(); // TBD later
					lastPair = convertRowCol(n); // n is needed
				} catch (Exception e) {
					System.out.println("\nexception.." + e.toString()); // e.printStackTrace();
				}

				// Set the CellState for the current cell
				verifyCellState(lastPair.getRow(), lastPair.getCol(), VESSEL_SIZE[i]);

				// BVessel.setStateArrayAt(lastPair.getRow(), lastPair.getCol(),
				// VESSEL_SIZE[i]);

				// Set the other cells part of the vessel (i.e. > 1 cell in size)
				if (VESSEL_SIZE[i] > 1) {
					// if BVessel.getStateArrayAt(lastPair.getRow(), lastPair.getCol(),
					// VESSEL_SIZE[i]);

//					if ((lastPair.getRow() + VESSEL_SIZE[i] - 1) < (gridRow - 1)) // border control
						for (int jj = 0; jj < VESSEL_SIZE[i] - 1; jj++)
							// extend the vessel incremental
//							BVessel.setStateArrayAt(lastPair.getRow() + jj, lastPair.getCol(), VESSEL_SIZE[i]);
					// remove number from randomArray
//					else // border control right hand side
//						for (int jj = 0; jj < VESSEL_SIZE[i] - 1; jj++)
							// extend the vessel decremental
							BVessel.setStateArrayAt(lastPair.getRow() - jj, lastPair.getCol(), VESSEL_SIZE[i]);
				}
			}

			// methodX(VESSEL_SIZE[j], VESSEL_NUMBER[i]); // TBD later
			// System.out.println("i:" + i + ", j:" + j + ", k:" + k + ", num:" + num + ",
			// row:" + vesselStartXY[k].getRow()
			// + ", col:" + vesselStartXY[k].getCol());
//			vesselStartXY[k] = lastPair;
			k++;
		}
	}

	public static void verifyCellState(int row, int col, int vesselSize) {
		switch (BVessel.getStateArrayAt(row, col)) {
		case CellState.STATE_EMPTY:
		case CellState.STATE_VESSEL_1:
		case CellState.STATE_VESSEL_2:
		case CellState.STATE_VESSEL_3:
		case CellState.STATE_VESSEL_4:
			BVessel.setStateArrayAt(row, col, vesselSize);
			break;
		default:
			System.out.println("BVessel.verifyCellState(): Invalid vesselSize:" + vesselSize);
			break;
		}
		if (row + 1 < gridRow) {
			BVessel.setStateArrayAt(row + 1, col, vesselSize);

			// }
			// if ( ) {
			BVessel.setStateArrayAt(row, col, vesselSize);
			// TBD METHOD LATER - remove add number ho the randomArray, lastNum++;
			if (row + 1 < gridRow)

				for (int i = row - 1; i <= row + 1; i += 2) {
					for (int j = col - 1; j <= col + 1; j++) {
						if (BVessel.getStateArrayAt(row, col) > CellState.STATE_EMPTY)
							;
					}
				}
		}
		// else .... TBD METHOD LATER - remove add number ho the randomArray, lastNum++;

	}

	// Getters and Setters ====================================================
	public static int[][] getStateArray() {
		return stateArray;
	}

	private static int getStateArrayAt(int row, int col) {
		return BVessel.stateArray[row][col];
	}

	public static void setStateArray(int[][] stateArray) {
		BVessel.stateArray = stateArray;
	}

	public static void setStateArrayAt(int row, int col, int numberValue) {
		switch (numberValue) {
		case CellState.STATE_EMPTY:
		case CellState.STATE_VESSEL_1:
		case CellState.STATE_VESSEL_2:
		case CellState.STATE_VESSEL_3:
		case CellState.STATE_VESSEL_4:
		case CellState.STATE_EMPTY_HIT:
		case CellState.STATE_VESSEL_1_HIT:
		case CellState.STATE_VESSEL_2_HIT:
		case CellState.STATE_VESSEL_3_HIT:
		case CellState.STATE_VESSEL_4_HIT:
			BVessel.stateArray[row][col] = numberValue;
			break;
		default:
			System.out.println("undefined CellState: " + numberValue);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
