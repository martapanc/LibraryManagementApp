package entities;

import java.sql.Date;

public class StaffMember {

	int staff_id;
	String last_name;
	String first_name;
	Date date_of_birth;
	String occupation;
	float salary;
	String branch_id;
	
	public StaffMember(){}
	
	public StaffMember(String last_name, String first_name, String occupation, String branch_id) {
		super();
		this.last_name = last_name;
		this.first_name = first_name;
		this.occupation = occupation;
		this.branch_id = branch_id;
	}

	
	public String[] toArray(){
		
		String[] array = {staff_id +"", last_name, first_name, date_of_birth +"", occupation, salary +"", branch_id};
		return array;
	}
	
	@Override
	public String toString() {
		return "\n  Staff ID: \t\t" + staff_id + "\n  Last name: \t\t" + last_name + "\n  First name: \t\t" + first_name
				+ "\n  Date of birth: \t\t" + date_of_birth + "   \n  Occupation: \t\t" + occupation + "   \n  Salary: \t\t$ " + salary  
				+ "\n  Branch ID: \t\t" + branch_id + "\n   ";
	}

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
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

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}	
}
