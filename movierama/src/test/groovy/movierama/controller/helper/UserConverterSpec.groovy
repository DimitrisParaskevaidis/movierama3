package movierama.controller.helper

import movierama.converters.UserConverter
import movierama.dto.UserDto
import spock.lang.Specification

class UserConverterSpec extends Specification {





	def "convert to userDto"(){
		given:
		UserConverter converter = new UserConverter();
		when:
		def user=converter.convert(new UserDto("Dim","password"));
		then:
		user.name=="Dim"
		user.password=="password"
	}
}