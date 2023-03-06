package movierama.controller.helper

import movierama.entity.User
import movierama.repository.DispositionRepository
import movierama.repository.MovieRepository
import movierama.repository.UserRepository
import movierame.controller.helpers.MovieRamaControllerHelper
import spock.lang.Specification

class MovieRamaControllerHelperSpec extends Specification {

	def setup() {
		def movierepo=Mock(MovieRepository.class)
		def disprepo=Mock(DispositionRepository.class)
		def userrepo=Mock(UserRepository.class)
	}

    def "searchUser  method"(){
		given:
		MovieRamaControllerHelper helper= new MovieRamaControllerHelper(movierepo,  disprepo, userrepo )
		when:
		User user =	helper.searchUser("Dim")
		then:
		userrepo.findByName(_) >> new User("", "")
		user!=null
	}
}
