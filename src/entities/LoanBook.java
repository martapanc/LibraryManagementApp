package entities;

import java.sql.*;

public class LoanBook {
	
	int user_id;
	int loan_id;
	int book_id;
	String title;
	Timestamp loan_date;
	Timestamp return_date;
	String branch_id;
	
	public String[] toArray(){
		String[] array = {loan_id +"", book_id + "", title, loan_date +"", return_date +"", branch_id};
		return array;		
	}
	
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getLoan_date() {
		return loan_date;
	}
	public void setLoan_date(Timestamp loan_date) {
		this.loan_date = loan_date;
	}
	public Timestamp getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Timestamp return_date) {
		this.return_date = return_date;
	}

}
