package movierama.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import movierama.converters.MovieConverter;
import movierama.dto.MovieDto;
import movierama.entity.User;
import movierama.repository.DispositionRepository;
import movierama.repository.MovieRepository;
import movierama.repository.UserRepository;
import movierame.controller.helpers.MovieRamaControllerHelper;

@Controller
public class MovieRamaController {

	private static final String REDIRECT_MOVIES = "redirect:/movies";

	private static final String SIGNUP = "signup";

	private static final String MOVIES = "movies";

	private static final String LOGIN = "login";

	private static final String INSERTION = "insertion";

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DispositionRepository dispositionRepository;

	@Autowired
	private MovieRamaControllerHelper movieRamaControllerHelper;

	@Autowired
	private Comparator hatesComparator;

	@Autowired
	private Comparator likesComparator;

	@Autowired
	private Comparator dateAddedComparator;

	@Autowired
	private Comparator nameComparator;

	@GetMapping("/login")
	public String login() {
		return LOGIN;
	}

	@GetMapping("/insertion")
	public String insertion() {
		return INSERTION;
	}

	@PostMapping("/login")
	public String loginPost(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
		User userSearched = movieRamaControllerHelper.searchUser(username);
		if (userSearched == null) {
			return LOGIN;
		}
		return MOVIES;
	}

	@GetMapping("/signup")
	public String signupGet() {
		return SIGNUP;
	}

	@GetMapping("/sortbylikes")
	public String sortMoviesByLikes(Model model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		List<MovieDto> movies = new MovieConverter(dispositionRepository).convert(movieRepository.findAll());
		movieRamaControllerHelper.showMovies(model, likesComparator, name, movies);
		return MOVIES;
	}

	@GetMapping("/sortbyhates")
	public String sortMoviesByHates(Model model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		List<MovieDto> movies = new MovieConverter(dispositionRepository).convert(movieRepository.findAll());
		movieRamaControllerHelper.showMovies(model, hatesComparator, name, movies);
		return MOVIES;
	}

	@GetMapping("/sortbyaddeddate")
	public String sortMoviesByAddeddate(Model model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		List<MovieDto> movies = new MovieConverter(dispositionRepository).convert(movieRepository.findAll());
		movieRamaControllerHelper.showMovies(model, dateAddedComparator, name, movies);
		return MOVIES;
	}

	@PostMapping("/signup")
	public void signup(@ModelAttribute("username") String username, @ModelAttribute("password") String password,@ModelAttribute("password2") String password2) {
		movieRamaControllerHelper.saveUser(username, password,password2);
	}



	@GetMapping("/movies")
	public String showAll(Model model) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		List<MovieDto> movies = new MovieConverter(dispositionRepository).convert(movieRepository.findAll());
		movieRamaControllerHelper.showMovies(model, nameComparator, name, movies);
		return MOVIES;
	}

	@GetMapping(value = "/incrementlikes")
	public String incrementLikes(@RequestParam String title, @RequestParam String name, Model model) {
		movieRamaControllerHelper.incrementLikes(title, name, model);
		return REDIRECT_MOVIES;
	}

	@GetMapping(value = "/incrementhates")
	public String incrementHates(@RequestParam String title, @RequestParam String name, Model model) {
		movieRamaControllerHelper.incrementHates(title, name, model);
		return REDIRECT_MOVIES;
	}

	@PostMapping("/insertion")
	public String insertMovies(@ModelAttribute("title") String title, @ModelAttribute("description") String description,
			@ModelAttribute("publicationdate") String publicationdate, BindingResult result) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		movieRamaControllerHelper.insertMovie(title, description, publicationdate, name);
		return INSERTION;
	}

}
