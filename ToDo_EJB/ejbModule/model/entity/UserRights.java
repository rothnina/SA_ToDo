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
public class UserRights implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRightsPK userRightsPK;	
	
	@Column(name="right")
	private int right;
	
	public UserRights() {}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "UserRights [userRightsPK=" + userRightsPK + ", right=" + right + "]";
	}
	
}
