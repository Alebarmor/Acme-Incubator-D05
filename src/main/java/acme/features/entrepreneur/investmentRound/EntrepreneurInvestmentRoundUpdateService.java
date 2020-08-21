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

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Activity;
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

		Boolean sumBudgets;

		// Validación de cuándo puede ser finalMode
		if (entity.getFinalMode()) {
			if (!errors.hasErrors("finalMode")) {
				sumBudgets = this.sumActivities(entity.getId());
				errors.state(request, sumBudgets, "finalMode", "errors.job.is.finalMode.sumBudgets", "It can be finalMode when the Investment amount coincides with the sum up of the activities budgets");
			}

			//			// Validación de Spam
			//			if (!errors.hasErrors("title")) {
			//				Boolean isSpam = this.spam(entity.getTitle());
			//				errors.state(request, !isSpam, "title", "errors.job.description.spam", "Contain spam words");
			//			}
			//
			//			if (!errors.hasErrors("description")) {
			//				Boolean isSpam = this.spam(entity.getDescription());
			//				errors.state(request, !isSpam, "description", "errors.job.description.spam", "Contain spam words");
			//			}
			//
			//			if (!errors.hasErrors("additionalInformation")) {
			//				Boolean isSpam = this.spam(entity.getAdditionalInformation());
			//				errors.state(request, !isSpam, "moreInfo", "errors.job.description.spam", "Contain spam words");
			//			}

		}
	}

	private boolean sumActivities(final Integer idInvestment) {
		Boolean result;
		Collection<Activity> activities;
		Investment inv;
		Double sum = 0.0;
		activities = this.repository.findActivitiesByInvestment(idInvestment);
		inv = this.repository.findOneById(idInvestment);
		for (Activity a : activities) {
			Double precio = a.getBudget().getAmount();
			sum = sum + precio;
		}
		if (sum == inv.getAmount().getAmount()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public void bind(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "entrepreneur");
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

		Date creationMoment;

		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);

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
