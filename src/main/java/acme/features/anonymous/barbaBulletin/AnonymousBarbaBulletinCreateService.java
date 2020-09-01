
package acme.features.anonymous.barbaBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.BarbaBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousBarbaBulletinCreateService implements AbstractCreateService<Anonymous, BarbaBulletin> {

	@Autowired
	AnonymousBarbaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<BarbaBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<BarbaBulletin> request, final BarbaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<BarbaBulletin> request, final BarbaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");

	}

	@Override
	public BarbaBulletin instantiate(final Request<BarbaBulletin> request) {
		assert request != null;

		BarbaBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new BarbaBulletin();
		result.setAuthor("Alejandro Barba");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<BarbaBulletin> request, final BarbaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<BarbaBulletin> request, final BarbaBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
