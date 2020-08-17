
package acme.features.anonymous.notices;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.notices.Notice;
import acme.framework.repositories.AbstractRepository;

public interface AnonymousNoticesRepository extends AbstractRepository {

	@Query("select n from Notice n where n.id = ?1")
	Notice findOneById(int id);

	@Query("select n from Notice n where n.deadline > CURRENT_TIMESTAMP")
	Collection<Notice> findManyActive();

}
