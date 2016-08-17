package entities;

import java.sql.Date;

public class LibraryUser {

	int user_id;
	String last_name;
	String first_name;
	Date date_of_birth;
	String address;
	String email;
	Date member_since;
	String branch_id;
	public LibraryUser(){}
	
	public LibraryUser(String last_name, String first_name, String address, String email, String branch_id) {
		this.last_name = last_name;
		this.first_name = first_name;
		this.address = address;
		this.email = email;
		this.branch_id = branch_id;
	}

	public String[] toArray() {
		String[] user = {user_id +"", last_name, first_name, date_of_birth +"", address, email, member_since +"", branch_id};
		return user;
	}
	
	@Override
	public String toString() {
		return "\n  User ID: \t\t" + user_id + "\n  Last name: \t\t" + last_name + "\n  First name: \t\t" + first_name
				+ "\n  Date of birth: \t\t" + date_of_birth + "\n  Address: \t\t" + address + "   \n  E-Mail: \t\t" + email 
				+ "\n  Member since: \t" + member_since + "\n  Branch ID: \t\t" + branch_id + "\n  ";
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getMember_since() {
		return member_since;
	}
	public void setMember_since(Date member_since) {
		this.member_since = member_since;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}	
}
