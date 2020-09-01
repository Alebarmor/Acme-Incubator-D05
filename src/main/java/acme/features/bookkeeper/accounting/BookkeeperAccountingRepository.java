
package acme.features.bookkeeper.accounting;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkeeperAccountingRepository extends AbstractRepository {

	@Query("select ar from AccountingRecord ar where ar.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select ar from AccountingRecord ar")
	Collection<AccountingRecord> findManyAll();

	@Query("select ar from AccountingRecord ar where ar.investment.id =?1")
	Collection<AccountingRecord> findAllAccountingRecordsById(int id);

}
