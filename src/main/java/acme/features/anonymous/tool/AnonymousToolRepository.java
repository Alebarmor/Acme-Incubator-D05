
package acme.features.anonymous.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tools.Tool;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousToolRepository extends AbstractRepository {

	@Query("select t from Tool t where t.id = ?1")
	Tool findOneById(int id);

	@Query("select t from Tool t")
	Collection<Tool> findManyAll();

	//TODO group by
	//	@Query("select t from Tool t GROUP BY t.starsNumber")
	//	Collection<Tool> findManyAllbyStars();

}
