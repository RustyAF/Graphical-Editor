package factory;

import view.MainFrame;

public class FrameFactory {
	
	public static MainFrame createFrame() {	
		return new MainFrame( "Editor Gráfico" );
	}
}

