
package acme.features.investor.investmentRounds;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class InvestorInvestmentRoundListService implements AbstractListService<Investor, Investment> {

	@Autowired
	InvestorInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "roundKind", "title");
	}

	@Override
	public Collection<Investment> findMany(final Request<Investment> request) {
		assert request != null;

		Collection<Investment> res;

		res = this.repository.findActivesInvestmentRounds();

		return res;
	}

}
