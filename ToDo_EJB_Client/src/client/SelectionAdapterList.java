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
 

public class SelectionAdapterList extends SelectionAdapter {
	private InitialContext ctx;
	private Shell parent; 
	private org.eclipse.swt.widgets.List listEntry; 
	private Collection<ListEntry> dbListEntry;
	private MyUser user; 
	private List dbList; 
	private org.eclipse.swt.widgets.List l; 
	private final int listAreaIndex = 5; 
	private org.eclipse.swt.widgets.List listAreaList;

	public SelectionAdapterList(Shell parent,org.eclipse.swt.widgets.List l, org.eclipse.swt.widgets.List listEntry, MyUser user){
		this.parent = parent; 
		this.l = l; 
		this.listEntry = listEntry; 
		this.user = user; 
		System.out.println( "!!!!!!!!!!!!!!!!!!!!!!Konstruktor SelectionAdapter user = " + user);
	}
	
	public void widgetSelected(SelectionEvent e) {
		
		try {
			ctx = new InitialContext();
			Control[] childrenShell = parent.getChildren(); 
			for (Control c : childrenShell)
			{
				System.out.println("c = " + c);
			}
			Group groupListArea = (Group) childrenShell[listAreaIndex];
			Control[] listAreaElements = groupListArea.getChildren(); 
			for (Control obj : listAreaElements)
			{
				System.out.println("obj = " + obj);
			}
			listAreaList = (org.eclipse.swt.widgets.List) listAreaElements[1]; 
			Button btnChangeList = (Button) listAreaElements[3]; 
			Button btnDeleteList = (Button) listAreaElements[4];
			
			ListDaoInterface listDaoInterface = (ListDaoInterface) ctx
					.lookup("ToDo_EJB/ListDao!model.dao.ListDaoInterface");
			ListEntryDaoInterface listEntryDaoInterface = (ListEntryDaoInterface) ctx
					.lookup("ToDo_EJB/ListEntryDao!model.dao.ListEntryDaoInterface");
			dbList = listDaoInterface.getListByName(l.getSelection()[0]); 
			System.out.println("DBList: " + dbList.toString());
			System.out.println("User: "+user.toString());
			dbListEntry = listEntryDaoInterface.getListEntriesFromListByUser(dbList, user);  
			System.out.println("Anzahl Listeinträge: " + dbListEntry.size()); 
			createListItem(dbListEntry);
			
			btnDeleteList.addSelectionListener(new SelectionAdapterListDelete(parent, dbList));
			
			
			
		} catch (NamingException e1) {
			e1.printStackTrace();
			System.out.println("User existiert nicht!");
		}
		
	}
	public void createListItem(Collection<ListEntry> dbList) {
		listEntry.removeAll();
		for (ListEntry listItem : dbList) {
			this.listEntry.add(listItem.getToDo());
		}
	}
	
}
