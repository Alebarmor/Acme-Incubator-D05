
package acme.features.entrepreneur.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentRoundsShowService implements AbstractShowService<Entrepreneur, Investment> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		boolean result;
		int investId;
		Investment i;
		Entrepreneur entrepreneur;
		Principal principal;

		investId = request.getModel().getInteger("id");
		i = this.repository.findOneById(investId);
		entrepreneur = i.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "ticker", "creationMoment", "roundKind", "amount", "description", "additionalInformation", "finalMode");
	}

	@Override
	public Investment findOne(final Request<Investment> request) {
		assert request != null;

		Investment result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
