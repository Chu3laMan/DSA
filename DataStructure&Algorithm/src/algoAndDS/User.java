package algoAndDS;

public class User {
	
	private String firstName;
	private String lastName;
	private String programming_language;
	private String framework;
	
	public User() {}

	public User(String firstName, String lastName, String programming_language, String framework) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.programming_language = programming_language;
		this.framework = framework;
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
	
	public String getProgramming_language() {
		return programming_language;
	}

	public void setProgramming_language(String programming_language) {
		this.programming_language = programming_language;
	}

	public String getFramework() {
		return framework;
	}

	public void setFramework(String framework) {
		this.framework = framework;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", programming_language="
				+ programming_language + ", framework=" + framework + "]";
	}

	

}
