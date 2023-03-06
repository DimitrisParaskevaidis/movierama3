package movierama.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import movierama.comparators.DateAddedComparator;
import movierama.comparators.HatesComparator;
import movierama.comparators.LikesComparator;
import movierama.comparators.NameComparator;
import movierama.repository.DispositionRepository;
import movierama.repository.MovieRepository;
import movierama.repository.UserRepository;
import movierame.controller.helpers.MovieRamaControllerHelper;

@Configuration
public class MovieramaConfiguration {

	@Autowired
	private DispositionRepository repository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Bean
	public NameComparator nameComp() {
		return new NameComparator();
	}

	@Bean
	public HatesComparator hateComp() {
		return new HatesComparator();
	}

	@Bean
	public LikesComparator likeComp() {
		return new LikesComparator();
	}

	@Bean
	public DateAddedComparator dateComp() {
		return new DateAddedComparator();
	}

	@Bean
	public MovieRamaControllerHelper helper() {
		return new MovieRamaControllerHelper(repository, movieRepository, userRepository);
	}
}
