/*
 * @author Shin Kaya (27 March 2018)  
 */
public class GameMode {
	private static int number; 

	// Class parameters -------------------------------------------------------
	public static final int GAME_SETUP 			= 0;
	public static final int GAME_USER_TURN		= 1;
	public static final int GAME_COMPUTER_TURN	= 2;
	
	public static final int NUMBER_OF_GAME_MODES = 3;
	// CONSTRUCTORS ============================================================
	public GameMode () {				// Constructor #1 
		this(GAME_SETUP);				// invoke #2 with default GAME_SETUP mode
	}
	
	public GameMode (int gameMode) {		// Constructor #2 
		setGameModeNumber(gameMode);
	}

	// Getters and Setters ----------------------------------------------------
	public static int getGameModeNumber() {
		return number;
	}

	public static void setGameModeNumber(int newModeNumber) {
		if ( (newModeNumber >= 0 ) && (newModeNumber < NUMBER_OF_GAME_MODES) )
			number = newModeNumber;
		else {
			System.out.println("GameMode:Err: # out of range! (<0 or >" + NUMBER_OF_GAME_MODES +")");
			if (newModeNumber < 0 ) 
					number = 0;
			else if ( newModeNumber >= NUMBER_OF_GAME_MODES ) 
					number = (NUMBER_OF_GAME_MODES - 1);
		}
	}

	// TEST CLIENT ============================================================
	public static void main(String[] args) {
		System.out.println("Testing Class 'GameMode' " + 
						 "\n--------------------------");
		GameMode a = new GameMode();
//		GameMode b = new GameMode(10);
//		GameMode c = new GameMode(-5);
		
		for (int i = -2; i < 5; i++) {
			setGameModeNumber(i);
			System.out.println(a.getClass() + ", tried:" + i + " set:" + getGameModeNumber());
		}
		System.out.println("--------------------------\n");

	}
	// ========================================================================
}
// END-OF-CODE