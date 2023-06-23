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
import model.dao.NoSuchRowException;
import model.entity.List;
import model.entity.ListEntry;
import model.entity.MyUser;

public class SelectionAdapterListDelete extends SelectionAdapter {
	private InitialContext ctx;
	private Shell parent; 
	private List selectedList;
	
	
	public SelectionAdapterListDelete(Shell parent,List selectedList){
		this.parent = parent; 
		this.selectedList = selectedList;
		
	}
	
	public void widgetSelected(SelectionEvent e) {
		
		try {
			ctx = new InitialContext();
			ListDaoInterface listDaoInterface = (ListDaoInterface) ctx
					.lookup("ToDo_EJB/ListDao!model.dao.ListDaoInterface");
			
			MessageBox msgb_Delete = new MessageBox(parent, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
			msgb_Delete.setMessage("Wollen Sie die Liste:\n" + selectedList.getListName() + "\nwirklich loeschen?");
			int msgb_result = msgb_Delete.open();
			switch(msgb_result) {
			case SWT.YES:
				listDaoInterface.remove(selectedList.getListId());
				break;
			case SWT.NO:
				break;
			}
			
		} catch (NamingException e1) {
			e1.printStackTrace();
			System.out.println("User existiert nicht!");
		} catch (NoSuchRowException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
