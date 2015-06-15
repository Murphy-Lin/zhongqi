package com.sinodata.evaluate.beans;

public class User {
	
	private String IDNumbr;
	private String username;
	private String age;
	private String phone;
	private String Gender;
	private String mail;
	private String AccountID;
	
	public User() {
		super();
	}
	
	public User(String iDNumbr, String username, String age, String phone,
			String gender, String mail, String accountID) {
		super();
		this.IDNumbr = iDNumbr;
		this.username = username;
		this.age = age;
		this.phone = phone;
		this.Gender = gender;
		this.mail = mail;
		this.AccountID = accountID;
	}

	public String getIDNumbr() {
		return IDNumbr;
	}

	public void setIDNumbr(String iDNumbr) {
		this.IDNumbr = iDNumbr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		this.Gender = gender;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAccountID() {
		return AccountID;
	}

	public void setAccountID(String accountID) {
		this.AccountID = accountID;
	}

	@Override
	public String toString() {
		return "User [IDNumbr=" + IDNumbr + ", username=" + username + ", age="
				+ age + ", phone=" + phone + ", Gender=" + Gender + ", mail="
				+ mail + ", AccountID=" + AccountID + "]";
	}
	
}
