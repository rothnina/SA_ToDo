package model.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

//create table userRights (
//myUser references myUser(myUserId) not null,
//listId references myList (listId) not null,
//right integer,
//primary key (myuser, listId) 
//);

@Embeddable
public class UserRightsPK implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int myUser;

	private int listId;

	@Override
	public int hashCode() {
		return Objects.hash(listId, myUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRightsPK other = (UserRightsPK) obj;
		return listId == other.listId && myUser == other.myUser;
	}

	@Override
	public String toString() {
		return "UserRightsPK [myUser=" + myUser + ", listId=" + listId + "]";
	}

}
