import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * @author Shin Kaya (27 March 2018)  
 */

public class ImagePointer extends JPanel {

	private static int 			num;
	private static ImageIcon[ ] 	image; 	
	private static String[ ] 	imageName;

	// Class parameters -------------------------------------------------------
	public static final int IMAGE_EMPTY			= 0;		// 
	public static final int IMAGE_FILLED			= 1;		// 
	public static final int IMAGE_COVERED 		= 2;		// vessel are covered (hidden) during game mode  
	public static final int IMAGE_MISS 			= 3;		// 
	public static final int IMAGE_HIT			= 4;		// 

	public static final int NUMBER_OF_IMAGES		= 5;		// 
	private static final long serialVersionUID 	= 1L;		// find-out why this is required

	// CONSTRUCTORS ============================================================
	public ImagePointer () {					// Constructor #1 
		this(IMAGE_EMPTY);						// invoke #2
	}
	
	public ImagePointer (int imgPtr) {			// Constructor #2 
		setImageName(new String[NUMBER_OF_IMAGES]); 	// create individual instances
		String[ ] imageName = { 						// initialize individual instances
				"img_empty.png", 		// 0 
				"img_filled.png", 		// 1
				"img_covered.png", 		// 2
				"img_miss.png", 			// 3
				"img_hit.png" 			// 4
		};
		setValue(imgPtr);
		image = new ImageIcon[NUMBER_OF_IMAGES];   
		for (int i = 0; i < imageName.length; i++) {
			try {
				image[i] = new ImageIcon( ( this.getClass().getResource( getImageName(i) ) ));
			} catch (Exception e) {
				System.out.println(i + ".... " + getImageName(i) + " not found (folder bin)");
			}
		}
		System.out.println("initiated to =" + getNum());
	}

	// Getters and Setters ----------------------------------------------------
	public int getNum() {
		return num;
	}

	public static void setValue(int imagePointer) {
		if ( (imagePointer >= 0 ) && (imagePointer < NUMBER_OF_IMAGES) )
				ImagePointer.num = imagePointer;
		else {
				System.out.println("Error: ImagePointer out of range!..( < 0 or > " + NUMBER_OF_IMAGES);
				if (imagePointer < 0 ) 
						ImagePointer.num = 0;
				else if ( imagePointer >= NUMBER_OF_IMAGES ) 
						ImagePointer.num = NUMBER_OF_IMAGES - 1;
		}
	}

	public static String getImageName(int i) {
		return imageName[i];
	}

	private static void setImageName(String[ ] imageName) {
		// PRIVATE METHOD image name is allowed to changed within local class   
		ImagePointer.imageName = imageName;
	}
		
	// TEST CLIENT ============================================================
	public static void main(String[] args) {
		System.out.println("Testing Class: 'ImagePointer' " + 
						 "\n------------------------------");
		ImagePointer a = new ImagePointer();
		for (int i = -5; i < 10; i++) {
			setValue(i);
			System.out.println("tried=" + i + " set = " + a.getNum());
		}
		System.out.println("------------------------------\n");
		
		for (int i = 0; i < NUMBER_OF_IMAGES; i++) {
			try {
				System.out.println(a.getClass().getResource(getImageName(i) ));
			} catch (Exception e) {
				System.out.println(i + ". " + getImageName(i) + " not found (folder bin)");
			}
		}

	}
	// ========================================================================
}
// END-OF-CODE