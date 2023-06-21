package model.dao;

import java.util.Collection;

import jakarta.ejb.*;
import jakarta.persistence.*;
import model.entity.List;
import model.entity.UserRights;


// public class MyUserDao implements java.io.Serializable, MyUserDaoInterface { // traditionell

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // = Default-Einstellung, i.e. Server übernimmt TA-Mgmt!
@Remote(UserRightsDaoInterface.class)
public class UserRightsDao implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	public UserRightsDao() {

	}

	public Collection<UserRights> getByMyUser(int myUser) throws NoSuchRowException{
		return em.createQuery("SELECT obj FROM UserRights obj WHERE obj.myUser = ?1", UserRights.class)
				.setParameter(1, myUser).getResultList();
	}
	
	public Collection<UserRights> getByListId(int listId) throws NoSuchRowException{
		return em.createQuery("SELECT obj FROM UserRights obj WHERE obj.listId = ?1", UserRights.class)
				.setParameter(1, listId).getResultList();
	}
	
	public UserRights getByMyUserAndListId(int myUser, int listId) throws NoSuchRowException{
		return em.createQuery("SELECT * FROM UserRights WHERE myUser = ?1 and listId = ?1", UserRights.class)
				.setParameter(1, myUser).setParameter(2, listId).getSingleResult();
	}

	public Collection<UserRights> list(){
		return em.createQuery("SELECT obj FROM UserRights obj", UserRights.class).getResultList();
	}

	public void save(UserRights arg) {
		UserRights obj = em.find(UserRights.class, arg.getRight());
		if (obj == null)
			em.persist(arg); // insert
		else
			em.merge(arg); // update
	}

	public void remove(int primaryKey) throws NoSuchRowException{
		UserRights obj = em.find(UserRights.class, primaryKey);
		if (obj != null)
			em.remove(obj); // insert
		else
			throw new NoSuchRowException();
	}

	

	@Remove
	public void close() {
	}
}