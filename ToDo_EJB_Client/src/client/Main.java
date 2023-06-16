package client;

import java.util.Date;

//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.dao.*;
import model.entity.*;

public class Main {

	public static void main(String[] args) {

		InitialContext ctx;
		try {
			ctx = new InitialContext();
			// Testen MyUser
			MyUserDaoInterface myUserDaoInterface = (MyUserDaoInterface) ctx
					.lookup("ToDo_EJB/MyUserDao!model.dao.MyUserDaoInterface");
			
			ListDaoInterface listDaoInterface = (ListDaoInterface) ctx
					.lookup("ToDo_EJB/ListDao!model.dao.ListDaoInterface");
			
			ListEntryDaoInterface listEntryDaoInterface = (ListEntryDaoInterface) ctx
					.lookup("ToDo_EJB/ListEntryDao!model.dao.ListEntryDaoInterface"); 
			
			UserRightsDaoInterface userRightsDaoInterface = (UserRightsDaoInterface) ctx
					.lookup("ToDo_EJB/UserRightsDao!model.dao.UserRightsDaoInterface");
			
			for (UserRights ur : userRightsDaoInterface.list()) {
				System.out.println("UserRight = " + ur);
				System.out.println("UserRight = " + ur.getList());
				
			}

			for (List list : listDaoInterface.list()) {
				System.out.println("List = " + list);
			}
			
			for (ListEntry listEntry : listEntryDaoInterface.list()) {
				System.out.println("ListEntry = " + listEntry);
			}

			for (MyUser obj : myUserDaoInterface.list()) {
				System.out.println("obj = " + obj);
				System.out.println("\tuserRights = " + obj.getUserRights());
				System.out.println("\tlist = " + obj.getList());
			}
			// get User by Id
			MyUser obj = myUserDaoInterface.getByPrimaryKey(1);
			System.out.println("UserById:\n  1 = " + obj);

			// get User by Username
			obj = myUserDaoInterface.getMyUserByName("h");
			System.out.println("User by Name:\n  h = " + obj);

			// save a new User
//			obj = new MyUser("Sara", "Pech", new Date(06 - 06 - 2023), new Date(06 - 06 - 2023), null, "SP",
//					"SPassword");
//			System.out.println("UserId from new User: " + obj.getMyUserId());
//			myUserDaoInterface.save(obj);
//			for (MyUser o : myUserDaoInterface.list()) {
//				System.out.println("obj = " + o);
//			}

			myUserDaoInterface.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (NoSuchRowException e) {
			e.printStackTrace();
		}
		
		GUI gui = new GUI(); 
		gui.open(); 

	}

}
