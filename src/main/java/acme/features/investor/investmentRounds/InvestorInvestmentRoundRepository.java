
package acme.features.investor.investmentRounds;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.Investment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from Investment  i where i.finalMode = 1")
	Collection<Investment> findActivesInvestmentRounds();

	@Query("select i from Investment i where i.id =?1")
	Investment findOneInvestmentRoundById(int id);

}
