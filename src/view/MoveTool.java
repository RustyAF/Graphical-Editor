package view;

import java.awt.Point;
import java.awt.event.MouseEvent;

import controller.App;
import model.tools.Tool;

public class MoveTool extends Tool {
	
	private Point ptPressed;
	private Point ptReleased;
	
	@Override
	public void mousePressed( MouseEvent e ) {
		super.mousePressed(e);
	}
	
	@Override
	public void mouseDragged( MouseEvent e ) {
		super.mouseDragged(e);
	}
	
	@Override
	public void mouseReleased( MouseEvent e ) {
		super.mouseReleased(e);
		ptPressed = getPtPressed();
		ptReleased = getPtRelased();
		if ( ptPressed.equals(ptReleased)){
		}
		else {
			App.getInstance().move((int) (ptReleased.getX() - ptPressed.getX()), (int) (ptReleased.getY() - ptPressed.getY()));
		}
	}
}
