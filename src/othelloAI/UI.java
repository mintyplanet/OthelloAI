package othelloAI;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public final class UI extends JFrame{
	private final OthelloButton[][] pieces;
	private final Board board;
	private final int dimension;
	private final JLabel message;
		
	public UI(Board b, Game game) {
		super("Othello GUI");
		this.setResizable(false);
		board = b;
		dimension = b.getDimension();
		message = new JLabel(board.getCurrentPlayer().toString() + "`s turn.");
		Box box = Box.createVerticalBox();
		
		JPanel boardPanel = new JPanel(new GridLayout(dimension, dimension));
		pieces = new OthelloButton[dimension][dimension];
		for(int y=0; y < dimension; y++){
			for(int x=0; x < dimension; x++){
				pieces[x][y] = new OthelloButton(b.getPosition(x, y));
				pieces[x][y].addActionListener(new ButtonActionListener(x,y,game));
				boardPanel.add(pieces[x][y]);
			}
		}
		add(box);
		box.add(boardPanel);
		
		box.add(message);
		
		pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void draw() {
		for(int y=0; y < dimension; y++){
			for(int x=0; x < dimension; x++){
				pieces[x][y].setPiece(board.getPosition(x,y));
			}
		}
		message.setText(board.getCurrentPlayer().toString() + "`s turn.");
	}
	
	public void setMessage(String string){
		message.setText(string);
		}

	public void togglePause() {
		for(OthelloButton[] obs:pieces){
			for(OthelloButton ob:obs){
				ob.setEnabled(!ob.isEnabled());
			}
		}
	}
}
