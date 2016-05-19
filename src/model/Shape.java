package model;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Shape {

	void paint( Graphics2D g );
	Color getColor();
	void setColor(Color c);
}
