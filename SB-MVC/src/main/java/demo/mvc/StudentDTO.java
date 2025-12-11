package demo.mvc;

public class StudentDTO {
	private String name;
	private String email;
	private int age;
	private String std;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return age;
	}

	public void setPhone(int age) {
		this.age = age;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}

	@Override
	public String toString() {
		return "StudentDTO [name=" + name + ", email=" + email + ", age=" + age + ", std=" + std + "]";
	}

}
