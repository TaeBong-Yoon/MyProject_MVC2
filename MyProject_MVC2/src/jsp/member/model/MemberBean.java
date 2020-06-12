package jsp.member.model;

import java.sql.Timestamp;

public class MemberBean {
	private String id;
	private String password;
	private String name;
	private String gender;
	private String birthdd;
	private String birthmm;
	private String birthyy;
	private String mail1;
	private String mail2;
	private String phone;
	private String address;
	private Timestamp reg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdd() {
		return birthdd;
	}

	public void setBirthdd(String birthdd) {
		this.birthdd = birthdd;
	}

	public String getBirthmm() {
		return birthmm;
	}

	public void setBirthmm(String birthmm) {
		this.birthmm = birthmm;
	}

	public String getBirthyy() {
		return birthyy;
	}

	public void setBirthyy(String birthyy) {
		this.birthyy = birthyy;
	}

	public String getMail1() {
		return mail1;
	}

	public void setMail1(String mail1) {
		this.mail1 = mail1;
	}

	public String getMail2() {
		return mail2;
	}

	public void setMail2(String mail2) {
		this.mail2 = mail2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getReg() {
		return reg;
	}

	public void setReg(Timestamp reg) {
		this.reg = reg;
	}

	

}
