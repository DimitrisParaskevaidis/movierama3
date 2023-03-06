package movierama.comparators;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import movierama.dto.MovieDto;

@Component
@Qualifier("hate")
public class HatesComparator implements Comparator<MovieDto> {

	@Override
	public int compare(MovieDto o1, MovieDto o2) {
		if (o1 == null) {
			return 0;
		}

		if (o2 == null) {
			return 0;
		}
		return (int) (o1.getHates() - o2.getHates());
	}

}
