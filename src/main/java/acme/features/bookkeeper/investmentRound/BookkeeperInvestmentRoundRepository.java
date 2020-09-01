
package acme.features.bookkeeper.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.Investment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from Investment i where i.id = ?1")
	Investment findOneById(int id);

	@Query("select i from Investment i")
	Collection<Investment> findManyAll();

	@Query("select ar.investment from AccountingRecord ar where ar.bookkeeper.id !=?1")
	Collection<Investment> findInvestmentRoundsByAccountingNotWroteBy(int id);

	@Query("select ar.investment from AccountingRecord ar where ar.bookkeeper.id =?1")
	Collection<Investment> findInvestmentRoundsByAccountingWroteBy(int id);

}
