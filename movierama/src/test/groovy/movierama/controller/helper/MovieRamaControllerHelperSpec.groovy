package movierama.controller.helper

import org.springframework.ui.Model

import movierama.entity.Disposition
import movierama.entity.User
import movierama.repository.DispositionRepository
import movierama.repository.MovieRepository
import movierama.repository.UserRepository
import movierame.controller.helpers.MovieRamaControllerHelper
import spock.lang.Specification


class MovieRamaControllerHelperSpec extends Specification {




	MovieRepository movierepo
	DispositionRepository disprepo
	UserRepository userrepo
	def setup() {
		movierepo=Mock(MovieRepository.class)
		disprepo=Mock(DispositionRepository.class)
		userrepo=Mock(UserRepository.class)
	}

	def "searchUser  method"(){
		given:
		def helper= new MovieRamaControllerHelper(  disprepo,movierepo, userrepo )
		when:
		User user =	helper.searchUser("Dim")
		then:
		1*userrepo.findByName(_) >> new User("", "")
		user != null
	}


	def "increment likes"(){
		given:
		def helper= new MovieRamaControllerHelper(  disprepo,movierepo, userrepo )
		def model = Mock(Model.class)
		when:
		helper.incrementLikes(name,title,model)
		then:
		1*disprepo.findByUsernameAndMovietitleAndDisposition(title,name, 'LIKE')>>like
		1*disprepo.findByUsernameAndMovietitleAndDisposition(title,name, 'HATE')>>hate
		timesDel*disprepo.delete(_)
		timesSave*disprepo.save(_)>>hate
		where:
		name|title   |hate        		                 |like                              |timesDel|timesSave
		'Dim'|'Seven'|null                       		 |new Disposition(name,title,'LIKE')|1      |0
		'Dim'|'Seven'|new Disposition(name,title,'HATE') |new Disposition(name,title,'LIKE')|2      |0
		'Dim'|'Seven'|null                               |null							    |0      |1
	}

	def "increment hates"(){
		given:
		def helper= new MovieRamaControllerHelper(  disprepo,movierepo, userrepo )
		def model = Mock(Model.class)
		when:
		helper.incrementHates(name,title,model)
		then:
		1*disprepo.findByUsernameAndMovietitleAndDisposition(title,name, 'LIKE')>>like
		1*disprepo.findByUsernameAndMovietitleAndDisposition(title,name, 'HATE')>>hate
		timesDel*disprepo.delete(_)
		timesSave*disprepo.save(_)>>hate
		where:
		name|title   |hate        		                 |like                              |timesDel|timesSave
		'Dim'|'Seven'|new Disposition(name,title,'HATE') |new Disposition(name,title,'LIKE')|2      |0
		'Dim'|'Seven'|new Disposition(name,title,'HATE') |null                              |1      |0
		'Dim'|'Seven'|null                               |null                              |0      |1
	}



	def "showMovies"() {
		given:
		def helper= new MovieRamaControllerHelper(  disprepo,movierepo, userrepo )
		def model = Mock(Model.class)
		def movies = Mock(List.class)
		def comparator =Mock(Comparator.class)
		when:
		helper.showMovies(model,comparator,'name',movies)
		then:
		3*model.addAttribute(_,_)
	}
	
	def "insertMovie"() {
		given:
		def helper= new MovieRamaControllerHelper(  disprepo,movierepo, userrepo )
		when:
		helper.insertMovie("Seven","Lorem ipsum","23/05/2023",'Dim')
		then:
		1*movierepo.findByTitle(_)
		1*movierepo.save(_)
	}
}
