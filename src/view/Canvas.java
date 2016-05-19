package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controller.App;
import model.tools.Tool;
import util.BoundBox;
import util.DocumentListener;

public class Canvas extends JPanel implements DocumentListener {

	private static final long serialVersionUID = 1L;
	private Tool[] tools;
	private Tool currentTool;
	public static final int LCT = 0;
	public static final int RCT = 1;
	public static final int ECT = 2;
	public static final int TCT = 3;
	public static final int SEL = 4;
	public static final int MOV = 5;
	public static final int NT = 6;
	
	public void init() {
		tools = new Tool[ NT ];
		tools [ LCT ] = new LineCreationTool();
		tools [ RCT ] = new RectCreationTool();
		tools [ ECT ] = new ElliCreationTool();
		tools [ TCT ] = new TextCreationTool();
		tools [ SEL ] = new SelectionTool();
		tools [ MOV ] = new MoveTool();
		currentTool = tools[ LCT ];
		addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed( MouseEvent e ) {
				currentTool.mousePressed(e);
			}
			@Override
			public void mouseReleased( MouseEvent e ) {
				currentTool.mouseReleased(e);
			}
		});
		addMouseMotionListener( new MouseAdapter() {
			@Override
			public void mouseDragged( MouseEvent e ) {
				currentTool.mouseDragged( e );
			}
		});
		App.getInstance().addDocumentListener( this );
	}
	
	@Override
	public void documentEvent( final DocumentEvent de ) {
		repaint();
	}
	
	public void drawSelectionBox( BoundBox sBox ){	
		assert sBox != null;
		BoundBox bBox = sBox.normalize();
		Graphics2D g2 = (Graphics2D) getGraphics();
		g2.setXORMode( getBackground() );
		g2.setColor( Color.LIGHT_GRAY );
		g2.setStroke( BoundBox.DASHED );
		g2.drawRect(bBox.x, bBox.y, bBox.width, bBox.height);
		g2.dispose();
	}
	
	@Override
	public void paint( Graphics g ) {	
		super.paint( g );
		App.getInstance().paint( (Graphics2D)g );
	}
	
	public void setCurrentTool( int idx) {
		if ( 0 <= idx && idx <NT){
			currentTool = tools[ idx ];
		}
	}
}
