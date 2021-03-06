package com.capg.paymentwallet.bean;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="CustomerBean")

public class CustomerBean {

	@Id
	@Column(name="cust_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cId;

	@Column(name = "fstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "emailId")
	private String emailId;
	@Column(name = "phNo")
	private String phoneNo;
	@Column(name = "panNum")
	private String panNum;
	@Column(name = "Address")
	private String address;

	

	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPanNum() {
		return panNum;
	}
	public void setPanNum(String panNum) {
		this.panNum = panNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerBean other = (CustomerBean) obj;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CustomerBean [firstName=" + firstName + ", lastName="
				+ lastName + ", emailId=" + emailId + ", phoneNo=" + phoneNo
				+ ", panNum=" + panNum + ", address=" + address + "]";
	}
	
		
}
