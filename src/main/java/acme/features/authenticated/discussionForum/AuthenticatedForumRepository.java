
package acme.features.authenticated.discussionForum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forums.Forum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("select f from Forum f where f.id = ?1")
	Forum findOneById(int id);

	@Query("select f from Forum f")
	Collection<Forum> findManyAll();

}
