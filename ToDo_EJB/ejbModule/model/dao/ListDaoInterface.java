package model.dao;

import java.util.Collection;

import jakarta.ejb.Remote;
import jakarta.ejb.Remove;
import model.entity.List;

@Remote
public interface ListDaoInterface {

	public List getByPrimaryKey(int primaryKey) throws NoSuchRowException;
	
	public Collection<List> getListsFromUser(String userName);

	public Collection<List> list();

	public void save(List arg);

	public void remove(int primaryKey) throws NoSuchRowException;

	@Remove
	public void close();
}