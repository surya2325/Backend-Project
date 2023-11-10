package companyexp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import companyexp.entities.Department;
import companyexp.entities.Expenditure;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Integer> {

	@Query("select e from Expenditure e where e.expdate between :date1 and :date2")
	List<Expenditure> findExpensesBetweenTwoDates(@Param("date1") LocalDate date1, @Param("date2") LocalDate date2,
			Sort sort);

	Optional<Expenditure> findByExpid(Integer expId);

	//5
	@Query("SELECT e from Expenditure e where e.catCode =:catCode")
	List<Expenditure> getByCatcodeAndSort(@Param("catCode") String catCode, Sort sort);

	@Query("SELECT e from Expenditure e join PaymentMode pm ON e.paymentCode=pm.paymentCode where pm.paymentName =:payMode")
	List<Expenditure> getByPaymentModeAndSort(@Param("payMode") String payMode, Sort sort);

	@Query(value = "select sum(e.amt) as total_amount, e.catcode from expenditures e where month(e.expdate) =:month group by e.catcode", nativeQuery = true)
	List<Map<String, Object>> getExpensesForEachCategory(@Param("month") Integer month);

	@Query(value = "select sum(e.amt) as total_amount, e.deptcode from expenditures e where e.expdate between :date1 and :date2 group by e.deptcode\n"
			+ "", nativeQuery = true)
	List<Map<String, Object>> getExpenditureOfDeptBetweenDates(@Param("date1") LocalDate date1,@Param("date2") LocalDate date2);

	List<Expenditure> findByAuthorizedby(String empName);
	
	List<Expenditure> findByDescriptionContaining(String searchString);
	
	@Query("select e from Expenditure e where e.amt between :min and :max")
	List<Expenditure> findExpensesBetweenMinAndMaxAmount(@Param("min") Double min, @Param("max") Double max);

}
