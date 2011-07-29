package othelloAI;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class OthelloButton extends JButton {

	private static final ImageIcon background = 
		new ImageIcon("C:/Users/yuki/workspace/OthelloAI/icons/bkgrd.gif");
	private static final ImageIcon whitePiece = 
		new ImageIcon("C:/Users/yuki/workspace/OthelloAI/icons/white.gif");
	private static final ImageIcon blackPiece = 
		new ImageIcon("C:/Users/yuki/workspace/OthelloAI/icons/black.gif");
	
	public OthelloButton(){		
		this(Board.Piece.EMPTY);
	}
	public OthelloButton(Board.Piece p){
		super();
		setPiece(p);
		setPreferredSize(new java.awt.Dimension(64,64));
	}
		
	public void setPiece(Board.Piece p){
		switch(p){
		case WHITE: this.setIcon(whitePiece);break;
		case BLACK: this.setIcon(blackPiece);break;
		case EMPTY: this.setIcon(background);break;		
		}
	}

}
