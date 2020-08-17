
package acme.entities.challenges;

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
public class Challenge extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotBlank
	private String				expertGoal;

	@NotNull
	private Money				expertReward;

	@NotBlank
	private String				averageGoal;

	@NotNull
	private Money				averageReward;

	@NotBlank
	private String				rookieGoal;

	@NotNull
	private Money				rookieReward; //expresado en euro zone

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
