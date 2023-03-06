package movierama.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import movierama.entity.Disposition;

public interface DispositionRepository extends CrudRepository<Disposition, Long> {

	@Query(value = "SELECT COUNT (USERNAME) FROM DISP WHERE MOVIETITLE=?1 AND DISPOSITION='LIKE'  GROUP BY MOVIETITLE,DISPOSITION", nativeQuery = true)
	public Long countLikes(String movietitle);

	@Query(value = "SELECT COUNT (USERNAME) FROM DISP WHERE MOVIETITLE=?1 AND DISPOSITION='HATE'  GROUP BY MOVIETITLE,DISPOSITION", nativeQuery = true)
	public Long countHates(String movietitle);

	@SuppressWarnings("unchecked")
	public Disposition save(Disposition disposition);

	public void delete(Disposition disposition);

	public Disposition findByUsernameAndMovietitleAndDisposition(String username, String movietitle,
			String disposition);

}
