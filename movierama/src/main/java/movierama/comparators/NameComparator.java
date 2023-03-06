package movierama.comparators;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import movierama.dto.MovieDto;

@Component
public class NameComparator implements Comparator<MovieDto> {

	@Override
	public int compare(MovieDto o1, MovieDto o2) {
		if (o1 == null) {
			return 0;
		}

		if (o2 == null) {
			return 0;
		}
		return o1.getTitle().compareTo(o2.getTitle());
	}

}
