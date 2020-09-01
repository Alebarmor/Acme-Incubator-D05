
package acme.features.authenticated.messages;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.messages.Message;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/message/")
public class AuthenticatedMessagesController extends AbstractController<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessagesListMineService	listMineService;

	@Autowired
	private AuthenticatedMessagesShowService		showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}

}
