package model.dao;

import java.util.Collection;

import jakarta.ejb.Remote;
import jakarta.ejb.Remove;
import model.entity.ListEntry;

@Remote
public interface ListEntryDaoInterface {

	public ListEntry getByPrimaryKey(int primaryKey) throws NoSuchRowException;
	
	public Collection<ListEntry> getListEntriesFromList(int listId);
	
	public Collection<ListEntry> getListEntriesFromCreator(int creator);
	
	public Collection<ListEntry> getListEntriesFromResponsible(int responsible);

	public Collection<ListEntry> list();

	public void save(ListEntry arg);

	public void remove(int primaryKey) throws NoSuchRowException;

	@Remove
	public void close();
}