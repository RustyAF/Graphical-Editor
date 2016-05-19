package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



import java.util.Stack;

import util.BoundBox;
import util.DocumentListener;
import util.DocumentListener.DocumentEvent;
import util.Command;

public class Drawing {
	
	private List<AbstractShape> shapes;
	private Stack<Command> redoStack;
	private Stack<Command> undoStack;
	private List<DocumentListener> listeners;
	
	public Drawing() {
		shapes = new LinkedList<>();
		listeners = new LinkedList<>();
		undoStack = new Stack<Command>();
		redoStack = new Stack<Command>();
	}

	public void add(final AbstractShape s) {
		assert s != null : "Shape must not be null";
		shapes.add(s);
		undoStack.clear();
		notifyListeners( DocumentEvent.ADDED );
	}
	
	public void add(final AbstractShape shape, final int index) {
		shapes.add(index, shape);
		notifyListeners( DocumentEvent.ADDED );
	}

	public void remove(final AbstractShape shape) {
		shapes.remove(shape);
		notifyListeners( DocumentEvent.REMOVED );
		Delete delete = new Delete();
		delete.delete(shape);
		delete.execute();
	}
	
	public int countSelected() {
		int count = 0;
		for (AbstractShape s : shapes) {
			if (s.isSelected()) {
				count++;
			}
		}
		return count;
	}
	
	public void undo() {
		if ( undoStack.isEmpty())
		{
			return;
		}
		else{
		 Command c = undoStack.pop();
		 c.unexecute();
		 redoStack.push(c);
		}
		notifyListeners( DocumentEvent.ADDED);
	}
	public void deleteSelected() {
		this.group();
		int index = 0;
		AbstractShape toDelete = null;
		for (Iterator<AbstractShape> it = shapes.iterator(); it.hasNext(); index++) {
			AbstractShape s = it.next();
			if (s.isSelected()) {
				toDelete = s;
				break;
			}
		}
		Command deleteCommand = new DeleteCommand( toDelete , index );
		deleteCommand.execute();
		undoStack.push( deleteCommand );
		notifyListeners( DocumentEvent.REMOVED);
	}
	
	public void deselectAll() {
		for ( AbstractShape f : shapes ) {
			f.setSelected(false);
		}
		notifyListeners(DocumentEvent.DESELECTED);
	}
	
	public void changeColor(Color c) {
		AbstractShape shape = null;
		Color before = null;
		Iterator<AbstractShape> iterator = shapes.iterator();
		AbstractShape itShape = null;
		while(iterator.hasNext()){
			itShape = iterator.next();
			if(itShape.isSelected()) {
				shape = itShape;
				before = shape.getColor();
				break;
			}			
		}
		ChangeColorCommand changeColor = new ChangeColorCommand();
		changeColor.change(c, before);
		shape.setColor(c);
		undoStack.add(changeColor);
		notifyListeners(DocumentEvent.ADDED);
	}
	public void changeGroupColor(Color c) {
		Color before = null;
		LinkedList<AbstractShape> changeColorList = new LinkedList<>();
		AbstractShape shape = null;
		Iterator<AbstractShape> iterator = shapes.iterator();
		AbstractShape itShape = null;
		while(iterator.hasNext()){
			itShape = iterator.next();
			if(itShape.isSelected()) {
				shape = itShape;
				before = shape.getColor();
				changeColorList.add(shape);	
			}
		}
		ChangeGroupColorCommand changeGroupColor = new ChangeGroupColorCommand();
		changeGroupColor.change(c, before);
		for(AbstractShape sh : changeColorList) {
			sh.setColor(c);
		}
		undoStack.add(changeGroupColor);
		notifyListeners(DocumentEvent.ADDED);
	}
	
	public class ChangeColorCommand implements Command {
		Color c,before;
		public void change(Color c, Color former) {
			this.c = c;
			this.before = former;
		}
		@Override
		public void execute() {
			changeColor(c);
		}
		@Override
		public void unexecute() {
			changeColor(before);	
		}
	}
	
