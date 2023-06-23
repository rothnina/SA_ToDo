package client;

import org.eclipse.swt.widgets.*;

import model.dao.ListDaoInterface;
import model.entity.MyUser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.*;


public class GUI {
	private Display display; 
	private Shell shell; 
	private List list; 
	private List listEntry;
	private Label l_username;
	private Text username;
	private Label l_password;
	private Text password;
	private Button login;
	public MyUser user;
	
	private Label l_label;
	private Button btnListNew, btnListChange, btnListDelete; 
	private Button btnListEntryNew, btnListEntryChange, btnListEntryDelete; 

	public GUI() {
		
		createDisplay();
		createShell();
		createLogin();
		createListArea();
		createListEntryArea();
		createListeners();
		
		shell.pack();
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
	    GridData data = new GridData();
	    data.horizontalSpan = 2; 
		login.setLayoutData(data);
		
		login.setText("Login");
		
	}
	public void createListArea() {
		Group listGroup = new Group(shell, SWT.LEFT); 
		RowLayout layout = new RowLayout(SWT.VERTICAL); 
		listGroup.setLayout(layout);
		
		l_label = new Label(listGroup, SWT.CENTER);
		l_label.setText("Listen");
		l_label.setFont(new Font(display, "Arial", 14, SWT.BOLD));

		list = new List(listGroup, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL); 
		list.setLayoutData(new RowData(240, 100));
		btnListNew = new Button(listGroup, SWT.PUSH); 
		btnListNew.setText("new");
		btnListChange = new Button(listGroup, SWT.PUSH); 
		btnListChange.setText("change");
		btnListDelete = new Button(listGroup, SWT.PUSH); 
		btnListDelete.setText("delete");
	}
	
	public void createListEntryArea() {
		Group listEntryGroup = new Group(shell, SWT.LEFT); 
		RowLayout layout = new RowLayout(SWT.VERTICAL); 
		listEntryGroup.setLayout(layout);
		
		l_label = new Label(listEntryGroup, SWT.CENTER);
		l_label.setText("Listeneinträge");
		l_label.setFont(new Font(display, "Arial", 14, SWT.BOLD));

		listEntry = new List(listEntryGroup, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL); 
		listEntry.setLayoutData(new RowData(240, 100));
		btnListEntryNew = new Button(listEntryGroup, SWT.PUSH); 
		btnListEntryNew.setText("new");
		btnListEntryChange = new Button(listEntryGroup, SWT.PUSH); 
		btnListEntryChange.setText("change");
		btnListEntryDelete = new Button(listEntryGroup, SWT.PUSH); 
		btnListEntryDelete.setText("delete");
	}
	
	public void createListeners() {
		
		login.addSelectionListener(new SelectionAdapterLogin(shell, username, password, user));
		
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
