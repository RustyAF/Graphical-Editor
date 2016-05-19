package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import model.AbstractShape;
import model.Drawing;
import util.BoundBox;
import util.DocumentListener;
import view.MainFrame;
import factory.DrawingFactory;
import factory.FrameFactory;

public class App {

	private Drawing model;
	private MainFrame view;
	
	private App() {
		model = DrawingFactory.createDrawing();
		view = FrameFactory.createFrame();
	}
	
	private static class AppHelper {
		private static final App instance = new App();
	}
	
	public static App getInstance() {
		return AppHelper.instance;
	}

	private void run() {
		view.setBounds( 10, 10, 840, 680 );
		view.init();
		view.setVisible( true );
	}

	public void paint(Graphics2D g) {
		model.paint( g );
	}
	
	public void deleteSeleted() {
		model.deleteSelected();
	}
	
	public void deselectAll() {
		model.deselectAll();
	}
	
	public void undo() {
		model.undo();
	}
	
	public void redo() {
		model.redo();
	}

	public void group() {	
		model.group();
	}
	
	public void changeColor(Color c) {
		model.changeColor(c);
	}
	
	public void changeGroupColor(Color c) {
		model.changeGroupColor(c);
	}

	public void unGroup() {
		model.unGroup();
	}
	
	public void addDocumentListener( DocumentListener dl ) {
		model.addListener( dl );
	}
	
	public void addFigure (final AbstractShape f) {
		model.add(f);
	}
	
	public static void main(String[] args) {
		App app = App.getInstance();
		app.run();
	}

	public void drawSelectionBox(BoundBox selectionBox) {
		view.drawSelectionBox( selectionBox );
	}

	public void select(Point pt) {
		model.select( pt );
	}

	public void select(BoundBox bBox) {
		model.select( bBox );
	}
	
	public void move(int dx, int dy) {
		model.move( dx, dy );
	}
	
	public void setTool( int t ) {
		view.setTool ( t );
	}
}
