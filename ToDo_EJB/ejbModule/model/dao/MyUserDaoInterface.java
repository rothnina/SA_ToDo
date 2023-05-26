package model.dao;

import java.util.Collection;

import jakarta.ejb.Remote;
import jakarta.ejb.Remove;
import model.entity.MyUser;

@Remote
public interface MyUserDaoInterface {

	public MyUser getByPrimaryKey(int primaryKey) throws NoSuchRowException;
	
	public MyUser getMyUserByName(String userName);

	public Collection<MyUser> list();

	public void save(MyUser arg);

	public void remove(int primaryKey) throws NoSuchRowException;

	@Remove
	public void close();
}