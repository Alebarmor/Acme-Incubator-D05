
package acme.entities.technologyRecords;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TechnologyRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1l;

	@NotBlank
	private String				title;

	@NotBlank
	private String				activitySector;

	@NotBlank
	private String				inventor;

	@NotBlank
	private String				description;

	@NotBlank
	@URL
	private String				web;

	@NotBlank
	@Email
	private String				contactEmail;

	@NotNull
	//True = Free-Source | False = Closed-Source
	private Boolean				source;

	@Min(-5)
	@Max(+5)
	private Integer				stars;
}
