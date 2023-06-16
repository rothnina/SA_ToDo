package client;

import org.eclipse.swt.widgets.*;

import model.entity.MyUser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.*;


public class GUI {
	private Display display; 
	private Shell shell; 
	private Label l_username;
	private Text username;
	private Label l_password;
	private Text password;
	private Button login;
	private MyUser user = null;
	
	private Label l_label;
	private Button[] lists;

	public GUI() {
		
		createDisplay();
		createShell();
		createLogin();
		createListArea();
		createListEntryArea();
		createListeners();
		
		//shell.pack();
	}
	private void createDisplay() {
		display = new Display(); 
	}
	
	private void createShell() {
		shell = new Shell(display);
		GridLayout layout = new GridLayout(2, true); 
		shell.setLayout(layout);
		shell.setText("ToDo App");
	}
	
	private void createLogin() {
		l_username = new Label(shell, SWT.CENTER);
		l_username.setText("username: ");
		username = new Text(shell, SWT.SINGLE); 
		
		l_password = new Label(shell, SWT.CENTER);
		l_password.setText("password: ");
		password = new Text(shell, SWT.SINGLE); 
		
		login = new Button(shell, SWT.PUSH);
		login.setText("Login");
		
	}
	public void createListArea() {
		l_label = new Label(shell, SWT.CENTER);
		l_label.setText("Listen");
		l_label.setFont(new Font(display, "Arial", 14, SWT.BOLD));
	}
	
	public void createListEntryArea() {
		
	}
	public void createListeners() {
		login.addSelectionListener(new SelectionAdapterLogin(username, password, user));
		
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
