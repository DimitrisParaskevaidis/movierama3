package movierame.controller.helpers;

import java.util.Comparator;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import movierama.converters.MovieConverter;
import movierama.dto.MovieDto;
import movierama.entity.Disposition;
import movierama.entity.Movie;
import movierama.entity.User;
import movierama.repository.DispositionRepository;
import movierama.repository.MovieRepository;
import movierama.repository.UserRepository;

@Service
public class MovieRamaControllerHelper {

	private static final String COMPARATOR = "comparator";

	private static final String USERNAME = "username";

	private static final String HATE = "HATE";

	private static final String LIKE = "LIKE";

	private static final String MOVIES = "movies";

	private DispositionRepository repository;

	private MovieRepository moviesRepository;

	private UserRepository userRepository;

	public MovieRamaControllerHelper(DispositionRepository dispositionRepository, MovieRepository movieRepository,
			UserRepository userRepository) {
		super();
		this.moviesRepository = movieRepository;
		this.repository = dispositionRepository;
		this.userRepository = userRepository;
	}

	public User searchUser(String username) {
		return userRepository.findByName(username);
	}

	public Disposition incrementLikes(String title, String name, Model model) {
		model.addAttribute(USERNAME, name);
		Disposition like = fetchDisposition(title, name, LIKE);
		Disposition hate = fetchDisposition(title, name, HATE);
		Disposition disp = null;
		if (hate != null) {
			repository.delete(hate);
		}
		if (like != null) {
			repository.delete(like);
		} else if (like == null) {

			disp = repository.save(new Disposition(name, title, LIKE));
		}
		return disp;
	}

	public void incrementHates(String title, String name, Model model) {
		model.addAttribute(USERNAME, name);
		Disposition like = fetchDisposition(title, name, LIKE);
		Disposition hate = fetchDisposition(title, name, HATE);
		if (like != null) {
			repository.delete(like);
		}
		if (hate != null) {
			repository.delete(hate);
		} else if (hate == null) {

			repository.save(new Disposition(name, title, HATE));
		}
	}

	public void showMovies(Model model, Comparator comparator, String name, List<MovieDto> movies) {
		model.addAttribute(USERNAME, name);
		model.addAttribute(MOVIES, movies);
		model.addAttribute(COMPARATOR, comparator);
	}

	public void insertMovie(String title, String description, String publicationdate, String name) {
		Movie movie = moviesRepository.findByTitle(title);
		if (movie != null) {
			return;
		}
		Movie movieToBeSaved = new MovieConverter().convert(title, description, publicationdate, name);

		moviesRepository.save(movieToBeSaved);
	}

	public void saveUser(String username, String password, String password2) {
		if(password==null) {
			return;
		}
		if(!password.equals(password2)) {
			return;
		}
		User user = new User(username, password);
		User userSearched = this.searchUser(username);
		if (userSearched == null) {
			userRepository.save(user);
		}
	}
	private Disposition fetchDisposition(String title, String name, String disposition) {
		return repository.findByUsernameAndMovietitleAndDisposition(name, title, disposition);
	}

}
