package companyexp.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import companyexp.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

	@Query(value = "select c.catname as category,sum(e.amt) as total_amount from Categories c join Expenditures e on c.catCode=e.catCode group by c.catName\n"
			+ "",nativeQuery = true)
	List<Map<String, Object>> getCategoriesAndTotalAmount();
}
