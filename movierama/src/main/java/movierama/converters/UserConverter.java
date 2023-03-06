package movierama.converters;

import movierama.dto.UserDto;
import movierama.entity.User;

public class UserConverter {

	public UserDto convert(UserDto userDto) {

		User user = new User();
		user.setPassword(userDto.getPassword());
		user.setName(userDto.getName());
		return userDto;

	}

}
