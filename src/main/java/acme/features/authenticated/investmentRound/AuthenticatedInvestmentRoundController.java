
package acme.features.authenticated.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investmentRounds.Investment;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/investment/")
public class AuthenticatedInvestmentRoundController extends AbstractController<Authenticated, Investment> {

	@Autowired
	private AuthenticatedInvestmentRoundsListService	listService;

	@Autowired
	private AuthenticatedInvestmentRoundsShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
