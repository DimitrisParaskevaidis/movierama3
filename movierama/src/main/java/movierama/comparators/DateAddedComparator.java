package movierama.comparators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import movierama.dto.MovieDto;
@Component
@Qualifier("date")
public class DateAddedComparator implements Comparator<MovieDto> {
	@Override
	public int compare(MovieDto o1, MovieDto o2) {
		if (o1 == null) {
			return 0;
		}

		if (o2 == null) {
			return 0;
		}
		Date addedDate1 = null;
		Date addedDate2 = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
		try {
			addedDate1 = simpleDateFormat.parse(o1.getAddeddate());
			addedDate2 = simpleDateFormat.parse(o2.getAddeddate());
		} catch (ParseException e) {
			throw new IllegalArgumentException();
		}

		return (int) (addedDate1.getTime() - addedDate2.getTime());
	}

}
