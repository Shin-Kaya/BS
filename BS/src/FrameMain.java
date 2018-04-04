
/* 
 * @author SHin Kaya (25 March 2018)
 * Ref: https://www.udemy.com/java-swing-complete/learn/v4/t/lecture/104280
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameMain extends JFrame implements ActionListener, MouseListener {
	private PanelHeader panelHeader; // Main frame consists of 3 panels (header)
	private JPanel panelMiddle; // Two matrices side-by-side
	private PanelFooter panelFooter; // Footer with user buttons

	public FrameMain() { // CONSTRUCTOR #1
		this("BattleShip Game");
	}

	public FrameMain(String headingText) { // CONSTRUCTOR #2
		super(headingText);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the open window on exit

		// create individual separate panels
		// HEADING PANEL
		panelHeader = new PanelHeader(); // will contain header for 2 BBoards one for player one for opponent
		this.add(panelHeader, BorderLayout.NORTH);

		// matrices side by side
		// MIDDLE PANEL
		panelMiddle = new JPanel();
		panelMiddle.setLayout(new BorderLayout());
		//
		BGrid gridPlayer = new BGrid();
		panelMiddle.add(gridPlayer, BorderLayout.WEST);
		//

		JLabel labelSpacer = new JLabel("    |    ");
		labelSpacer.setBackground(Color.BLACK);
		// JButton btn = new JButton(" Compile Vessels ");
		// btn.addActionListener(this); // (actionListener);
		// btn.addMouseListener(this); // (mouseListener);

		panelMiddle.add(labelSpacer, BorderLayout.CENTER);

		//
		BGrid gridComputer = new BGrid();
		panelMiddle.add(gridComputer, BorderLayout.EAST);
		//
		this.add(panelMiddle, BorderLayout.CENTER);

		panelFooter = new PanelFooter(); // will contain footer with 3 buttons: PLAYER READY, Computer Ready, (START
		this.add(panelFooter, BorderLayout.SOUTH);

		this.pack(); //
		setVisible(true);
	}

	public static void main(String[] args) {
		System.out.print("Run this Frame from App.Java \n...End...");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton clicked = (JButton) event.getSource();
		System.out.println("clicked : (" + clicked.getClass() + ") " + event.toString());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
		System.out.println("Mouse Enter (" + this.getClass() + ") ");
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
		System.out.println("Mouse Exit (" + this.getClass() + ") ");

	}

	@Override
	public void mousePressed(MouseEvent m) {
		System.out.println("Mouse Pressed (" + this.getClass() + ") ");

	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
		JButton mouseReleased = (JButton) mouse.getSource();
		System.out.print("Mouse released (mouse.toString is: >>>" + mouse.toString() + "<<<");

	}
}
