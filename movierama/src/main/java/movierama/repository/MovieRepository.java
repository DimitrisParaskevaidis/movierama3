package movierama.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import movierama.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

	public List<Movie> findAll();

	public List<Movie> findByUsername(String name);

	public Movie findByTitle(String title);

	@SuppressWarnings("unchecked")
	public Movie save(Movie movie);

}
