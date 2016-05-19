package view;

import java.awt.Point;
import java.awt.event.MouseEvent;

import controller.App;
import model.AbstractShape;
import model.tools.Tool;

public abstract class CreationTool extends Tool{
	
	public abstract AbstractShape createFigure();
	
	@Override
	public final void mouseReleased(MouseEvent e) {	
		super.mouseReleased( e );
		Point ptPressed = getPtPressed();
		Point ptRelaesed = getPtRelased();
		if ( ptPressed.equals( ptRelaesed )) {
		}
		else {
			AbstractShape f = createFigure();
			if (f != null) {
				f.setSelected( true );
				App.getInstance().addFigure(f);
			}
		}
	}
}
