package client;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;


public class GUI {
	private Display display; 
	private Shell shell; 

	public GUI() {
		
		createDisplay();
		createShell();
	}
	private void createDisplay() {
		display = new Display(); 
	}
	
	private void createShell() {
		shell = new Shell(display);
		GridLayout layout = new GridLayout(2, true); 
		shell.setLayout(layout);
		shell.setText("Simulation Euro-Lotto");
	}
	
	public void open() {
		shell.open();
		while(!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
