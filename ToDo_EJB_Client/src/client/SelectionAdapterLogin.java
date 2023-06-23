package client;

import java.util.Collection;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

import model.dao.ListDaoInterface;
import model.dao.MyUserDaoInterface;
import model.entity.List;
import model.entity.MyUser;

public class SelectionAdapterLogin extends SelectionAdapter {
	private InitialContext ctx;
	private Shell parent; 
	private Text username;
	private Text password;
	private MyUser user;
	private Collection<List> dbList;
	private final int listAreaIndex = 5; 
	private final int listEntryAreaIndex = 6; 
	private org.eclipse.swt.widgets.List listAreaList; 
	private org.eclipse.swt.widgets.List listEntryAreaList; 
	
	public SelectionAdapterLogin(Shell parent,  Text username, Text password, MyUser user){
		this.parent = parent;  
		this.username = username;
		this.password = password;
		this.user = user;
	}
	
	public void widgetSelected(SelectionEvent e) {
		
		MyUserDaoInterface myUserDaoInterface;
		Control[] childrenShell = parent.getChildren(); 
		for (Control c : childrenShell)
		{
			System.out.println("c = " + c);
		}
		Group groupListArea = (Group) childrenShell[listAreaIndex];
		Group groupListEntryArea = (Group) childrenShell[listEntryAreaIndex]; 
		Control[] listAreaElements = groupListArea.getChildren(); 
		Control[] listEntryAreaElements = groupListEntryArea.getChildren();
		for (Control obj : listAreaElements)
		{
			System.out.println("obj = " + obj);
		}
		listAreaList = (org.eclipse.swt.widgets.List) listAreaElements[1]; 
		Button btnNewList = (Button) listAreaElements[2]; 
		listEntryAreaList = (org.eclipse.swt.widgets.List) listEntryAreaElements[1]; 
		try {
			ctx = new InitialContext();
			myUserDaoInterface = (MyUserDaoInterface) ctx
					.lookup("ToDo_EJB/MyUserDao!model.dao.MyUserDaoInterface");
			MyUser obj = myUserDaoInterface.getMyUserByName(username.getText());
			System.out.println("User by Username:\n " + username.getText() + ":" + obj);
			
			if (password.getText().equals(obj.getPassword())) {
				System.out.println("Hurra, das Password ist Korrekt/nDer User konnte angemeldet werden");
				user = obj;
				password.setText(""); 
			}

			// ....
			ListDaoInterface listDaoInterface = (ListDaoInterface) ctx
					.lookup("ToDo_EJB/ListDao!model.dao.ListDaoInterface");
			dbList = listDaoInterface.getListsFromUser(user);
			for (List list : dbList) {
				System.out.println("List = " + list);
			}
			System.out.println("ListAnzahl" + dbList.size());
			createListItem(dbList);
			
			
		} catch (NamingException e1) {
			e1.printStackTrace();
			System.out.println("User existiert nicht!");
		}
		System.out.println(user.toString());
		
		listAreaList.addSelectionListener(new SelectionAdapterList(parent, listAreaList, listEntryAreaList, user));
		btnNewList.addSelectionListener(new SelectionAdapterListNew(parent, listAreaList, listEntryAreaList, user));
		
	}
	public void createListItem(Collection<List> dbList) {
		listAreaList.removeAll();
		for (List listItem : dbList) {
			this.listAreaList.add(listItem.getListName());
		}
	}
	
}
