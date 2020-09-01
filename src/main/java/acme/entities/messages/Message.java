
package acme.entities.messages;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.entities.forums.Forum;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Message extends DomainEntity {

	/* title, a creation moment, an optional list of tags, and a body */

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotNull
	@Valid
	@Past
	private Date				creationMoment;

	private String				tags;

	@NotBlank
	private String				body;

	// Relationships ------

	@ManyToOne
	private Forum				forum;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		user;

}
