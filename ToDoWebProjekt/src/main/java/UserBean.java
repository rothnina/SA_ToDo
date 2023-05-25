import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import model.dao.NoSuchRowException;

import model.entity.List;
import model.entity.ListEntry;
import model.entity.MyUser;
import model.entity.UserRights;


@ManagedBean(name = "MyUserBean", eager = true)

@SessionScoped 
public class UserBean implements Serializable {
	private static final long serialVersionUID = 5064399780143563858L;

	public static final String EJBName = "java:global/AuftragsbearbeitungEJB/MyUserManager!session.MyUserManagerInterface";

	@EJB(mappedName = EJBName)
	private MyUserManagerInterface userManager;

	public MyUser findByPrimaryKey(int primaryKey) {
		try {
			return userManager.findByPrimaryKey(primaryKey);
		} catch (NoSuchRowException e) {
			String message = "Failed to retrieve data from webservice: " + e.getMessage();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
		}
		return null;
	}

	public Collection<MyUser> findByPrimaryKeyList(int primaryKey) {
		Collection<MyUser> returnList = new ArrayList<MyUser>();
		returnList.add(findByPrimaryKey(primaryKey));
		return returnList;
	}

	public Collection<MyUser> findByName(String name) {
		try {
			return userManager.findByName(name);
		} catch (NoSuchRowException e) {
			String message = "Failed to retrieve data from webservice: " + e;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
		}
		return null;
	}

	public Collection<MyUser> findByAliasNameAndPasswordList(String aliasName, String password) {
		Collection<MyUser> returnList = new ArrayList<MyUser>();
		returnList.add(findByAliasNameAndPassword(aliasName, password));
		return returnList;
	}

	public MyUser findByAliasNameAndPassword(String aliasName, String password) {
		try {
			return userManager.findByAliasNameAndPassword(aliasName, password);
		} catch (NoSuchRowException e) {
			String message = "Failed to retrieve data from webservice: " + e;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
		}
		return null;
	}

	public String save(String nextSite) throws Exception {
		MyUser MyUser = getMyUser();
		System.out.println("MyUserManagedBean.save MyUser = " + MyUser);
		System.out.println("\n\n---------------------------\n\nMyUserManagedBean.save MyUser = " + MyUser + "\n\n");
		String message = "Failed to save data in webservice: ";
		if (MyUser == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
		} else
			try {
				userManager.save(MyUser);
			} catch (Exception e) {
				throw e;
			}
		return nextSite;
	}

	public Collection<MyUser> list() {
		return userManager.list();
	}

	public Collection<MyUser> listComplement(int primaryKey) {
		return userManager.listComplement(primaryKey);
	}

	
	
	public UserBean() {
	}

	public UserBean(//...) {
		//TODO add this.x = x
	}

	//TODO add all getter and setter 

	//TODO add toString method
}

