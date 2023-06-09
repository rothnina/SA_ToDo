package model.dao;

import java.util.Collection;

import jakarta.ejb.*;
import jakarta.persistence.*;
import model.entity.ListEntry;


// public class MyUserDao implements java.io.Serializable, MyUserDaoInterface { // traditionell

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // = Default-Einstellung, i.e. Server übernimmt TA-Mgmt!
@Remote(ListEntryDaoInterface.class)
public class ListEntryDao implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	public ListEntryDao() {
	}

	public ListEntry getByPrimaryKey(int primaryKey) throws NoSuchRowException{
		ListEntry obj = em.find(ListEntry.class, primaryKey); 
		if (obj == null)
			throw new NoSuchRowException();
		return obj;
	}
	
	public Collection<ListEntry> getListEntriesFromList(int listId){
		return em.createQuery("SELECT obj FROM ListEntry obj WHERE obj.listId = 1?", ListEntry.class)
				.setParameter(1, listId).getResultList();
	}
	
	public Collection<ListEntry> getListEntriesFromCreator(int creator){
		return em.createQuery("SELECT obj FROM ListEntry obj WHERE obj.creator = 1?", ListEntry.class)
				.setParameter(1, creator).getResultList();
	}
	
	public Collection<ListEntry> getListEntriesFromResponsible(int responsible){
		return em.createQuery("SELECT obj FROM ListEntry obj WHERE obj.responsible = 1?", ListEntry.class)
				.setParameter(1, responsible).getResultList();
	}

	public Collection<ListEntry> list(){
		return em.createQuery("SELECT obj FROM ListEntry obj", ListEntry.class).getResultList();
	}
	
	public int calculateNextId() {
		int nextId = (int) em.createQuery("SELECT coalesce(max(x.id), 0) FROM ListEntry x").getSingleResult();
		return (nextId+1);
	}

	public void save(ListEntry arg) {
		ListEntry obj = em.find(ListEntry.class, arg.getEntryId());
		if (obj == null)
			em.persist(arg); // insert
		else
			em.merge(arg); // update
	}

	public void remove(int primaryKey) throws NoSuchRowException{
		ListEntry obj = em.find(ListEntry.class, primaryKey);
		if (obj != null)
			em.remove(obj); // insert
		else
			throw new NoSuchRowException();
	}
	

	@Remove
	public void close() {
	}
}