package model.dao;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.*;
import model.entity.MyUser;

public class MyUserDao {

	private EntityManager em;

	public MyUserDao() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceToDo");
		em = emf.createEntityManager();
	}

	public MyUser getByPrimaryKey(int primaryKey) throws NoSuchRowException {
		// zu erzeugendes Statement:
		// select * from sportler where snr = ? (Prepared Statement)
		MyUser obj = em.find(MyUser.class, primaryKey);
		if (obj == null)
			throw new NoSuchRowException();
		return obj;
	}
	public Collection<MyUser> getMyUserByName(String userName){
		Collection<MyUser> users = em.createQuery("SELECT obj FROM MyUser obj", MyUser.class).getResultList();
		Collection<MyUser> returnList = new ArrayList();
		for (MyUser ur : users) {
			if(ur.getUserName().equals(userName)) {
				returnList.add(ur);
			}
		}
		return returnList;
	}

	public Collection<MyUser> list() {

		return em.createQuery("SELECT obj FROM MyUser obj", MyUser.class).getResultList();

	}

	public void save(MyUser arg) {
		EntityTransaction ta = em.getTransaction();
		ta.begin();
		MyUser obj = em.find(MyUser.class, arg.getMyUserId());
		if (obj == null)
			em.persist(arg); // insert
		else
			em.merge(arg); // update
		ta.commit();
	}

	public void remove(int primaryKey) throws NoSuchRowException {
		EntityTransaction ta = em.getTransaction();
		ta.begin();
		MyUser obj = em.find(MyUser.class, primaryKey);
		if (obj != null)
			em.remove(obj); // insert
		else
			throw new NoSuchRowException();
		ta.commit();
	}

	public void close() {

		if (em != null)
			em.close();
	}
}