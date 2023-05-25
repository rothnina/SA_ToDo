package model.dao;

import java.util.Collection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import model.entity.ListEntry;

public class ListEntryDao {

	private EntityManager em;

	public ListEntryDao() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenzEinheit");
		em = emf.createEntityManager();
	}

	public ListEntry getByPrimaryKey(int primaryKey) throws NoSuchRowException {
		ListEntry obj = em.find(ListEntry.class, primaryKey);
		if (obj == null)
			throw new NoSuchRowException();
		return obj;
	}

	public Collection<ListEntry> list() {
		return em.createQuery("SELECT obj FROM Fine obj", ListEntry.class).getResultList();
	}

	public void save(ListEntry arg) {
		EntityTransaction ta = em.getTransaction();
		ta.begin();
		ListEntry obj = em.find(ListEntry.class, arg.getListId());
		if (obj == null)
			em.persist(arg);
		else
			em.merge(arg);
		ta.commit();
	}

	public void remove(int primaryKey) throws NoSuchRowException {
		EntityTransaction ta = em.getTransaction();
		ta.begin();
		ListEntry obj = em.find(ListEntry.class, primaryKey);
		if (obj != null)
			em.remove(obj);
		else
			throw new NoSuchRowException();
		ta.commit();
	}

	public void close() {
		if (em != null)
			em.close();
	}
}
