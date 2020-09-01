
package acme.features.anonymous.esquenBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.EsquenBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousEsquenBulletinListService implements AbstractListService<Anonymous, EsquenBulletin> {

	@Autowired
	AnonymousEsquenBulletinRepository repository;


	@Override
	public boolean authorise(final Request<EsquenBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<EsquenBulletin> request, final EsquenBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");

	}

	@Override
	public Collection<EsquenBulletin> findMany(final Request<EsquenBulletin> request) {
		assert request != null;

		Collection<EsquenBulletin> result;

		result = this.repository.findMany();

		return result;
	}

}
