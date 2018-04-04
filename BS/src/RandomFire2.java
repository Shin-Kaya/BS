/*
 * @author Shin Kaya (31 March 2018) 
 */

public class RandomFire {
	/**
	 * 
	 */
	private int 		row;
	private int 		col;
	private int 		lineArraySize;
	private int[ ] 		array;
	private int[ ][ ] 	array2d;

	private static final long serialVersionUID = 1L;
	
	// Constructors ===========================================================
	public RandomFire () {		// Constructor #1  
		this(10, 10);			// invoke #2 with defaults
	}
								// Constructor #2
	public RandomFire (int gridRow, int gridCol) {									
		this.row = gridRow;
		this.col = gridCol;
		lineArraySize = row * col;
		
		
		int[ ] array = new int[lineArraySize];				// creating the array
			
		for (int i = 0; i < array.length; i++) {		// initializing the array 
			array[i] = i; 
		}

		int[ ][ ] array2d = new int[row][col];			// creating the array
		for (int i = 0; i < array.length; i++) {		// initializing the array 
			for (int j = 0; j < array.length; j++) {	// initializing the array 
					array2d[i][j] = i*10+j; 			//
					array[i*10+j] = i*10+j;
					System.out.println(i + ", " + j + "\t array:" + array[i*10+j] + "\t array2d:" + array2d[i][j] );
			}	
		}
	}

	public static void main(String[] args) {
		System.out.println("Testing Class: RandomFire");
		RandomFire f = new RandomFire();
	}

}
