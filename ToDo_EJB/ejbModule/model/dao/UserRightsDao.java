package model.dao;

import java.util.Collection;

import jakarta.persistence.*;
import model.entity.UserRights;


public class UserRightsDao {

	private EntityManager em;

	public UserRightsDao() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceToDo");
		em = emf.createEntityManager();
	}

	public UserRights getByUserId(int userId) throws NoSuchRowException {
		// zu erzeugendes Statement:
		// select * from sportler where snr = ? (Prepared Statement)
		UserRights obj = em.find(UserRights.class, userId);
		if (obj == null)
			throw new NoSuchRowException();
		return obj;
	}

	public Collection<UserRights> list() {
		return em.createQuery("SELECT obj FROM UserRights obj", UserRights.class).getResultList();
	}

	public void save(UserRights arg) {
		EntityTransaction ta = em.getTransaction();
		ta.begin();
		Collection<UserRights> obj = em.createQuery("SELECT obj from UserRights obj",UserRights.class).getResultList();
		if (obj == null)
			em.persist(arg); // insert
		else {
			for (UserRights ur : obj) {
				if (ur.getMyUserId() == arg.getMyUserId()) {
					if (ur.getListId() == arg.getListId()) {
						em.merge(arg); // update
						ta.commit();
						return;
					}
				}
			}
			em.persist(arg);
			
		}
		ta.commit();
	}

	public void remove(UserRights arg) throws NoSuchRowException {
		EntityTransaction ta = em.getTransaction();
		ta.begin();
		Collection<UserRights> obj = em.createQuery("SELECT obj from UserRights obj",UserRights.class).getResultList();
		for(UserRights ur : obj) {
			if(ur.getMyUserId() == arg.getMyUserId()) {
				if (ur.getListId() == arg.getListId()) {
					em.remove(ur); // remove
				}
			}
		}
		ta.commit();
	}

	public void close() {
		// entspricht (im weitesten Sinne) conn.close()
		if (em != null)
			em.close();
	}
}
