
package acme.features.administrator.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	// Internal state ---------------------------------------------------------------------------------

	@Autowired
	AdministratorChallengeRepository repository;


	// AbstractCreateService<Administrator, Challenge> interface --------------------------------------

	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "expertGoal", "expertReward", "averageGoal", "averageReward", "rookieGoal", "rookieReward");
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		Challenge result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findChallengeById(id);

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Boolean isFuture, isPositiveExpert, isPositiveAverage, isPositiveRookie, isEuroExpert, isEuroAverage, isEuroRookie, isExpert, isAverage, isRookie;

		// Validación de fecha futura
		Date fechaActual;
		fechaActual = new Date();
		isFuture = entity.getDeadline().after(fechaActual);
		errors.state(request, isFuture, "deadline", "errors.challenge.deadline.future", "Deadline must be in future");

		// Validación dinero positivo
		isPositiveExpert = entity.getExpertReward().getAmount() > 0;
		errors.state(request, isPositiveExpert, "expertReward", "errors.challenge.reward.money.amount-positive", "The amount must be positive");

		isPositiveAverage = entity.getAverageReward().getAmount() > 0;
		errors.state(request, isPositiveAverage, "averageReward", "errors.challenge.reward.money.amount-positive", "The amount must be positive");

		isPositiveRookie = entity.getExpertReward().getAmount() > 0;
		errors.state(request, isPositiveRookie, "rookieReward", "errors.challenge.reward.money.amount-positive", "The amount must be positive");

		// Validación moneda
		isEuroExpert = entity.getExpertReward().getCurrency().equals("EUR") || entity.getExpertReward().getCurrency().equals("€");
		errors.state(request, isEuroExpert, "expertReward", "errors.challenge.reward.money.euro", "The money must be in euro '€' / 'EUR'");

		isEuroAverage = entity.getAverageReward().getCurrency().equals("EUR") || entity.getAverageReward().getCurrency().equals("€");
		errors.state(request, isEuroAverage, "averageReward", "errors.challenge.reward.money.euro", "The money must be in euro '€' / 'EUR'");

		isEuroRookie = entity.getRookieReward().getCurrency().equals("EUR") || entity.getRookieReward().getCurrency().equals("€");
		errors.state(request, isEuroRookie, "rookieReward", "errors.challenge.reward.money.euro", "The money must be in euro '€' / 'EUR'");

		// Validación cantidad orden
		isExpert = entity.getExpertReward().getAmount() > entity.getAverageReward().getAmount() && entity.getExpertReward().getAmount() > entity.getRookieReward().getAmount();
		errors.state(request, isExpert, "expertReward", "errors.challenge.goldReward.amount-major", "The amount of gold must be higher than silver and bronze");

		isAverage = entity.getAverageReward().getAmount() < entity.getExpertReward().getAmount() && entity.getAverageReward().getAmount() > entity.getRookieReward().getAmount();
		errors.state(request, isAverage, "averageReward", "errors.challenge.silverReward.amount-medium", "The amount of silver must be higher than bronze and lower than gold");

		isRookie = entity.getRookieReward().getAmount() < entity.getAverageReward().getAmount() && entity.getRookieReward().getAmount() < entity.getExpertReward().getAmount();
		errors.state(request, isRookie, "rookieReward", "errors.challenge.bronzeReward.amount-minor", "The amount of bronze must be lower than silver and gold");
	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
