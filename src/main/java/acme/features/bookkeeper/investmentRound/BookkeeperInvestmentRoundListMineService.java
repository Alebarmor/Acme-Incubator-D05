
package acme.features.bookkeeper.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BookkeeperInvestmentRoundListMineService implements AbstractListService<Bookkeeper, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookkeeperInvestmentRoundRepository repository;


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
		request.unbind(entity, model, "title", "ticker", "amount");
	}

	@Override
	public Collection<Investment> findMany(final Request<Investment> request) {
		assert request != null;

		int id = request.getPrincipal().getActiveRoleId();

		Collection<Investment> result;

		result = this.repository.findInvestmentRoundsByAccountingWroteBy(id);

		return result;

	}

}
