package model.entity;

import java.io.Serializable;

import java.util.Date;

import jakarta.persistence.*;



//create table listEntry (
//	     entryId integer, 
//	     listId integer, 
//	     responsible integer, 
//	     creator integer, 
//	     creationDate date, 
//	     endTime date, 
//	     status integer, 
//	     toDo varchar2(100), 
//	     primary key (entryId), 
//	     foreign key (listId) references myList(listId),
//	     foreign key (responsible) references user(userId), 
//	     foreign key (creator) references user(userId)
//	);

@Entity
@Table(name = "listEntry")
public class ListEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "entryId") //not necessary because the name is identical with them in the database
	private int entryId;

	@Column(name = "listId")
	private int listId;

	@Column(name = "responsible")
	private int responsible;
	
	@Column(name = "creator")
	private int creator; 
	
	@Column(name = "creationDate")
	private Date creationDate;
	
	@Column(name = "endTime")
	private Date endTime; 
	
	@Column(name = "status")
	private int status; 
	
	@Column(name = "toDo")
	private String toDo; 

	public ListEntry() {
	}

	public ListEntry(int entryId, int listId, int responsible, int creator, Date creationDate, Date endTime, int status, String toDo ) {
		this.entryId = entryId; 
		this.listId = listId; 
		this.responsible = responsible; 
		this.creator = creator; 
		this.creationDate = creationDate; 
		this.endTime = endTime; 
		this.status = status;
		this.toDo = toDo; 
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public int getResponsible() {
		return responsible;
	}

	public void setResponsible(int responsible) {
		this.responsible = responsible;
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

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ListEntry [entryId=" + entryId + ", listId=" + listId + ", responsible=" + responsible + ", creator="
				+ creator + ", creationDate=" + creationDate + ", endTime=" + endTime + ", status=" + status + ", toDo="
				+ toDo + "]";
	}

}
