
package acme.entities.investmentRounds;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Activity extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	/*
	 * The work programme consists of one
	 * or more activities, each of which has a title, a start date and
	 * time, an end date and time,
	 * and a budget. Note that the budget of the activities must sum up to
	 * the amount of money in the
	 * corresponding investment round.
	 */

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				start;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				end;

	@NotNull
	private Money				budget;

}
