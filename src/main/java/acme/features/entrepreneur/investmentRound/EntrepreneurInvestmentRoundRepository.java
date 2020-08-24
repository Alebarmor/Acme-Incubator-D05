
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from Investment i where i.id = ?1")
	Investment findOneById(int id);

	@Query("select i from Investment i")
	Collection<Investment> findManyAll();

	@Query("select a.investment from Activity a where a.investment.entrepreneur.id =?1")
	Collection<Investment> findInvestmentRoundsByEntrepreneurId(int id);

	@Query("select c from Customisation c")
	Customisation findCustomisation();

	@Query("select et from Entrepreneur et where et.userAccount.id = ?1")
	Entrepreneur findOneEntrepreneurByUserAccount(int userAccountId);

	@Query("select iv from Investment iv where iv.ticker = ?1")
	Investment findTickerOfInvestment(String ticker);

}
