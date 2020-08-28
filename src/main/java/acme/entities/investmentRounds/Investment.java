
package acme.entities.investmentRounds;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

/*
 * for which the system must store the following data: a ticker, a creation date and moment,
 * a kind of round, a title, a description, an amount of money, an optional link with additional
 * information, and a work programme. The work programme consists of one
 * or more activities, each of which has a title, a start date and time, an end date and time,
 * and a budget. Note that the budget of the activities must sum up to the amount of money in the
 * corresponding investment round. The kind of an investment round
 * can be either “SEED”, “ANGEL”, “SERIES-A”, “SERIES-B”, “SERIES-C”, or “BRIDGE”.
 */

@Entity
@Getter
@Setter
@Table(indexes = @Index(columnList = "ticker"))
public class Investment extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}[-][0-9]{2}[-][0-9]{6}$")
	private String				ticker;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@NotNull
	private RoundKind			roundKind;

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@NotNull
	@Valid
	private Money				amount;

	@NotNull
	private Boolean				finalMode;

	@URL
	private String				additionalInformation;

	// Relationships --------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur		entrepreneur;

}
