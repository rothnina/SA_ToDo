package entity;

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
@Table(name="myUser")
public class MyUser {
	private static final long serialVersionUID = 1L;

	@Id
//	@Column(name="myUserId")
	private int myUserId;
	
//	@Column(name = "firstName")
	private String firstName;

//	@Column(name = "lastName")
	private String lastName;

//	@Column(name = "birthdate")
	private int birthdate;

//	@Column(name = "registrationDate")
	private String registrationDate;

//	@Column(name = "deregistrationDate")
	private int deregistrationDate;

//	@Column(name = "userName")
	private String userName;
	
//	@Column(name = "password")
	private String password;

	public MyUser() {}
	
	
	public MyUser(int myUserId, String firstName, String lastName, int birthdate, String registrationDate,
			int deregistrationDate, String userName, String password) {
		super();
		this.myUserId = myUserId;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getDeregistrationDate() {
		return deregistrationDate;
	}

	public void setDeregistrationDate(int deregistrationDate) {
		this.deregistrationDate = deregistrationDate;
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

	@Override
	public String toString() {
		return "MyUser [myUserId=" + myUserId + ", firstName=" + firstName + ", lastName=" + lastName + ", birthdate="
				+ birthdate + ", registrationDate=" + registrationDate + ", deregistrationDate=" + deregistrationDate
				+ ", userName=" + userName + ", password=" + password + "]";
	}
	
	
	
}
