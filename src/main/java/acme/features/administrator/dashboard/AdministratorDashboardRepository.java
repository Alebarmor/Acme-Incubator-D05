
package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(n) from Notice n")
	Integer numberOfNotices();

	@Query("select count(tr) from TechnologyRecord tr")
	Integer numberOfTechnologyRecords();

	@Query("select count(t) from Tool t")
	Integer numberOfToolRecords();

	@Query("select min(i.minMoney.amount), max(i.maxMoney.amount), avg(i.minMoney.amount + i.maxMoney.amount), stddev(i.minMoney.amount + i.maxMoney.amount) from Inquire i where i.deadline > CURRENT_TIMESTAMP")
	Collection<Object[]> activeInquiresMoneyStats();

	@Query("select min(o.minMoney.amount), max(o.maxMoney.amount), avg(o.minMoney.amount + o.maxMoney.amount), stddev(o.minMoney.amount + o.maxMoney.amount) from Overture o where o.deadline > CURRENT_TIMESTAMP")
	Collection<Object[]> activeOverturesMoneyStats();

	@Query("select tr.activitySector, count(tr) from TechnologyRecord tr group by tr.activitySector")
	Collection<Object[]> technologiesBySector();

	@Query("select tr.source, count(tr) from TechnologyRecord tr group by tr.source")
	Collection<Object[]> technologiesBySource();

	@Query("select t.sector, count(t) from Tool t group by t.sector")
	Collection<Object[]> toolsBySector();

	@Query("select t.isOpenSource, count(t) from Tool t group by t.isOpenSource")
	Collection<Object[]> toolsBySource();

}
