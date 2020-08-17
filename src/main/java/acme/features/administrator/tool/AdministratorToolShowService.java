
package acme.features.administrator.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tools.Tool;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorToolShowService implements AbstractShowService<Administrator, Tool> {

	@Autowired
	private AdministratorToolRepository repository;


	@Override
	public boolean authorise(final Request<Tool> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Tool> request, final Tool entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "sector", "inventorName", "description", "website", "email", "isOpenSource", "starsNumber");
	}

	@Override
	public Tool findOne(final Request<Tool> request) {
		assert request != null;

		Tool result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolById(id);

		return result;
	}

}
