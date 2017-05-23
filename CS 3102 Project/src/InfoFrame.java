import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;

/**
 * InfoFrame.java
 * 
 * This class provides a dialog window for status information about the game to
 * be displayed. It is used by creating an instance of the object and then
 * calling println and print just as you would with System.out.
 * 
 * See ZombieSurvival.java for examples.
 * 
 * @author Mark Sherriff
 * 
 */
public class InfoFrame {

	private JTextArea textArea = null;
	private JPanel panel;
	private JScrollPane scroll;
	private mainPart field;
	private JFrame frame;
	private Scanner scr;

	public InfoFrame(mainPart field_) {

		field = field_;
		frame = new JFrame();
		frame.setSize(600, 300);
		frame.setLocation(850, 30);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		scroll = new JScrollPane(textArea);
		panel.add(scroll, BorderLayout.CENTER);

		frame.getContentPane().add(panel);

		frame.setVisible(true);
	}
	
	public ArrayList<Boolean> beforeStart() throws Exception{
		
		ArrayList<Boolean> answers = new ArrayList<Boolean>();

		
		JOptionPane.showMessageDialog(null,"Hello! Welcome to the puzzle solver!");
		
		boolean invalid = true;
		boolean ownPuzzle = false;
		boolean multiColor = false;
		boolean extraPiece = false;
		String ans;

		do {
			ans = JOptionPane.showInputDialog("Are you going construct your own puzzle? (Y or N) \n Please input no, coz I haven't implement this part yet...");
			if (ans.length() == 1 && (ans.charAt(0) == 'y' || ans.charAt(0) =='Y')) {
				ownPuzzle = true;
				invalid = false;
			} else if (ans.length() == 1 && (ans.charAt(0) == 'n' || ans.charAt(0) =='N')) {
				invalid = false;
			} else {
				JOptionPane.showMessageDialog(null,"Please input a valid answer!");
			}
		} while (invalid);

		
		if (ownPuzzle) {
			JOptionPane.showMessageDialog(null,"Now you are going to construct your own puzzle! ");
			JOptionPane.showMessageDialog(null,"Before we start, please answer the following questions.");

			invalid = true;
			
			do {
				ans = JOptionPane.showInputDialog("Is your puzzle multicolored? (Y or N)");
				if (ans.length() == 1 && (ans.charAt(0) == 'y' || ans.charAt(0) =='Y')) {
					ownPuzzle = true;
					invalid = false;
				} else if (ans.length() == 1 && (ans.charAt(0) == 'n' || ans.charAt(0) =='N')) {
					invalid = false;
				} else {
					JOptionPane.showMessageDialog(null,"Please input a valid answer!");
				}
			} while (invalid);
			
			invalid = true;
			
			do {
				ans = JOptionPane.showInputDialog("Is your puzzle going to have extra pieces? (Y or N)");
				if (ans.length() == 1 && (ans.charAt(0) == 'y' || ans.charAt(0) =='Y')) {
					ownPuzzle = true;
					invalid = false;
				} else if (ans.length() == 1 && (ans.charAt(0) == 'n' || ans.charAt(0) =='N')) {
					invalid = false;
				} else {
					JOptionPane.showMessageDialog(null,"Please input a valid answer!");
				}
			} while (invalid);
		}

		answers.add(ownPuzzle);
		answers.add(multiColor);
		answers.add(extraPiece);
		
		return answers;				
	}
	
	public void puzzleConstruct() {
		
	}
	
	public String readPuzzle() {
		JList list = new JList(new String[] {"checkerboard", "pentominoes8*8 four missing offset near middle",
				"IQ creator", "lucky13", "pentominoes 3*20", "pentominoes 4*15", "pentominoes 5*12",
				"pentominoes 6*10", "pentominoes 8*8 corner missing", "pentominoes 8*8 four missing diagonal",
				"pentominoes 8*8 four missing near corners", "pentominoes 8*8 four near middle",
				"pentominoes 8*8 four missing offset near corners", "pentominoes 8*8 middle missing",
				"pentominoes 8*8 side missing", "thirteenhole", "partial corss"});
		JOptionPane.showMessageDialog(
		  null, list, "Multi-Select Example", JOptionPane.PLAIN_MESSAGE);
		
		return Arrays.toString(list.getSelectedIndices());
	}
	
	public void println(String str) {
		textArea.append(str + '\n');
	}

	public void print(String str) {
		textArea.append(str);
	}

	public int continueOn() {
		int result = JOptionPane.showConfirmDialog(null,"Would you like to continue to find all other results? Caution: This may take forever");
		return result;
	}

	public boolean afterFirst(boolean b) {
		if(b) {
			int result = JOptionPane.showConfirmDialog(null,"Hooray! We found one solution! Would you like to see the graph of the solution?");
			if (result == 0)
				return true;
			else
				return false;
		} else {
			JOptionPane.showMessageDialog(null,"Sorry! There is no solution...");
			exit();
		}
		return false;
	}
	
	public void exit() {
		JOptionPane.showMessageDialog(null,"Thank you for using the puzzle solver! Wish you have a wonderful Day!");
		System.exit(-1);
	}

	public void allfinish(int allpiece) {
		println("Thanks for using puzzle solver.");
		if(allpiece == 1) {
			JOptionPane.showMessageDialog(null,"Sorry there is only one solution to this puzzle");
			exit();
		} else {
			String output1 = "Great! There are " + (allpiece - 1) + " more solution(s) to this puzzle! We totally found " + allpiece + " solutions!";
			JOptionPane.showMessageDialog(null,output1);
			exit();
		}
	}

}
