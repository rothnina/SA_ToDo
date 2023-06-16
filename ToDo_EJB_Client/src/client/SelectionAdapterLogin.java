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
	private Text username;
	private Text password;
	private MyUser user;
	private Collection<List> lists;
	
	public SelectionAdapterLogin(Text username, Text password, MyUser user){
		this.username = username;
		this.password = password;
		this.user = user;
		
		
	}
	
	public void widgetSelected(SelectionEvent e) {
		
		MyUserDaoInterface myUserDaoInterface;
		try {
			ctx = new InitialContext();
			myUserDaoInterface = (MyUserDaoInterface) ctx
					.lookup("ToDo_EJB/MyUserDao!model.dao.MyUserDaoInterface");
			MyUser obj = myUserDaoInterface.getMyUserByName(username.getText());
//			System.out.println("User by Username:\n " + username.getText() + ":" + obj);
			
			if (password.getText().equals(obj.getPassword())) {
				System.out.println("Hurra, das Password ist Korrekt/nDer User konnte angemeldet werden");
				user = obj;
			}
			
			ListDaoInterface listDaoInterface = (ListDaoInterface) ctx
					.lookup("ToDo_EJB/ListDao!model.dao.ListDaoInterface");
			lists = listDaoInterface.getListsFromUser(user);
			for (List list : lists) {
				System.out.println("List = " + list);
			}
			
			createListButtons();
			
			
		} catch (NamingException e1) {
			e1.printStackTrace();
			System.out.println("User existiert nicht!");
		}
		
	}
	public void createListButtons() {
		Shell shell = username.getShell();
		for (List l: lists) {
			Button b = new Button(shell, SWT.SINGLE);
			b.setText(l.getListName());
			
		}
	}
	
}
