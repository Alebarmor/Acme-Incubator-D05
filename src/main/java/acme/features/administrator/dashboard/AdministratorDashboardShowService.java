
package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	private AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer numberOfNotices = this.repository.numberOfNotices();
		Integer numberOfTechnologyRecords = this.repository.numberOfTechnologyRecords();
		Integer numberOfTools = this.repository.numberOfToolRecords();

		List<Object[]> activeInquiresMoneyStats = new ArrayList<Object[]>(this.repository.activeInquiresMoneyStats());
		Double minimumMoneyActiveInquires = (Double) activeInquiresMoneyStats.get(0)[0];
		Double maximumMoneyActiveInquires = (Double) activeInquiresMoneyStats.get(0)[1];
		Double averageMoneyActiveInquires = (Double) activeInquiresMoneyStats.get(0)[2];
		Double stddevMoneyActiveInquires = (Double) activeInquiresMoneyStats.get(0)[3];

		List<Object[]> activeOverturesMoneyStats = new ArrayList<Object[]>(this.repository.activeOverturesMoneyStats());
		Double minimumMoneyActiveOvertures = (Double) activeOverturesMoneyStats.get(0)[0];
		Double maximumMoneyActiveOvertures = (Double) activeOverturesMoneyStats.get(0)[1];
		Double averageMoneyActiveOvertures = (Double) activeOverturesMoneyStats.get(0)[2];
		Double stddevMoneyActiveOvertures = (Double) activeOverturesMoneyStats.get(0)[3];

		List<Object[]> technologiesAndSectors = new ArrayList<Object[]>(this.repository.technologiesBySector());
		List<String> trSectors = new ArrayList<String>();
		List<Long> technologiesBySector = new ArrayList<Long>();
		for (Object[] cs : technologiesAndSectors) {
			trSectors.add((String) cs[0]);
			technologiesBySector.add((Long) cs[1]);
		}

		List<Object[]> technologiesAndSources = new ArrayList<Object[]>(this.repository.technologiesBySource());
		List<String> trSource = new ArrayList<String>();
		List<Long> technologiesBySource = new ArrayList<Long>();
		for (Object[] ts : technologiesAndSources) {
			trSource.add(ts[0].toString());
			technologiesBySource.add((Long) ts[1]);
		}

		if (trSource.get(0) == "true") {
			trSource.set(0, "Open Source");
			trSource.set(1, "Closed Source");
		} else {
			trSource.set(0, "Closed Source");
			trSource.set(1, "Open Source");
		}

		List<Object[]> toolsAndSectors = new ArrayList<Object[]>(this.repository.toolsBySector());
		List<String> tSectors = new ArrayList<String>();
		List<Long> toolsBySector = new ArrayList<Long>();
		for (Object[] is : toolsAndSectors) {
			tSectors.add((String) is[0]);
			toolsBySector.add((Long) is[1]);
		}

		List<Object[]> toolsAndSources = new ArrayList<Object[]>(this.repository.toolsBySource());
		List<String> tSource = new ArrayList<String>();
		List<Long> toolsBySource = new ArrayList<Long>();
		for (Object[] is : toolsAndSources) {
			tSource.add(is[0].toString());
			toolsBySource.add((Long) is[1]);
		}

		if (tSource.get(0) == "true") {
			tSource.set(0, "Open Source");
			tSource.set(1, "Closed Source");
		} else {
			tSource.set(0, "Closed Source");
			tSource.set(1, "Open Source");
		}

		entity.setNumberOfNotices(numberOfNotices);
		entity.setNumberOfTechnologyRecords(numberOfTechnologyRecords);
		entity.setNumberOfTools(numberOfTools);

		entity.setMinimumMoneyActiveInquires(minimumMoneyActiveInquires);
		entity.setMaximumMoneyActiveInquires(maximumMoneyActiveInquires);
		entity.setAverageMoneyActiveInquires(averageMoneyActiveInquires);
		entity.setStddevMoneyActiveInquires(stddevMoneyActiveInquires);

		entity.setMinimumMoneyActiveOvertures(minimumMoneyActiveOvertures);
		entity.setMaximumMoneyActiveOvertures(maximumMoneyActiveOvertures);
		entity.setAverageMoneyActiveOvertures(averageMoneyActiveOvertures);
		entity.setStddevMoneyActiveOvertures(stddevMoneyActiveOvertures);

		entity.setTrSectors(trSectors);
		entity.setTechnologiesBySector(technologiesBySector);

		entity.setTrSource(trSource);
		entity.setTechnologiesBySource(technologiesBySource);

		entity.setTSectors(tSectors);
		entity.setToolsBySector(toolsBySector);

		entity.setTSource(tSource);
		entity.setToolsBySource(toolsBySource);

		request.unbind(entity, model, "numberOfNotices", "numberOfTechnologyRecords", "numberOfTools", "minimumMoneyActiveInquires", "maximumMoneyActiveInquires", "averageMoneyActiveInquires", "stddevMoneyActiveInquires", "minimumMoneyActiveOvertures",
			"maximumMoneyActiveOvertures", "averageMoneyActiveOvertures", "stddevMoneyActiveOvertures", "trSectors", "technologiesBySector", "trSource", "technologiesBySource", "tSectors", "toolsBySector", "tSource", "toolsBySource");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		Dashboard d = new Dashboard();
		return d;
	}

}
