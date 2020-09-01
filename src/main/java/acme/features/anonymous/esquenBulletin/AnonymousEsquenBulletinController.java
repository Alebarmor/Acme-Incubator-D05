
package acme.features.anonymous.esquenBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.EsquenBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/esquen-bulletin/")
public class AnonymousEsquenBulletinController extends AbstractController<Anonymous, EsquenBulletin> {

	@Autowired
	private AnonymousEsquenBulletinListService		listService;

	@Autowired
	private AnonymousEsquenBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
