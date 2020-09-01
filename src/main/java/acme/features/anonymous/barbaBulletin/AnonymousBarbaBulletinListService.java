
package acme.features.anonymous.barbaBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.BarbaBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousBarbaBulletinListService implements AbstractListService<Anonymous, BarbaBulletin> {

	@Autowired
	AnonymousBarbaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<BarbaBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<BarbaBulletin> request, final BarbaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");

	}

	@Override
	public Collection<BarbaBulletin> findMany(final Request<BarbaBulletin> request) {
		assert request != null;

		Collection<BarbaBulletin> result;

		result = this.repository.findMany();

		return result;
	}

}
