package OthelloAI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public final class UI extends JFrame implements Observer{
	private final OthelloButton[][] pieces;
	private final Board board;
	private final int dimension;
		
	public UI(Board b) {
		super("Othello GUI");
		board = b;
		dimension = b.getDimension();
		setLayout(new GridLayout(dimension, dimension));
		pieces = new OthelloButton[dimension][dimension];
		for(int y=0; y < dimension; y++){
			for(int x=0; x < dimension; x++){
				pieces[x][y] = new OthelloButton(b.getPosition(x, y));
				pieces[x][y].addActionListener(new ButtonActionListener(x,y,b,this));
				this.add(pieces[x][y]);
			}
		}
		pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		for(int y=0; y < dimension; y++){
			for(int x=0; x < dimension; x++){
				pieces[x][y].setPiece(board.getPosition(x,y));
			}
		}
		//this.repaint();
	}
}
