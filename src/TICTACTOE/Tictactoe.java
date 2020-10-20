package TICTACTOE;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tictactoe extends JFrame implements ActionListener{
	
	public static int BOARD_SIZE = 3;
	public static enum GAME_STATUS{Incomplete,XWins,ZWins,Tie}
	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean crossTurn = true;
	
	public Tictactoe() {
		super.setTitle("TICTACTOE");
		super.setSize(800,800);
		GridLayout Grid = new GridLayout(BOARD_SIZE,BOARD_SIZE);
		super.setLayout(Grid);
		Font font = new Font("Comic Sans",1,150);

		for(int row=0;row<BOARD_SIZE;row++) {
			for(int col=0;col<BOARD_SIZE;col++) {
				JButton button = new JButton("");
				buttons[row][col] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		
		super.setResizable(false);
		super.setVisible(true);
		JOptionPane.showMessageDialog(this, "Welcome! It's a 2 player game, wherein each of the players will have their turns simultaneously.");
		JOptionPane.showMessageDialog(this, "To select a particular box, simply click on it.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedButton = (JButton)e.getSource();
		makeMove(clickedButton);
		
		GAME_STATUS gs = getGameStatus();
		if(gs==GAME_STATUS.Incomplete) {
			return;
		}
		
		declareWinner(gs);
		
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?");
		
		if(choice==JOptionPane.YES_OPTION) {
			for(int row=0;row<BOARD_SIZE;row++) {
				for(int col=0;col<BOARD_SIZE;col++) {
					buttons[row][col].setText("");
					
				}
			}
			crossTurn = true;
		}else if(choice==JOptionPane.NO_OPTION) {
			super.dispose();
		}
	}
	
	private void declareWinner(GAME_STATUS gs) {
		if(gs == GAME_STATUS.XWins) {
			JOptionPane.showMessageDialog(this, "X WINS THE GAME");
		}else if(gs == GAME_STATUS.ZWins) {
			JOptionPane.showMessageDialog(this, "0 WINS THE GAME");
		}else JOptionPane.showMessageDialog(this, "IT IS A TIE");
	}
	
	private void makeMove(JButton clickedButton) {
		String btntxt = clickedButton.getText();
		
		if(btntxt.length()>0) {
			JOptionPane.showMessageDialog(this, "INVALID MOVE!");
		}
		
		if(btntxt.length()==0) {
			if(crossTurn) {
				clickedButton.setText("X");
			}else {
				clickedButton.setText("0");
			}
			crossTurn = !crossTurn;
		}
		
	}
	
	private GAME_STATUS getGameStatus() {
		String text1="",text2="";
		int row,col;

		//HORIZONTAL CHECK
		for(row=0;row<BOARD_SIZE;row++) {
			for(col=0;col<BOARD_SIZE-1;col++) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col+1].getText();
				
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
			}
			if(col==BOARD_SIZE-1) {
				if(text1.equals("X"))
				  return GAME_STATUS.XWins;
				else return GAME_STATUS.ZWins;
			}
		}
		
		//VERTICAL CHECK
		for(col=0;col<BOARD_SIZE;col++) {
			for(row=0;row<BOARD_SIZE-1;row++) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row+1][col].getText();
				
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
			}
			if(row==BOARD_SIZE-1) {
				if(text1.equals("X"))
				  return GAME_STATUS.XWins;
				else return GAME_STATUS.ZWins;
			}
		}
		
		//LEFT DIAGONAL
		for(row=0,col=0;row<BOARD_SIZE-1;row++,col++) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row+1][col+1].getText();
			if(!text1.equals(text2) || text1.length()==0) {
				break;
			}
		}
		
		if(row==BOARD_SIZE-1) {
			if(text1.equals("X"))
			  return GAME_STATUS.XWins;
			else return GAME_STATUS.ZWins;
		}
		
		
		//RIGHT DIAGONAL
		for(row=0,col=BOARD_SIZE-1;row<BOARD_SIZE-1;row++,col--) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row+1][col-1].getText();
			if(!text1.equals(text2) || text1.length()==0) {
				break;
			}
		}
		
		if(row==BOARD_SIZE-1) {
			if(text1.equals("X"))
			  return GAME_STATUS.XWins;
			else return GAME_STATUS.ZWins;
		}
		
		//INCOMPLETE
		for(row=0;row<BOARD_SIZE;row++) {
			for(col=0;col<BOARD_SIZE;col++) {
				text1=buttons[row][col].getText();
				if(text1.length()==0) {
					return GAME_STATUS.Incomplete;
				}
			}
		}
		
		//TIE
		return GAME_STATUS.Tie;

	}

}
