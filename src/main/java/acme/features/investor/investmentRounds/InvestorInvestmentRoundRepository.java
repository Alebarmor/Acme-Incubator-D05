
package acme.features.investor.investmentRounds;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.Investment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from Investment  ir where ir.finalMode = 1")
	Collection<Investment> findActivesInvestmentRounds();

	@Query("select ir from Investment ir where ir.id =?1")
	Investment findOneInvestmentRoundById(int id);

}
