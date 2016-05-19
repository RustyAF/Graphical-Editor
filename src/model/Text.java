package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import util.BoundBox;

public class Text extends AbstractShape {

	private static Font monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 36);
	private String text;
	
	public Text(BoundBox boundBox, Color color) {
		super(boundBox, color);
	}

	@Override
	protected void doPaint(Graphics2D g) {
		g.setColor( getColor() );
		g.setFont( monoFont );
		BoundBox bbox = getBoundBox();
		FontMetrics fm = g.getFontMetrics();
		g.drawString(text, bbox.x, bbox.y);
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
