
package acme.features.investor.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	private InvestorApplicationRepository	repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	repositoryInvest;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		//Application application = this.repository.findOneApplicationById(request.getModel().getInteger("id"));

		//return application == null;
		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "statement", "offer", "investmentRound.ticker");
		model.setAttribute("investId", entity.getInvestmentRound().getId());
	}

	@Override
	public Application instantiate(final Request<Application> request) {

		Application result;
		Principal principal;

		int idInvestment;
		principal = request.getPrincipal();

		idInvestment = request.getModel().getInteger("id");
		Entrepreneur entrepreneur;
		result = new Application();

		Investment i = this.repositoryInvest.findOneInvestmentRoundById(idInvestment);
		entrepreneur = i.getEntrepreneur();
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		int investorId = principal.getAccountId();
		Investor investor = this.repository.findOneInvestorByUserAccountId(investorId);

		result.setCreationMoment(moment);
		result.setInvestor(investor);
		result.setInvestmentRound(this.repository.findOneInvestmentRoundById(idInvestment));
		result.setEntrepreneur(entrepreneur);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("ticker")) {
			Boolean unique = null;
			unique = this.repository.findApplicationByTicker(entity.getTicker()) != null;
			errors.state(request, !unique, "ticker", "investor.application.error.duplicatedTicker");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		this.repository.save(entity);
	}

}
