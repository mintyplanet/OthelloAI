package OthelloAI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import OthelloAI.Board.Piece;

public final class ButtonActionListener extends Observable implements ActionListener {

	private final int x, y;
	private Board board;
	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.print(x);
		System.out.print(y);
		System.out.println(board.getPosition(x, y));
		board.setPosition(x,y, board.isHumansTurn()?Piece.WHITE:Piece.BLACK);
		setChanged();
		notifyObservers();

	}
	public ButtonActionListener(int x, int y, Board b, UI ui){
		super();
		this.addObserver(ui);
		this.x = x;
		this.y = y;
		this.board = b;
	}

}
