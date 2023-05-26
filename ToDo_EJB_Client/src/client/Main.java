package client;

//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.dao.*;
import model.entity.*;

public class Main {

	public static void main(String[] args) {

		InitialContext ctx;
		try {
			ctx = new InitialContext();
			MyUserDaoInterface myUserDaoInterface = (MyUserDaoInterface) ctx
					.lookup("ToDo_EJB/MyUserDao!model.dao.MyUserDaoInterface");

			for (MyUser obj : myUserDaoInterface.list()) {
				System.out.println("obj = " + obj);
				System.out.println("\tuserRights = " + obj.getUserRights());
				System.out.println("\tlist = " + obj.getList());
			}
			myUserDaoInterface.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
