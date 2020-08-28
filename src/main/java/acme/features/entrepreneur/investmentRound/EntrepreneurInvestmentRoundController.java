
package acme.features.entrepreneur.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/investment/")
public class EntrepreneurInvestmentRoundController extends AbstractController<Entrepreneur, Investment> {

	@Autowired
	private EntrepreneurInvestmentRoundsListMineService	listService;

	@Autowired
	private EntrepreneurInvestmentRoundsShowService		showService;

	@Autowired
	private EntrepreneurInvestmentRoundUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}

}
