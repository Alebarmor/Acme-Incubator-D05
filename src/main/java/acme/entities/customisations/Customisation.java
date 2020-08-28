
package acme.entities.customisations;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customisation extends DomainEntity {

	private static final long	serialVersionUID	= 1;

	@NotBlank
	private String				spam;

	@Range(min = 0, max = 100)
	@NotNull
	private Double				threshold;

	@NotBlank
	private String				activitySectors;


	public Boolean isSpam(final String str) {
		boolean res = false;

		Double cont = 0.0;

		String[] spam = this.spam.toLowerCase().split(",");

		String[] words = str.toLowerCase().replace(".", "").replace(",", "").split(" ");

		String newStr = str.toLowerCase();

		Double n = (double) words.length;

		for (String s : spam) {
			s = s.trim();
			cont += StringUtils.countMatches(newStr, s);
		}

		Double porcentage = cont / n * 100;

		res = porcentage >= this.threshold;

		return res;
	}

	public Integer cuentaPalabras(final String s) {
		String[] words = s.split(" ");
		return words.length;
	}

}
