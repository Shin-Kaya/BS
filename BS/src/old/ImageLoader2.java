import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * @author Shin Kaya (31 March 2018)  
 */

public class ImageLoader2 extends ImageIcon {   // a better way of using imageLoader TBD later

	private static ImageIcon[ ]	imageIcon; 	

	// Class parameters -------------------------------------------------------
	public static final String[ ] IMAGE_NAME = {  
			"img_empty.png", 		// 0 
			"img_filled.png", 		// 1
			"img_covered.png", 		// 2
			"img_miss.png", 		// 3
			"img_hit.png" 			// 4
	};
	public static final int NUM_IMAGE_EMPTY			= 0;		// 
	public static final int NUM_IMAGE_FILLED		= 1;		// 
	public static final int NUM_IMAGE_COVERED 		= 2;		// vessel are covered (hidden) during game mode  
	public static final int NUM_IMAGE_MISS 			= 3;		// 
	public static final int NUM_IMAGE_HIT			= 4;		// 

//	Eclipse Wrote: The serializable class ImageLoader does not declare btn static final serialVersionUID field of type long
	private static final long serialVersionUID 	= 1L;	// find-out what it actually does and why this is necessary  

	// CONSTRUCTORS ============================================================
	public ImageLoader2 () {						// Constructor #1 
		this(NUM_IMAGE_EMPTY);					// invoke #2
	}
	
	public ImageLoader2 (int imageNum) {			// Constructor #2 
//		setImagePointer(imageNum);				// set the pointer to the desired number 
		for (int i = 0; i < IMAGE_NAME.length; i++) {
			System.out.println(IMAGE_NAME[i]);
		}
		
		imageIcon = new ImageIcon[IMAGE_NAME.length];
		
		System.out.print("Image Icons: ");
		for (int i = 0; i < IMAGE_NAME.length; i++) {
			try {
				imageIcon[i] = new ImageIcon( getClass().getResource( IMAGE_NAME[i] ) );
				System.out.print(i + ", ");
			} catch (Exception e) {
				System.out.println(i + IMAGE_NAME[i] + " not found in 'bin'");
			}
		}
		printAttributes();
	}
	
	public void printAttributes () {
		System.out.println("\n----------------------------------");
		System.out.println(getClass() + ".printAttribues()");
		System.out.println("----------------------------------");
		for (int i = 0; i < ImageLoader2.IMAGE_NAME.length; i++) {
			System.out.println(i+ "." + ImageLoader2.getImageName(i)) ;
		}
		System.out.println("\n----------------------------------");
	}

	// Getters and Setters ----------------------------------------------------

	public static String getImageName(int i) {
		return IMAGE_NAME[i];
	}
		
	public ImageIcon getImageIcon(int ImageRequested) {
		if ( (ImageRequested >= 0 ) && (ImageRequested < IMAGE_NAME.length) )
				return imageIcon[ImageRequested];
		else {	
				System.out.println("There is no imageIcon with number:" + ImageRequested);
				return imageIcon[IMAGE_NAME.length-1];
		}
	}

	// TEST CLIENT ============================================================
	public static void main(String[] args) {
//		logicTestClient ();
		guiTestClient(); 		
	}
	
	public static void guiTestClient () {
		
		// Declarations (1) ---------------------------------------------------
		JButton[] btn = new JButton[5];
		ImageLoader2 b = new ImageLoader2();

		JFrame window = new JFrame("Testing: '" + b.getClass() + "'");
		window.setLayout( new BorderLayout() );
		
		JPanel panel = new JPanel();
		panel.setLayout( new FlowLayout() );

		// Instantiations (2) -------------------------------------------------
		for (int i = 0; i < 5; i++) {
			btn[i] = new JButton();
			btn[i].setIcon(b.getImageIcon(i));
			panel.add(btn[i]);
		}
		
		// Addition to container (3) ------------------------------------------
		window.add(panel, BorderLayout.NORTH); 
		
		// wrapping-up 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // close the open window on exit
		window.pack();  // find-out: what does this do and when this is needed ?????

		// Visualization (4) --------------------------------------------------
		window.setVisible(true);
	}
	
	public static void logicTestClient () { 
		ImageLoader2 btn = new ImageLoader2();
		System.out.println("\n------------------------------" 
					+ "Testing Class" + btn.getClass()
					+ "\n------------------------------");
		System.out.println("------------------------------\n");
		
		for (int i = 0; i < IMAGE_NAME.length; i++) {
			try {
				System.out.println(btn.getClass().getResource(ImageLoader2.getImageName(i) ));
			} catch (Exception e) {
				System.out.println(i + ". " + ImageLoader2.getImageName(i) + " not found (folder bin)");
			}
		}

	}
	// ========================================================================
}
// END-OF-CODE