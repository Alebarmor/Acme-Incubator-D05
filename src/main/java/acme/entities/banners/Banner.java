
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@URL
	private String				picture;

	@NotBlank
	private String				slogan;

	@NotBlank
	@URL
	private String				url;

	private String				holder;

	@CreditCardNumber
	private String				number;

	private String				brand;

	@Pattern(regexp = "[0-9]{2}/[0-9]{4}")
	private String				expirationDate;

	@Pattern(regexp = "^[0-9]{3}$")
	private String				cvv;

}
