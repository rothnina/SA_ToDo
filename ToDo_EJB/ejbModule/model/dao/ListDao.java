package model.dao;

import java.util.Collection;

import jakarta.ejb.*;
import jakarta.persistence.*;
import model.entity.List;
import model.entity.MyUser;


// public class MyUserDao implements java.io.Serializable, MyUserDaoInterface { // traditionell

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // = Default-Einstellung, i.e. Server übernimmt TA-Mgmt!
@Remote(ListDaoInterface.class)
public class ListDao implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	public ListDao() {
	}

	public List getByPrimaryKey(int primaryKey) throws NoSuchRowException{
		List obj = em.find(List.class, primaryKey); 
		if (obj == null)
			throw new NoSuchRowException();
		return obj;
	}
	
	public Collection<List> getListsFromUser(MyUser creator){
		return em.createQuery("SELECT obj FROM List obj WHERE obj.creator = ?1", List.class)
				.setParameter(1, creator).getResultList();
	}
	
	public List getListByName(String listName) {
		return em.createQuery("SELECT obj FROM List obj WHERE obj.listName = ?1", List.class).setParameter(1, listName).getSingleResult(); 
	}

	public Collection<List> list(){
		return em.createQuery("SELECT obj FROM List obj", List.class).getResultList();
	}
	
	public int calculateNextId() {
		int nextId = (int) em.createQuery("SELECT coalesce(max(x.id), 0) FROM List x").getSingleResult();
		return (nextId+1);
	}

	public void save(List arg) {
		if(arg.getListId()==0) {
			arg.setListId(calculateNextId());
		}
		List obj = em.find(List.class, arg.getListId());
		if (obj == null)
			em.persist(arg); // insert
		else
			em.merge(arg); // update
	}

	public void remove(int primaryKey) throws NoSuchRowException{
		List obj = em.find(List.class, primaryKey);
		if (obj != null)
			em.remove(obj); // insert
		else
			throw new NoSuchRowException();
	}
	

	@Remove
	public void close() {
	}
}