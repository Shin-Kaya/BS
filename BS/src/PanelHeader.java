
/* 
 * @author SHin Kaya (25 March 2018)
 * Ref: https://www.udemy.com/java-swing-complete/learn/v4/t/lecture/104280
 * Ref: https://stackoverflow.com/questions/5987600/simple-adding-a-jlabel-to-jpanel 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelHeader extends JPanel {
	private JLabel label1, label2;

	public PanelHeader() { // CONSTRUCTOR #1
		this("PLAYER BOARD", "COMPUTER BOARD");
	}

	public PanelHeader(String text) { // CONSTRUCTOR #2
		this(text, null);
	}

	public PanelHeader(String text1, String text2) { // CONSTRUCTOR #3
		super(); // set the layout in the constructor
		this.setLayout(new BorderLayout());

		label1 = new JLabel(text1); // create label1 with passed text1
		// label1.setFont(font); // TBD later : Larger font
		JLabel label_spacer = new JLabel("           ");
		label2 = new JLabel(text2); // create label2 with passed text2
		// label2.setFont(font); // TBD later : Larger font

		add(label1, BorderLayout.WEST); // add labels to the PanelHeader (i.e. JPanel)
		add(label_spacer, BorderLayout.CENTER);
		add(label2, BorderLayout.EAST);

	}

	public static void main(String[] args) {
		System.out.print("Run this Panel from App.Java \n...End...");

	}

}
