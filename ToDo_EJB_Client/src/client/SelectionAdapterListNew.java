package client;

import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import model.dao.ListDaoInterface;
import model.dao.ListEntryDaoInterface;
import model.dao.MyUserDaoInterface;
import model.entity.List;
import model.entity.ListEntry;
import model.entity.MyUser;

public class SelectionAdapterListNew extends SelectionAdapter {
	private InitialContext ctx;
	private Shell parent; 
	
	
	public SelectionAdapterListNew(Shell parent,org.eclipse.swt.widgets.List l, org.eclipse.swt.widgets.List listEntry, MyUser user){
		this.parent = parent; 
		
	}
	
	public void widgetSelected(SelectionEvent e) {
		
		try {
			ctx = new InitialContext();
			
			ListDaoInterface listDaoInterface = (ListDaoInterface) ctx
					.lookup("ToDo_EJB/ListDao!model.dao.ListDaoInterface");
			InputDialog dlg = new InputDialog(parent);
			dlg.setText("New List"); 
		    String input = dlg.open();
		    if (input != null) {
		      // TODO save with user as creator
		      System.out.println(input);
		    }
			
		} catch (NamingException e1) {
			e1.printStackTrace();
			System.out.println("User existiert nicht!");
		}
		
	}
	
}
