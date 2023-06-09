package model.entity;

import jakarta.persistence.*;

import model.entity.*;

//create table userRights (
//	    myUser references myUser(myUserId) not null,
//	    listId references myList (listId) not null,
//	    right integer, 

// right varchar2(20) check ( right in ( 'KEINE', 'ALLE', 'WASWEISSICH')
//	    primary key (myuser, listId) 
//	);

@Entity
@Table(name = "userRights")
public class UserRights implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRightsPK userRightsPK;

	
	private int right;

	@ManyToOne
	@JoinColumn(name = "myUser", insertable = false, updatable = false)
	private MyUser myUser;
	
	@ManyToOne
	@JoinColumn(name = "listId", insertable = false, updatable = false)
	private List list;
	
	public UserRights() {
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "UserRights [userRightsPK=" + userRightsPK + ", right=" + right + "]";
	}

}
