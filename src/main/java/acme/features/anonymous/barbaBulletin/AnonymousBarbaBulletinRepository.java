
package acme.features.anonymous.barbaBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.BarbaBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousBarbaBulletinRepository extends AbstractRepository {

	@Query("select bb from BarbaBulletin bb")
	Collection<BarbaBulletin> findMany();

}
