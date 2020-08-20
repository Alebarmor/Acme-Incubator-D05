
package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.Investment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from Investment i where i.id = ?1")
	Investment findOneById(int id);

	@Query("select i from Investment i")
	Collection<Investment> findManyAll();

}
