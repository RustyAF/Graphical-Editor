package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.*;

import controller.App;
import util.BoundBox;
import util.DocumentListener;

public class MainFrame extends JFrame implements DocumentListener {

	private static final long serialVersionUID = 1L;
	JColorChooser chooser = new JColorChooser();
	boolean undoColor = false;
	private Canvas canvas;
	public MainFrame(String title) throws HeadlessException {
		super(title);
		canvas = new Canvas();
		setDefaultCloseOperation( EXIT_ON_CLOSE );
	}

	public void init() {
		canvas.init();
		canvas.setBackground( Color.WHITE );
		add( BorderLayout.CENTER, canvas );
		JMenu file = new JMenu( "File" );
		JMenuItem group = new JMenuItem( "Group" );
		group.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().group();
			}
		});
		JMenuItem unGroup = new JMenuItem( "Ungroup" );
		unGroup.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().unGroup();
			}
		});
		JMenuItem delete = new JMenuItem( "Delete" );
		delete.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().deleteSeleted();
			}
		});
		JMenuItem undo = new JMenuItem( "Undo" );
		undo.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().undo();
			}
		});
		JMenuItem redo = new JMenuItem( "Redo" );
		redo.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().redo();
			}
		});
		JMenuItem selectionTool = new JMenuItem( "Selection Tool" );
		selectionTool.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setTool( Canvas.SEL);
			}
		});
		JMenuItem moveTool = new JMenuItem( "Move Tool" );
		moveTool.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setTool( Canvas.MOV);
			}
		});
		JMenuItem lineTool = new JMenuItem( "Line Tool" );
		lineTool.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setTool( Canvas.LCT );
			}
		});
		JMenuItem ellipseTool = new JMenuItem("Ellipse Tool");
		ellipseTool.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setTool( Canvas.ECT );
			}
		});
		JMenuItem rectangleTool = new JMenuItem("Rectangle Tool");
		rectangleTool.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setTool( Canvas.RCT );
			}
		});
		JMenuItem textTool = new JMenuItem("Text Tool");
		textTool.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().setTool( Canvas.TCT );
			}
		});
		JMenuItem changeColor = new JMenuItem("Change Color");
		changeColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 Color newColor = chooser.showDialog(null,"Pick a Color", Color.BLACK);
				App.getInstance().changeColor(newColor);
			}
		});
		JMenuItem changeGroupColor = new JMenuItem("Change Group Color");
		changeGroupColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 Color newColor = chooser.showDialog(null,"Pick a Color", Color.BLACK);
				App.getInstance().changeGroupColor(newColor);
			}
		});
		JMenu edit = new JMenu( "Edit" );
		edit.add( group );
		edit.add( unGroup );
		edit.add(delete);
		edit.add(undo);
		edit.add(redo);
		edit.add(changeColor);
		edit.add(changeGroupColor);
		JMenu tool = new JMenu( "Tools" );
		tool.add( selectionTool );
		tool.add( moveTool );
		tool.add( lineTool );
		tool.add( ellipseTool );
		tool.add( rectangleTool );
		tool.add( textTool );
		JMenuBar jbar = new JMenuBar();
		jbar.add( file );
		jbar.add( edit );
		jbar.add( tool );
		add( BorderLayout.NORTH, jbar );
		App.getInstance().addDocumentListener( this );
	}
	
	public void drawSelectionBox( BoundBox sB ) {
		canvas.drawSelectionBox( sB );
	}
	
	public void setTool(int t) {
		canvas.setCurrentTool( t );
	}

	@Override
	public void documentEvent(DocumentEvent de) {
		this.setTitle("Editor Gráfico"+"*");
	}
}
