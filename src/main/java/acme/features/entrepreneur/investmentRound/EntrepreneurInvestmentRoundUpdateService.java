/*
 * AuthenticatedConsumerUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, Investment> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurInvestmentRoundRepository repository;


	// AbstractUpdateService<Authenticated, Consumer> interface -----------------

	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean superaMoney;
		Double sumaBudget;

		// Validación del dinero

		int cont = 0;
		cont = this.repository.numberOfActivitiesByInvestmentId(entity.getId());
		if (cont != 0) {

			if (!errors.hasErrors("amount")) {
				superaMoney = true;
				sumaBudget = this.repository.sumBudgetWorkProgramme(entity.getId());
				double actualAmount = entity.getAmount().getAmount();
				if (actualAmount < sumaBudget) {
					superaMoney = false;
				}
				errors.state(request, superaMoney, "amount", "entrepreneur.investment-round.form.error.dineroIncorrecto");
			}
		}
	}

	@Override
	public void bind(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "roundKind", "title", "description", "amount", "additionalInformation", "finalMode");
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

	@Override
	public void update(final Request<Investment> request, final Investment entity) {
		assert request != null;
		assert entity != null;

		int cont = 0;
		cont = this.repository.numberOfActivitiesByInvestmentId(entity.getId());
		if (cont != 0) {

			double sumaBudget = this.repository.sumBudgetWorkProgramme(entity.getId());
			double actualAmount = entity.getAmount().getAmount();
			if (actualAmount == sumaBudget) {
				entity.setFinalMode(true);
			}
		}

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Investment> request, final Response<Investment> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
