
package acme.features.administrator.inquiries;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.inquiries.Inquire;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorInquiriesRepository extends AbstractRepository {

	@Query("select i from Inquire i where i.id = ?1")
	Inquire findOneById(int id);

	@Query("select i from Inquire i")
	Collection<Inquire> findMany();

}
