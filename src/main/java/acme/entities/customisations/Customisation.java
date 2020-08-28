/*
 * AcmeRequest.java
 *
 * Copyright (c) 2019 Aureliano Piqueras, based on Rafael Corchuelo's DP Starter project.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.entities.customisations;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
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
@Table(indexes = {
	@Index(columnList = "activeID", unique = true)
})
public class Customisation extends DomainEntity {

	// Serialization identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

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
