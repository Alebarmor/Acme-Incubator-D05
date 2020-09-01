
package acme.features.anonymous.barbaBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.BarbaBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("anonymous/barba-bulletin/")
public class AnonymousBarbaBulletinController extends AbstractController<Anonymous, BarbaBulletin> {

	@Autowired
	private AnonymousBarbaBulletinListService	listService;

	@Autowired
	private AnonymousBarbaBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
