
package acme.features.authenticated.inquiries;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.inquiries.Inquire;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/inquire/")
public class AuthenticatedInquiriesController extends AbstractController<Authenticated, Inquire> {

	@Autowired
	private AuthenticatedInquiriesListService	listService;

	@Autowired
	private AuthenticatedInquiriesShowService	showService;

	@Autowired
	private AuthenticatedInquiriesCreateService	createService;

	@Autowired
	private AuthenticatedInquiriesUpdateService	updateService;

	@Autowired
	private AuthenticatedInquiriesDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}

}
