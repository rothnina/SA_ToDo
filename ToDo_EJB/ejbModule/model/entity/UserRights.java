package model.entity;

import jakarta.persistence.*;

//create table userRights (
//	    myUser references myUser(myUserId) not null,
//	    listId references myList (listId) not null,
//	    right integer,
//	    primary key (myuser, listId), 
//	    foreign key (myUser) references user(userId),
//	    foreign key (listId) references myList(listId) 
//	);

@Entity
@Table(name="userRights")
public class UserRights {
	private static final long serialVersionUID = 1L;

	@Column(name = "myUserId")
	private int myUserId;

	@Column(name = "listId")
	private int listId;
	
	@Column(name="right")
	private int right;
	
	public UserRights() {}

	public UserRights(int myUserId, int listId, int right) {
		super();
		this.myUserId = myUserId;
		this.listId = listId;
		this.right = right;
	}

	public int getMyUserId() {
		return myUserId;
	}

	public void setMyUserId(int myUserId) {
		this.myUserId = myUserId;
	}

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "UserRights [myUserId=" + myUserId + ", listId=" + listId + ", right=" + right + "]";
	}
	
	
}
