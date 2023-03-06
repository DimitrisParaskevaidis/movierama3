package movierama.controller.helper

import movierama.converters.MovieConverter
import movierama.entity.Movie
import movierama.repository.DispositionRepository
import spock.lang.Specification

class MovieConverterSpec extends Specification {










	def "convert to Movie"(){
		given:
		MovieConverter converter = new MovieConverter();
		when:
		def movie=converter.convert("Seven", "Lorem ipsum", "23/05/2023", "Dimitris");
		then:
		movie.publicationdate.getTime() ==1684789200000
		movie.title=='Seven'
		movie.description == 'Lorem ipsum'
		movie.username == 'Dimitris'
	}



	def "convert to movies list"(){
		given:
		MovieConverter converter = new MovieConverter();
		def movie1=converter.convert("Seven", "Lorem ipsum", "23/05/2023", "Dimitris");
		def movie2 = converter.convert("Eight", "Lorem ipsum", "23/05/2023", "Dim");
		List<Movie> movies= new ArrayList<>();
		movies.add(movie1)
		movies.add(movie2)
		expect:
		def movie = movies.first()
		movie.publicationdate.getTime() ==1684789200000
		movie.title=='Seven'
		movie.description == 'Lorem ipsum'
		movie.username == 'Dimitris'
		def movie3=movies.last()
		movie3.publicationdate.getTime() ==1684789200000
		movie3.title=='Eight'
		movie3.description == 'Lorem ipsum'
		movie3.username == 'Dim'
	}




	def "convert to movieDto"(){
		given:
		def dispositionRepository = Mock (DispositionRepository.class)
		MovieConverter converter = new MovieConverter(dispositionRepository);
		when:
		def movieDto=converter.convert(converter.convert("Seven", "Lorem ipsum", "23/05/2023", "Dimitris"));
		then:
		1*dispositionRepository.countLikes(_) >>12
		1*dispositionRepository.countHates(_) >>15
		movieDto.likes ==12
		movieDto.hates==15
		movieDto.publicationdate =="23/05/2023"
		movieDto.title=='Seven'
		movieDto.description == 'Lorem ipsum'
		movieDto.username == 'Dimitris'
	}
}
