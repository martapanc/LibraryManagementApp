package entities;

public class Author {
	int author_id;
	String last_name;
	String first_name;
	String role;

	public Author(String last_name, String first_name, String role){
		this.last_name = last_name;
		this.first_name = first_name;
		this.role = role;
	}
	
	public Author() {}
	
	public String[] toArray() {
		
		String[] array = {author_id +"", last_name, first_name, role};
		return array;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Author [author_id=" + author_id + ", last_name=" + last_name + ", first_name=" + first_name + ", role="
				+ role + "]";
	}	
}
