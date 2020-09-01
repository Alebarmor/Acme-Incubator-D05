
package acme.features.anonymous.esquenBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.EsquenBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousEsquenBulletinCreateService implements AbstractCreateService<Anonymous, EsquenBulletin> {

	@Autowired
	AnonymousEsquenBulletinRepository repository;


	@Override
	public boolean authorise(final Request<EsquenBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<EsquenBulletin> request, final EsquenBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<EsquenBulletin> request, final EsquenBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");

	}

	@Override
	public EsquenBulletin instantiate(final Request<EsquenBulletin> request) {
		assert request != null;

		EsquenBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new EsquenBulletin();
		result.setAuthor("Lizseth Katherine Esquen");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<EsquenBulletin> request, final EsquenBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<EsquenBulletin> request, final EsquenBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
