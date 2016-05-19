package model.tools;

import java.awt.Point;
import java.awt.event.MouseEvent;

import view.MouseAdapter;

//strategy

public abstract class Tool extends MouseAdapter{
	
	private Point ptPressed;
	private Point ptRelased;
	
	@Override
	public void mousePressed(MouseEvent e) {
		ptPressed = e.getPoint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		ptRelased = e.getPoint();
	}

	protected Point getPtPressed() {
		return ptPressed;
	}

	protected Point getPtRelased() {
		return ptRelased;
	}
}
