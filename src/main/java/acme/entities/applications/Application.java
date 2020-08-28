
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.investmentRounds.Investment;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	/*
	 * a ticker, a creation date and moment,
	 * a statement, and an investment money offer.
	 */

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}[-][0-9]{2}[-][0-9]{6}$", message = "{entrepreneur.application.ticker.pattern}")
	private String				ticker;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@NotBlank
	private String				statement;

	@NotNull
	@Valid
	private Money				offer;

	// Relationships ----------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Investment			investmentRound;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Investor			investor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur		entrepreneur;

	// Derived attributes

	/*
	 * @Transient
	 * public Entrepreneur getEntrepreneur() {
	 * 
	 * Entrepreneur result;
	 * result = new Entrepreneur();
	 * result = this.investmentRound.getEntrepreneur();
	 * 
	 * return result;
	 * }
	 */

}
