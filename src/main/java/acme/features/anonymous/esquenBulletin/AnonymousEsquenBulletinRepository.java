
package acme.features.anonymous.esquenBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.EsquenBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousEsquenBulletinRepository extends AbstractRepository {

	@Query("select bb from EsquenBulletin bb")
	Collection<EsquenBulletin> findMany();

}
