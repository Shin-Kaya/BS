
/* 
 * @author SHin Kaya (25 March 2018)
 * Ref: https://www.udemy.com/java-swing-complete/learn/v4/t/lecture/104280
 * Ref: https://stackoverflow.com/questions/5987600/simple-adding-a-jlabel-to-jpanel 
 */

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMiddle extends JPanel {

	public PanelMiddle() { // CONSTRUCTOR #1
		this("               PLAYER BOARD", "COMPUTER BOARD               ");
	}

	public PanelMiddle(String text) { // CONSTRUCTOR #2
		this(text, null);
	}

	public PanelMiddle(String text1, String text2) { // CONSTRUCTOR #3
		// set the layout in the constructor
		super(new FlowLayout(FlowLayout.LEADING));

		// set size OR preferred size of the JPanel
		// setPreferredSize( new Dimension(100, 10) ); // actual size to be adjusted
		// later

		// create individual component-instances and initialize
		// CODE GOES HERE .... XXX

		// add components to the JPanel (i.e. PanelMiddle)
		// add(playerBoard, computerBoard);
	}

	public static void main(String[] args) {
		System.out.print("Run this Panel from App.Java \n...End...");
	}

}
