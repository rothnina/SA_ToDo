package model.dao;

import java.util.Collection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import model.entity.List;

public class ListDao {

	private EntityManager em;

	public ListDao() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceToDo");
		em = emf.createEntityManager();
	}

	public List getByPrimaryKey(int primaryKey) throws NoSuchRowException {
		List obj = em.find(List.class, primaryKey);
		if (obj == null)
			throw new NoSuchRowException();
		return obj;
	}

	public Collection<List> list() {
		return em.createQuery("SELECT obj FROM Fine obj", List.class).getResultList();
	}

	public void save(List arg) {
		EntityTransaction ta = em.getTransaction();
		ta.begin();
		List obj = em.find(List.class, arg.getListId());
		if (obj == null)
			em.persist(arg);
		else
			em.merge(arg);
		ta.commit();
	}

	public void remove(int primaryKey) throws NoSuchRowException {
		EntityTransaction ta = em.getTransaction();
		ta.begin();
		List obj = em.find(List.class, primaryKey);
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

