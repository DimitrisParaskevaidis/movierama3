package movierama.repository;

import org.springframework.data.repository.CrudRepository;

import movierama.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByName(String name);

	@SuppressWarnings("unchecked")
	User save(User user);

}
