
package acme.forms;

import java.io.Serializable;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						numberOfNotices;
	Integer						numberOfTechnologyRecords;
	Integer						numberOfTools;

	Double						minimumMoneyActiveInquires;
	Double						maximumMoneyActiveInquires;
	Double						averageMoneyActiveInquires;
	Double						stddevMoneyActiveInquires;

	Double						minimumMoneyActiveOvertures;
	Double						maximumMoneyActiveOvertures;
	Double						averageMoneyActiveOvertures;
	Double						stddevMoneyActiveOvertures;

	Collection<String>			trSectors;
	Collection<Long>			technologiesBySector;

	Collection<String>			trSource;
	Collection<Long>			technologiesBySource;

	Collection<String>			tSectors;
	Collection<Long>			toolsBySector;

	Collection<String>			tSource;
	Collection<Long>			toolsBySource;

}
