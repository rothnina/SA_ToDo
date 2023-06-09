package model.dao;

import java.util.Collection;

import jakarta.ejb.Remote;
import jakarta.ejb.Remove;
import model.entity.UserRights;

@Remote
public interface UserRightsDaoInterface {

	public Collection<UserRights> getByMyUser(int myUser) throws NoSuchRowException;
	
	public Collection<UserRights> getByListId(int listId) throws NoSuchRowException;
	
	public UserRights getByMyUserAndListId(int myUser, int listId) throws NoSuchRowException;

	public Collection<UserRights> list();

	public void save(UserRights arg);

	public void remove(int primaryKey) throws NoSuchRowException;

	@Remove
	public void close();
}