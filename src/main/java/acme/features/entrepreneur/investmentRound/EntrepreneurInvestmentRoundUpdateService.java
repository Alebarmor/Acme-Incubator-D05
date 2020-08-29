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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisations.AdministratorCustomisationRepository;
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
	private EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	private AdministratorCustomisationRepository	spamRepository;


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

		int id = entity.getId();
		Boolean finalMode = this.repository.isFinalMode(id);

		List<Customisation> ca = (List<Customisation>) this.spamRepository.findManyAll();
		Customisation c = ca.get(0);

		// Validación del Round Kind

		if (!errors.hasErrors("roundKind")) {
			List<String> kinds = new ArrayList<String>(Arrays.asList("SEED", "ANGEL", "SERIES_A", "SERIES_B", "SERIES_C", "BRIDGE"));
			Boolean correct = kinds.contains(entity.getRoundKind().toString());
			errors.state(request, correct, "roundKind", "errors.investment.roundKind", entity.getRoundKind());
		}

		// Validación del Final Mode

		if (!errors.hasErrors("finalMode") && entity.getFinalMode() == true) {
			double sumaBudget = this.repository.sumBudgetWorkProgramme(entity.getId());
			double actualAmount = entity.getAmount().getAmount();
			Boolean correctAmount = actualAmount == sumaBudget;
			if (!correctAmount) {
				entity.setFinalMode(false);
			} else {
				entity.setFinalMode(true);
			}
			errors.state(request, correctAmount, "amount", "errors.investment.amount", entity.getAmount());
			errors.state(request, !finalMode, "finalMode", "errors.investment.isFinalMode", entity.getFinalMode());
		}

		// Validación del Spam

		if (!errors.hasErrors("title")) {
			Boolean isSpam = c.isSpam(entity.getTitle());
			errors.state(request, !isSpam, "title", "errors.investment.spam", entity.getTitle());
		}

		if (!errors.hasErrors("description")) {
			Boolean isSpam = c.isSpam(entity.getDescription());
			errors.state(request, !isSpam, "description", "errors.investment.spam", entity.getDescription());
		}

	}

	@Override
	public void bind(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "roundKind", "title", "description", "amount", "additionalInformation", "finalMoment");
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
