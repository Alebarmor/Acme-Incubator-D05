
package acme.entities.inquiries;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

/*
 * The system must store inquiries, each of which consists of a title,
 * a creation date and time, a deadline date and time, one or more paragraphs
 * that describe it, a money interval, and a contact e-mail.
 */

@Entity
@Getter
@Setter

public class Inquire extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				paragraphs;

	@NotNull
	private Money				minMoney;

	@NotNull
	private Money				maxMoney;

	@NotBlank
	@Email
	private String				email;

}
