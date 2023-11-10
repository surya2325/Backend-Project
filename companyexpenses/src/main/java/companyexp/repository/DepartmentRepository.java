package companyexp.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import companyexp.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, String> {
	

	@Query(value = "select d.deptName as department,sum(e.amt) as total_amount from Departments d join Expenditures e on d.deptCode=e.deptCode group by d.deptName\n"
			+ "",nativeQuery = true)
	List<Map<String, Object>> getDeaprtmentAndTotalAmount();

}
