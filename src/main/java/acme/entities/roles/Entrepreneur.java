
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entrepreneur extends UserRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				startUpName;

	@NotBlank
	@Length(min = 3)
	private String				sector;

	@NotBlank
	private String				qualifications;

	@NotBlank
	private String				skills;

}
