
package acme.features.entrepreneur.investmentRound;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, Investment> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	// AbstractCreateService<Entrepreneur, Investment> interface ---------------------------------------

	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "entrepreneur");
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "roundKind", "title", "description", "description", "amount", "additionalInformation", "finalMode");
	}

	@Override
	public Investment instantiate(final Request<Investment> request) {
		Investment result;
		result = new Investment();

		Principal principal;
		int userAccountId;
		Entrepreneur Entrepreneur;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		Entrepreneur = this.repository.findOneEntrepreneurByUserAccount(userAccountId);

		result.setEntrepreneur(Entrepreneur);
		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isPast, isPositive, isEuro;

		// Validación del ticker unique
		Boolean unique = null;
		unique = this.repository.findTickerOfInvestment(entity.getTicker()) != null;
		errors.state(request, !unique, "ticker", "errors.Investment.ticker.unique", "The ticker must be unique");

		// Validación de fecha de creación
		if (!errors.hasErrors("creationMoment")) {
			Date fechaActual, fecha;
			fechaActual = new Date();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaActual);
			calendar.add(Calendar.DAY_OF_WEEK, 7);
			fecha = calendar.getTime();

			isPast = entity.getCreationMoment().before(fecha);
			errors.state(request, isPast, "creationMoment", "errors.investment.deadline.future", "Creation Moment must be in past and within a week");
		}

		// Validación dinero positivo
		if (!errors.hasErrors("amount")) {
			isPositive = entity.getAmount().getAmount() > 0;
			errors.state(request, isPositive, "amount", "errors.investment.amount.money.amount-positive", "The amount must be positive");
		}

		// Validación moneda
		if (!errors.hasErrors("salary")) {
			isEuro = entity.getAmount().getCurrency().equals("EUR") || entity.getAmount().getCurrency().equals("€");
			errors.state(request, isEuro, "amount", "errors.investment.amount.money.euro", "The money must be in euro '€' / 'EUR'");
		}

		// Validación de Spam
		if (!errors.hasErrors("ticker")) {
			Boolean isSpam = this.spam(entity.getTicker());
			errors.state(request, !isSpam, "ticker", "errors.investment.description.spam", "Contain spam words");
		}

		if (!errors.hasErrors("title")) {
			Boolean isSpam = this.spam(entity.getTitle());
			errors.state(request, !isSpam, "title", "errors.investment.description.spam", "Contain spam words");
		}

		if (!errors.hasErrors("description")) {
			Boolean isSpam = this.spam(entity.getDescription());
			errors.state(request, !isSpam, "description", "errors.investment.description.spam", "Contain spam words");
		}

		if (!errors.hasErrors("additionalInformation")) {
			Boolean isSpam = this.spam(entity.getAdditionalInformation());
			errors.state(request, !isSpam, "additionalInformation", "errors.investment.description.spam", "Contain spam words");
		}

	}

	@Override
	public void create(final Request<Investment> request, final Investment entity) {
		this.repository.save(entity);
	}

	private Boolean spam(final String string) {
		Boolean result = false;
		String spam = this.repository.findCustomisation().getSpamWords();
		String[] listaSpam = spam.trim().split(",");
		for (String palabra : listaSpam) {
			if (string.contains(palabra)) {
				result = true;
			}
		}
		return result;
	}
}
