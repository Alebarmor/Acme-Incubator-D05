
package acme.features.investor.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	private InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		Application application = this.repository.findOneApplicationById(request.getModel().getInteger("id"));

		return application == null;
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

		request.unbind(entity, model, "ticker", "creationMoment", "statement", "offer");
		model.setAttribute("id", request.getModel().getInteger("id"));
	}

	@Override
	public Application instantiate(final Request<Application> request) {

		Application result;
		Principal principal;

		int idInvestment;
		principal = request.getPrincipal();
		//		accountId = principal.getActiveRoleId();
		idInvestment = request.getModel().getInteger("id");
		result = new Application();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		int investorId = principal.getAccountId();
		Investor investor = this.repository.findOneInvestorByUserAccountId(investorId);
		//String firmName = investor.getFirmName();
		//String profile = investor.getProfile();
		//String sector = investor.getSector();

		result.setCreationMoment(moment);
		result.setInvestor(investor);
		result.setInvestmentRound(this.repository.findOneInvestmentRoundById(idInvestment));

		//result.setSkills(skills);
		//result.setQualifications(qualifications);
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