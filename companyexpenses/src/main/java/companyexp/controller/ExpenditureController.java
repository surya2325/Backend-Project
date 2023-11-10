package companyexp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import companyexp.entities.Expenditure;
import companyexp.repository.ExpenditureRepository;

@RestController
@CrossOrigin("**")
public class ExpenditureController {
	@Autowired
	private ExpenditureRepository expenditureRepository;

	@GetMapping("/Expenses")
	public List<Expenditure> getExpenses() {
		return expenditureRepository.findAll();
	}

	@PostMapping("/addexpenditure")
	public Expenditure addExpenditure(@RequestBody Expenditure expenditure) {
		try {
			expenditureRepository.save(expenditure);
			return expenditure;
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	@PutMapping("/updateCatcode/{amount}")
	public Expenditure updateCatCode(@PathVariable("amount") Double amount, @RequestParam("expId") int expId) {
		Optional<Expenditure> optionalExpenditure = expenditureRepository.findByExpid(expId);
		if (optionalExpenditure.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found...!");
		}
		Expenditure expenditure = optionalExpenditure.get();
		expenditure.setAmt(amount);
		return expenditureRepository.save(expenditure);
	}

	@DeleteMapping("/deleteExpenditure")
	public String deleteExpenditure(@RequestParam Integer expId) {
		Optional<Expenditure> optionalExpenditure = expenditureRepository.findById(expId);
		if (optionalExpenditure.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found...!");
		}
		expenditureRepository.deleteById(expId);
		return "Deleted successfully...!";
	}

	//5
	@GetMapping("/getExpensesByCatCodeSortById/{catCode}")
	public List<Expenditure> getExpensesByCatCodeSortById(@PathVariable String catCode) {
		List<Expenditure> expenditures = expenditureRepository.getByCatcodeAndSort(catCode,Sort.by("expid").ascending());
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Catcode not found....!");

		else
			return expenditures;
	}

	@GetMapping("/getExpensesByPaymentModeSortById/{payMode}")
	public List<Expenditure> getExpensesByPaymentModeSortById(@PathVariable String payMode) {
		List<Expenditure> expenditures = expenditureRepository.getByPaymentModeAndSort(payMode,
				Sort.by("expid").ascending());
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");

		else
			return expenditures;
	}

	@GetMapping("/getExpensesBetweenTwoDates/{date1}/{date2}")
	public List<Expenditure> getExpensesBetweenTwoDates(@PathVariable("date1") LocalDate date1,
			@PathVariable("date2") LocalDate date2) {
		List<Expenditure> expenditures = expenditureRepository.findExpensesBetweenTwoDates(date1, date2,
				Sort.by("expdate").descending());
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");

		else
			return expenditures;
	}

	@GetMapping("/getExpensesForEachCategory/{month}")
	public List<Map<String, Object>> getExpensesForEachCategory(@PathVariable("month") Integer month) {
		List<Map<String, Object>> expenditures = expenditureRepository.getExpensesForEachCategory(month);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");

		else
			return expenditures;

	}

	@GetMapping("/getExpensesOfDeptBetweenDates/{date1}/{date2}")
	public List<Map<String, Object>> getExpensesOfDeptBetweenDates(@PathVariable("date1") LocalDate date1,
			@PathVariable("date2") LocalDate date2) {
		List<Map<String, Object>> expenditures = expenditureRepository.getExpenditureOfDeptBetweenDates(date1, date2);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");

		else
			return expenditures;
	}

	@GetMapping("/getExpensesByAuthorizedBy/{empName}")
	public List<Expenditure> getExpensesByAuthorizedBy(@PathVariable("empName") String empName) {
		List<Expenditure> expenditures = expenditureRepository.findByAuthorizedby(empName);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");

		else
			return expenditures;
	}

	@GetMapping("/getExpensesByDescOfGivenString/{name}")
	public List<Expenditure> getExpensesByDescOfGivenString(@PathVariable("name") String name) {
		List<Expenditure> expenditures = expenditureRepository.findByDescriptionContaining(name);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");

		else
			return expenditures;
	}

	@GetMapping("/getExpensesBetweenMinAndMaxAmount/{min}/{max}")
	public List<Expenditure> getExpensesBetweenMinAndMaxAmount(@PathVariable("min") Double min,
			@PathVariable("max") Double max) {
		List<Expenditure> expenditures = expenditureRepository.findExpensesBetweenMinAndMaxAmount(min, max);
		if (expenditures.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found....!");

		else
			return expenditures;
	}

}
