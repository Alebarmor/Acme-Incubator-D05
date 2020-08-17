
package acme.features.administrator.inquiries;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.inquiries.Inquire;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/inquire/")
public class AdministratorInquiriesController extends AbstractController<Administrator, Inquire> {

	@Autowired
	private AdministratorInquiriesCreateService	createService;

	@Autowired
	private AdministratorInquiriesUpdateService	updateService;

	@Autowired
	private AdministratorInquiriesDeleteService	deleteService;

	@Autowired
	private AdministratorInquiriesListService	listService;

	@Autowired
	private AdministratorInquiriesShowService	showService;


	@PostConstruct
	private void initialise() {

		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}

}
