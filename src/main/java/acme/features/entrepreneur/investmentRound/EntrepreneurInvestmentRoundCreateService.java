
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisations.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, Investment> {

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	AdministratorCustomisationRepository	spamRepository;


	@Override
	public boolean authorise(final Request<Investment> request) {
		assert request != null;

		boolean result;
		Entrepreneur entrepreneur;
		Principal principal;

		entrepreneur = this.repository.findOneEntrepreneurByUserAccount(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "ticker", "creationMoment", "finalMode");
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "roundKind", "title", "description", "amount", "additionalInformation");
	}

	@Override
	public Investment instantiate(final Request<Investment> request) {
		Investment result;
		result = new Investment();

		Entrepreneur Entrepreneur;
		Entrepreneur = this.repository.findOneEntrepreneurByUserAccount(request.getPrincipal().getActiveRoleId());

		Date date = new Date(System.currentTimeMillis() - 1);

		String ticker = this.randomValidTicker(Entrepreneur.getSector(), date);

		result.setEntrepreneur(Entrepreneur);
		result.setFinalMode(false);
		result.setCreationMoment(date);
		result.setTicker(ticker);

		return result;
	}

	@Override
	public void validate(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<Customisation> ca = (List<Customisation>) this.spamRepository.findManyAll();
		Customisation c = ca.get(0);

		// Validación del Round Kind

		if (!errors.hasErrors("roundKind")) {
			List<String> kinds = new ArrayList<String>(Arrays.asList("SEED", "ANGEL", "SERIES_A", "SERIES_B", "SERIES_C", "BRIDGE"));
			Boolean correct = kinds.contains(entity.getRoundKind().toString());
			errors.state(request, correct, "roundKind", "errors.investment.roundKind");
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
	public void create(final Request<Investment> request, final Investment entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	public String randomValidTicker(final String a, final Date d) {
		int n = 0;
		Boolean unique = null;

		String ticker = "";

		while (n == 0) {
			String first = String.valueOf(new Random().nextInt(9));
			String second = String.valueOf(new Random().nextInt(9));
			String third = String.valueOf(new Random().nextInt(9));
			String fourth = String.valueOf(new Random().nextInt(9));
			String fifth = String.valueOf(new Random().nextInt(9));
			String sixth = String.valueOf(new Random().nextInt(9));
			String numbers = first + second + third + fourth + fifth + sixth;
			ticker = this.generateTicker(a, d, numbers);
			unique = this.repository.findInvestmentByTicker(ticker) == null;
			if (unique) {
				n++;
			}
		}

		String res = ticker;

		return res;
	}

	public String generateTicker(final String a, final Date d, final String str) {
		String res;

		String activitySector = a;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		String year = String.valueOf(calendar.get(Calendar.YEAR));

		activitySector = activitySector.substring(0, 3).toUpperCase();
		year = year.substring(year.length() - 2);

		res = activitySector + "-" + year + "-" + str;

		return res;
	}

}
