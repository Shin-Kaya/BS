
/* 
 * @author SHin Kaya (25 March 2018)
 * Ref: https://www.udemy.com/java-swing-complete/learn/v4/t/lecture/104280
 * Ref: https://stackoverflow.com/questions/5987600/simple-adding-a-jlabel-to-jpanel 
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelFooter extends JPanel implements ActionListener {
	private JLabel lblText;
	private JButton btnStartGame;
	private static final long serialVersionUID = 1L;

	public PanelFooter() { // CONSTRUCTOR #1
		// invoking constructor(String text, String buttonLabel)
		this("Arrange all Vessels & click here: ", " START GAME ");
	}

	public PanelFooter(String text) { // CONSTRUCTOR #2
		this(text, null);
	}

	public PanelFooter(String text, String buttonLabel) { // CONSTRUCTOR #3
		// set the layout in the constructor
		super(new FlowLayout(FlowLayout.CENTER));
		ActionListener ev = null;

		// create individual component-instances and initialize
		lblText = new JLabel(text); // create label with the text
		btnStartGame = new JButton(buttonLabel); // create button with the label

		// add components to the JPanel
		this.add(lblText);
		this.add(btnStartGame);
		this.btnStartGame.addActionListener(this);
	}

	public static void main(String[] args) {
		System.out.print("Run this Panel from App.Java \n...End...");
	}

	public void actionPerformed(ActionEvent ev) {
		JButton clicked = (JButton) ev.getSource();
		System.out.println("---------------------------\nclicked in PanelFooter.java  \n" + ev.toString() + "\n");
	}

}
