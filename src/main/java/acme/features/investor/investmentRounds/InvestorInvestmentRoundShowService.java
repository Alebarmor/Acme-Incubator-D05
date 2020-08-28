
package acme.features.investor.investmentRounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Investor;
import acme.features.investor.application.InvestorApplicationRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorInvestmentRoundShowService implements AbstractShowService<Investor, Investment> {

	@Autowired
	InvestorInvestmentRoundRepository	repository;

	@Autowired
	InvestorApplicationRepository		appRepository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		/*
		 * int irId = request.getModel().getInteger("id");
		 *
		 * Investment ir = this.repository.findOneInvestmentRoundById(irId);
		 *
		 * Boolean res = true;
		 * if (!ir.finalMode) {
		 * res = false;
		 * }
		 * return res;
		 */
		return true;
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "roundKind", "title", "description", "amount", "additionalInformation");
	}

	@Override
	public Investment findOne(final Request<Investment> request) {
		assert request != null;

		Investment res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneInvestmentRoundById(id);
		return res;
	}

}
