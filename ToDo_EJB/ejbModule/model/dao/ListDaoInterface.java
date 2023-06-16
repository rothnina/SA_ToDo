package model.dao;

import java.util.Collection;

import jakarta.ejb.Remote;
import jakarta.ejb.Remove;
import model.entity.List;
import model.entity.MyUser;

@Remote
public interface ListDaoInterface {

	public List getByPrimaryKey(int primaryKey) throws NoSuchRowException;
	
	public Collection<List> getListsFromUser(MyUser creator);

	public Collection<List> list();

	public void save(List arg);

	public void remove(int primaryKey) throws NoSuchRowException;

	@Remove
	public void close();
}