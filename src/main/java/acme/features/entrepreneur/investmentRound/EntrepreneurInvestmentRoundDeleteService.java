
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.features.entrepreneur.activity.EntrepreneurActivityRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, Investment> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	EntrepreneurActivityRepository			entrepreneurActivitiesRepository;


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
	public void validate(final Request<Investment> request, final Investment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Validation: An investment round can be deleted as long as
		// no investor has applied for it, independently from whether it is in draft or final mode
		Collection<Application> applications;
		applications = this.repository.findApplicationByInvestmentId(entity.getId());

		if (applications != null && !applications.isEmpty()) {
			errors.state(request, false, "finalMode", "errors.investment.hasApplications", "An investment round can be deleted as long as no investor has applied for it");
		}

	}

	@Override
	public void delete(final Request<Investment> request, final Investment entity) {
		assert request != null;
		assert entity != null;

		Collection<Activity> activities = this.repository.findActivitiesByInvestmentId(entity.getId());
		this.entrepreneurActivitiesRepository.deleteAll(activities);

		this.repository.delete(entity);
	}

}
