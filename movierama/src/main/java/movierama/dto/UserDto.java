package movierama.dto;

public class UserDto {

	private String name;

	private String password;

	public UserDto() {
		super();
	}

	public UserDto(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
