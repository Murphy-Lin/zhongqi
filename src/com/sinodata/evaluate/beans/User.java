package com.sinodata.evaluate.beans;

public class User {
	
	private String IDNumbr;
	private String username;
	private String age;
	private String phone;
	private String Gender;
	
	public User(String iDNumbr, String username, String age, String phone,
			String gender) {
		super();
		IDNumbr = iDNumbr;
		this.username = username;
		this.age = age;
		this.phone = phone;
		Gender = gender;
	}
	
	

	public User() {
		super();
	}



	public String getIDNumbr() {
		return IDNumbr;
	}

	public void setIDNumbr(String iDNumbr) {
		IDNumbr = iDNumbr;
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
		Gender = gender;
	}

	@Override
	public String toString() {
		return "User [IDNumbr=" + IDNumbr + ", username=" + username + ", age="
				+ age + ", phone=" + phone + ", Gender=" + Gender + "]";
	}
	
	
}
