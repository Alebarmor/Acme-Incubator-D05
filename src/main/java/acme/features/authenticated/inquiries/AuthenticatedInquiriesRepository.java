
package acme.features.authenticated.inquiries;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.inquiries.Inquire;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedInquiriesRepository extends AbstractRepository {

	@Query("select i from Inquire i where i.id = ?1")
	Inquire findOneById(int id);

	@Query("select i from Inquire i where i.deadline > CURRENT_TIMESTAMP")
	Collection<Inquire> findManyActive();

}
