package model.entity;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.*;


//create table myList (
//    listId integer primary key,
//    creator integer, 
//    creationDate date,
//    listName varchar2(20),
//    foreign key (creator) references myuser (myuserid)
//);
//

@Entity
@Table(name = "myList")
public class List implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "listId") //not necessary because the name is identical with them in the database
	private int listId;

	@Column(name = "creator")
	private int creator;

	@Column(name = "creationDate")
	private Date creationDate;
	
	@Column(name = "listName")
	private String listName; 
	
	

	public List() {
	}

	public List(int listId, int creator, Date creationDate, String listName ) {
		this.listId = listId; 
		this.creator = creator; 
		this.creationDate = creationDate; 
		this.listName = listName; 
	}

	

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "List [listId=" + listId + ", creator=" + creator + ", creationDate=" + creationDate + ", listName="
				+ listName + "]";
	}

}
