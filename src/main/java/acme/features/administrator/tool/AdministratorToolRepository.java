
package acme.features.administrator.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tools.Tool;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorToolRepository extends AbstractRepository {

	@Query("select t from Tool t where t.id = ?1")
	Tool findOneToolById(int id);

	@Query("select t from Tool t")
	Collection<Tool> findManyTools();

}
