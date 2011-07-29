package othelloAI;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public final class ButtonActionListener extends Observable implements ActionListener {

	private final int x, y;
	@Override
	public void actionPerformed(ActionEvent e) {
		setChanged();
		notifyObservers(new Point(x, y));

	}
	public ButtonActionListener(int x, int y, Game game){
		super();
		this.addObserver(game);
		this.x = x;
		this.y = y;
	}

}
