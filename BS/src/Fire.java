import java.util.Random;

public class Fire {

	private static int[] arr;
	private static int last;
	private static int gridRow;
	private static int gridCol;
	private static int gridSizeSquared;
	private static Random random = new Random();
	private static Fire fire;

	public Fire() {
		this(10, 10); 	 // BattleGrid default 10 x 10 grid  
	}
	
	public Fire(int gridRow, int gridCol) {
		Fire.gridRow = gridRow; // "gridRow = 10 means" grid consist of 10 rows
		Fire.gridCol = gridCol; // "gridCols = 10 means" grid consist of 10 cols
		Fire.gridSizeSquared = gridRow * gridCol;
		last = gridSizeSquared - 1; // initialize last fire to the last member of gridArray (2D-array)
		arr = new int[gridSizeSquared]; // create the array the matching 1D array

		for (int i = 0; i < arr.length; i++) {
			arr[i] = i; // populate the array as individual pointers to gridArray (2D-array)
		}
	}

	public static int getNextNumber() throws Exception {
		if (last < 0) {
			throw new Exception("terminated: last < 0 -->" + fire.getClass());
		}

		int r = random.nextInt(last); // generate a new random number between 0 to last

		// System.out.println("i:" + i);
		int tmp = -arr[r]; 	// save the value at the random number points to (with negation)
		arr[r] = arr[last]; // override the content of the array at the random number
		arr[last] = tmp; 	// assign the last member to random number with negative sign indicating that
							// member is used
		last--; // decrement the last number to avoid repeating previously generated number
		return r;
	}


	public static int[] convertRowCol(int number) {
		final int ROW = 0;
		final int COL = 1;
		int[] retArr = new int[2]; 		// integer array to be returned in the form of (1)Row (2) Col
		retArr[ROW] = number / gridRow; // calculate the 2D-row number based on gridRow using integer division
		retArr[COL] = number % gridCol; // calculate the 2D-col number based on gridCol using the MODE(%) operator
		return retArr; // return the array containing (1) ROW (2) COLOUMN numbers
	}
//  REPLACE THE ABOVE METHOD WITH THIS ONE USING PAIR CLASS LATER FOR SIMPLICITY
//	public static Pair convertRowCol(int number) {
//	Pair retPair = new Pair();
//	retPair.setRow(number / gridRow); // calculate the 2D-row number based on gridRow using integer division
//	retPair.setCol(number % gridCol); // calculate the 2D-col number based on gridCol using the MODE(%) operator
//	return retPair; // return the array containing (1) ROW (2) COLOUMN numbers
//	}

	// CLASS TEST CLIENT ======================================================
	public static void main(String[] args) {
		int num;

		fire = new Fire(3, 3); // local variable
		printAttributes(Fire.arr, last);
		try {
			for (int i = 0; i < (gridRow * gridCol - 1); i++) {
				num = Fire.getNextNumber();
				System.out.print("\nr:" + num);
				printAttributes(Fire.arr, last);

				System.out.print("...");
				printAttributes(Fire.convertRowCol(num), -last);
			}
		} catch (Exception e) {
			System.out.println("\nexception.." + e.toString());
			// e.printStackTrace();
		}
		System.out.println("\nEnd...");
	}

	static void printAttributes(int[] arr, int last) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("\t" + arr[i]);
		}
		System.out.print("\tlast:" + last);
	}

}
