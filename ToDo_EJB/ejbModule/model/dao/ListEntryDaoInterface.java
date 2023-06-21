package model.dao;

import java.util.Collection;

import jakarta.ejb.Remote;
import jakarta.ejb.Remove;
import model.entity.List;
import model.entity.ListEntry;
import model.entity.MyUser;

@Remote
public interface ListEntryDaoInterface {

	public ListEntry getByPrimaryKey(int primaryKey) throws NoSuchRowException;
	
	public Collection<ListEntry> getListEntriesFromList(List list);
	
	public Collection<ListEntry> getListEntriesFromCreator(MyUser creator);
	
	public Collection<ListEntry> getListEntriesFromResponsible(MyUser responsible);
	
	public Collection<ListEntry> getListEntriesFromListByUser(List list,MyUser user); 

	public Collection<ListEntry> list();

	public void save(ListEntry arg);

	public void remove(int primaryKey) throws NoSuchRowException;

	@Remove
	public void close();
}