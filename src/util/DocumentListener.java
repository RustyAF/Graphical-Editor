package util;

// Observer Pattern
public interface DocumentListener {

	public static enum DocumentEvent {
		ADDED, REMOVED, SELECTED, DESELECTED, GROUP, UNGROUP, MOVED, RESIZED, SAVED;
	}
	void documentEvent( DocumentEvent de );
}
