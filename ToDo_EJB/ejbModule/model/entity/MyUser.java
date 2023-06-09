package model.entity;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.*;

//create table myUser (
//	    myUserId integer primary key, 
//	    firstName varchar2(20), 
//	    lastName varchar2(20), 
//	    birthday date,
//	    registrationDate date, 
//	    deregistrationDate date, 
//	    userName varchar2 (20),
//	    password varchar2(20)
//	);

@Entity
@Table(name = "myUser")
public class MyUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int myUserId;

	private String firstName;

	private String lastName;

	@Column(name = "birthday")
	private Date birthdate;

	private Date registrationDate;

	private Date deregistrationDate;

	private String userName;

	private String password;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "myUser")
	private Collection<UserRights> userRights;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
	private Collection<List> list;

	public MyUser() {
	}

	public MyUser(int myUserId, String firstName, String lastName, Date birthdate, Date registrationDate,
			Date deregistrationDate, String userName, String password) {
		this.myUserId = myUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.registrationDate = registrationDate;
		this.deregistrationDate = deregistrationDate;
		this.userName = userName;
		this.password = password;
	}
	
	public MyUser(String firstName, String lastName, Date birthdate, Date registrationDate,
			Date deregistrationDate, String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.registrationDate = registrationDate;
		this.deregistrationDate = deregistrationDate;
		this.userName = userName;
		this.password = password;
	}

	public int getMyUserId() {
		return myUserId;
	}

	public void setMyUserId(int myUserId) {
		this.myUserId = myUserId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getDeregistrationDate() {
		return deregistrationDate;
	}

	public void setDeregistrationDate(Date deregistrationDate) {
		this.deregistrationDate = deregistrationDate;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Collection<UserRights> getUserRights() {
		return userRights;
	}

	public void setUserRights(Collection<UserRights> userRights) {
		this.userRights = userRights;
	}

	
	public Collection<List> getList() {
		return list;
	}

	public void setList(Collection<List> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "MyUser [myUserId=" + myUserId + ", firstName=" + firstName + ", lastName=" + lastName + ", birthdate="
				+ birthdate + ", registrationDate=" + registrationDate + ", deregistrationDate=" + deregistrationDate
				+ ", userName=" + userName + ", password=" + password + "]";
	}

}
