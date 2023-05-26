package model.dao;

import java.util.ArrayList;

import java.util.Collection;

import jakarta.ejb.*;
import jakarta.persistence.*;
import model.entity.MyUser;

// public class MyUserDao implements java.io.Serializable, MyUserDaoInterface { // traditionell

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // = Default-Einstellung, i.e. Server übernimmt TA-Mgmt!
@Remote(MyUserDaoInterface.class)
public class MyUserDao implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	public MyUserDao() {
	}

	public MyUser getByPrimaryKey(int primaryKey) throws NoSuchRowException {
		MyUser obj = em.find(MyUser.class, primaryKey);
		if (obj == null)
			throw new NoSuchRowException();
		return obj;
	}

//	public Collection<MyUser> getMyUserByName(String userName) {
//		Collection<MyUser> users = em.createQuery("SELECT obj FROM MyUser obj", MyUser.class).getResultList();
//		Collection<MyUser> returnList = new ArrayList();
//		for (MyUser ur : users) {
//			if (ur.getUserName().equals(userName)) {
//				returnList.add(ur);
//			}
//		}
//		return returnList;
//	}

	public MyUser getMyUserByName(String userName) {
		return em.createQuery("SELECT obj FROM MyUser obj WHERE obj.userName = 1?", MyUser.class)
				.setParameter(1, userName).getSingleResult();
	}

	public Collection<MyUser> list() {
		return em.createQuery("SELECT obj FROM MyUser obj", MyUser.class).getResultList();
	}

	public void save(MyUser arg) {
		MyUser obj = em.find(MyUser.class, arg.getMyUserId());
		if (obj == null)
			em.persist(arg); // insert
		else
			em.merge(arg); // update
	}

	public void remove(int primaryKey) throws NoSuchRowException {
		MyUser obj = em.find(MyUser.class, primaryKey);
		if (obj != null)
			em.remove(obj); // insert
		else
			throw new NoSuchRowException();
	}

	@Remove
	public void close() {
	}
}