	public class ChangeGroupColorCommand implements Command {
			Color c,before;
			public void change(Color c, Color former) {
				this.c = c;
				this.before = former;
			}
			@Override
			public void execute() {
				changeGroupColor(before);
			}
			@Override
			public void unexecute() {
				changeGroupColor(before);	
			}
		}
	
	public class  DeleteCommand implements Command {
		int index;
		AbstractShape deletedFigure;
		public  DeleteCommand( AbstractShape s, int index ) {
				deletedFigure = s;
				this.index = index;
		}
		@Override
		public void execute() {
			shapes.remove(deletedFigure);
		}
		@Override
		public void unexecute() {	
			shapes.add(index, deletedFigure);
		}	
	}
	
	public class Delete implements Command {	
		AbstractShape delShape;
		public void delete(AbstractShape del) {
			this.delShape = del;
		}
		@Override
		public void execute() {
			shapes.remove(delShape);
		}
		@Override
		public void unexecute() {
			shapes.add(delShape);
		}
	}
	
	public class GroupCommand implements Command {
		AbstractShape selectedFigure;
		public GroupCommand( AbstractShape f ) {
            for (Iterator<AbstractShape> it = shapes.iterator(); it.hasNext();) {
                AbstractShape s = it.next();
                if(s.isSelected()) {
                }
            }
        }
		@Override
		public void execute() {
		}
		@Override
		public void unexecute() {
		}
	}

    public class MoveCommand implements Command {
        int dx, dy;
        @Override
        public void execute() {
        }
        @Override
        public void unexecute() {
        }
    }
    
	public void redo() {
		if ( redoStack.isEmpty()){
			return;
		}
		else{
			Command c = redoStack.pop();
			c.execute();
			undoStack.push(c);
		}
		notifyListeners( DocumentEvent.ADDED);
	}
	
	public void addListener( final DocumentListener dl ) {	
		assert dl != null;
		listeners.add( dl );
	}
	
	private void notifyListeners( final DocumentEvent de ) {
		for ( DocumentListener dl : listeners ) {
			dl.documentEvent( de );
		}
	}
	
	public void group() {
		assert countSelected() > 1 : "Number of selected figures must be > 1";
		List<AbstractShape> selected = new LinkedList<>();
		for (Iterator<AbstractShape> it = shapes.iterator(); it.hasNext();) {
			AbstractShape s = it.next();
			if (s.isSelected()) {
				selected.add(s);
				it.remove();
			}
		}
		AbstractShape mGroup = new Group(selected);
		this.deselectAll();
		mGroup.setSelected(true);
		shapes.add( mGroup );
		notifyListeners( DocumentEvent.GROUP );
	}

	public void unGroup() {
		assert countSelected() > 0 : "Number of selected figures must be > 0";
		for (AbstractShape s : shapes) {
			if (s.isSelected()) {
				int before = shapes.size();
				for (Iterator<AbstractShape> it = s.iterator(); it.hasNext();) {
					shapes.add(it.next());
				}
				if (before < shapes.size()) {
					shapes.remove(s);
					notifyListeners( DocumentEvent.UNGROUP );
					break;
				}
			}
		}
	}

	public void paint(final Graphics2D g) {
		for (Shape s : shapes) {
			s.paint(g);
		}
	}
	
	public void select( Point pt ) {
		Iterator<AbstractShape> it = shapes.iterator();
		while( it.hasNext() ) {
			AbstractShape f = it.next();
			if ( f.contains( pt ) ) {
				f.setSelected( true );
			}
		}
		notifyListeners(DocumentEvent.DESELECTED);
	}
	
	public void select(BoundBox sBox) {
		assert (sBox != null);
		assert (sBox.isNormalized());
		BoundBox bBox = sBox.normalize();
		for ( AbstractShape s : shapes ) {
			if ( s.contained( bBox ) ) {
				s.setSelected( true );
			}
		}
		notifyListeners(DocumentEvent.DESELECTED);
	}
	
	public void move( int dx, int dy) {
		for ( AbstractShape s : shapes ) {
			if ( s.isSelected() ){
				s.move(dx, dy);
				for (Iterator<AbstractShape> it = s.iterator(); it.hasNext();) {
					it.next().move(dx, dy);
				}
			}
		}
		notifyListeners( DocumentEvent.MOVED);
	}
}
