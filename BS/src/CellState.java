/*
 * @author Shin Kaya (27 March 2018)  
 */
public class CellState {
	private int number; // Acceptable values for CellState.number defined as follows:

	// Class parameters -------------------------------------------------------
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

	public static final int MAX_NUMBER_OF_STATE = 15; // fired and hit cell part of four cell vessel (wounded)

	// CONSTRUCTORS ============================================================
	public CellState() { // Constructor #1
		this(STATE_EMPTY); // invoke #2 with default (Empty State num)
	}

	public CellState(int desiredStateNumber) { // Constructor #2
			setCellStateNumber(desiredStateNumber);
	}

	// Getters and Setters ----------------------------------------------------
	public int getCellStateNumber() {
		return number;
	}

	public void setCellStateNumber(int newStateNumber) {
		switch (newStateNumber) {
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
			number = newStateNumber; // allow updating the CellState
			break;
		default:
			System.out.println("undefined CellState: " + newStateNumber + "@ " + getClass());
		}
	}

	// TEST CLIENT ============================================================
	public static void main(String[] args) {
		System.out.println("Testing Class: 'CellState' " + "\n--------------------------");
		CellState a = new CellState();
		System.out.println("state=" + a.getCellStateNumber());
		CellState b = new CellState(20);
		System.out.println("state=" + b.getCellStateNumber());
		CellState c = new CellState(-10);
		System.out.println("state=" + c.getCellStateNumber());

		for (int i = -2; i <= 23; i++) {
			a.setCellStateNumber(i);
			System.out.println("tried=" + i + " set = " + a.getCellStateNumber());
		}
		System.out.println("--------------------------\n");

	}
	// ========================================================================
}
// END-OF-CODE