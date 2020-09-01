
package acme.features.authenticated.messages;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedMessagesRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findMessagesByForumId(int forumId);

}
