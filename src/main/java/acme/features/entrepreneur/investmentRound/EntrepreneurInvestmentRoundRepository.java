
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.Investment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from Investment i where i.id = ?1")
	Investment findOneById(int id);

	@Query("select i from Investment i")
	Collection<Investment> findManyAll();

	@Query("select a.investment from Activity a where a.investment.entrepreneur.id =?1")
	Collection<Investment> findInvestmentRoundsByEntrepreneurId(int id);

}
