package movierama.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import movierama.dto.MovieDto;
import movierama.entity.Movie;
import movierama.repository.DispositionRepository;

public class MovieConverter {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private DispositionRepository dispositionRepository;

	public MovieConverter(DispositionRepository likeRepository) {
		super();
		this.dispositionRepository = likeRepository;
	}

	public MovieConverter() {
		super();
	}

	public MovieDto convert(Movie movie) {

		MovieDto movieDto = new MovieDto();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
		movieDto.setAddeddate(dateFormat.format(movie.getAddeddate()));
		movieDto.setDescription(movie.getDescription());
		movieDto.setHates(dispositionRepository.countHates(movie.getTitle()));
		movieDto.setLikes(dispositionRepository.countLikes(movie.getTitle()));
		movieDto.setPublicationdate(dateFormat.format(movie.getPublicationdate()));
		movieDto.setTitle(movie.getTitle());
		movieDto.setUsername(movie.getUsername());
		return movieDto;

	}

	public Movie convert(String title, String description, String publicationdate, String username) {

		Movie movie = new Movie();

		SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
		movie.setAddeddate(new Date());
		movie.setDescription(description);
		try {
			movie.setPublicationdate(dateFormat.parse(publicationdate));
		} catch (ParseException e) {
			throw new IllegalArgumentException(publicationdate);
		}
		movie.setTitle(title);
		movie.setUsername(username);
		return movie;

	}

	public List<MovieDto> convert(List<Movie> movies) {
		List<MovieDto> movieDtos = new ArrayList<>();
		for (Movie movie : movies) {
			MovieDto movieDto = new MovieDto();
			SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
			movieDto.setAddeddate(dateFormat.format(movie.getAddeddate()));
			movieDto.setDescription(movie.getDescription());
			String title = movie.getTitle();
			Long hates = dispositionRepository.countHates(title);
			movieDto.setHates(hates == null ? 0 : hates);
			Long likes = dispositionRepository.countLikes(title);
			movieDto.setLikes(likes == null ? 0 : likes);
			movieDto.setPublicationdate(dateFormat.format(movie.getPublicationdate()));
			movieDto.setTitle(title);
			movieDto.setUsername(movie.getUsername());
			movieDtos.add(movieDto);
		}
		return movieDtos;

	}

}
