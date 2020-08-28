
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository			repository;

	@Autowired
	private AdministratorCustomisationRepository	spamRepository;


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

		request.bind(entity, errors, "entrepreneur", "creationMoment");
	}

	@Override
	public void unbind(final Request<Investment> request, final Investment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "roundKind", "title", "description", "description", "amount", "additionalInformation", "finalMode");
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

		int id = entity.getId();
		Boolean finalMode = this.repository.isFinalMode(id);

		List<Customisation> ca = (List<Customisation>) this.spamRepository.findManyAll();
		Customisation c = ca.get(0);

		// Validación del Round Kind

		if (!errors.hasErrors("roundKind")) {
			List<String> kinds = new ArrayList<String>(Arrays.asList("SEED", "ANGEL", "SERIES-A", "SERIES-B", "SERIES-C", "BRIDGE"));
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
	public void create(final Request<Investment> request, final Investment entity) {
		Date creationMomentDate;
		creationMomentDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMomentDate);
		this.repository.save(entity);
	}

}
