
package acme.features.authenticated.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.Activity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedActivityRepository extends AbstractRepository {

	@Query("select a from Activity a where a.id = ?1")
	Activity findOneById(int id);

	@Query("select a from Activity a")
	Collection<Activity> findManyAll();

	@Query("select a from Activity a where a.investment.id =?1")
	Collection<Activity> findAllActivitiesById(int id);

}
