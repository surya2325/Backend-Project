package companyexp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import companyexp.entities.Category;
import companyexp.repository.CategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("**")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping("/categories")
	@Operation(description = "getting categories", summary = "getting categories")
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@PostMapping("/addCategory")
	@Operation(description = "add category", summary = "add category")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "category added successfully..!"),
			@ApiResponse(responseCode = "400", description = "category code alredy present"),
			@ApiResponse(responseCode = "500", description = "internal server error"), })
	public Category addCategory(@Valid @RequestBody Category category) {
		try {
			var optCategory = categoryRepository.findById(category.getCatCode());

			if (optCategory.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "catcode already existed...!");
			}
			return categoryRepository.save(category);
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

	@DeleteMapping("/deletecategory")
	@Operation(description = "delete category", summary = "delete category")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "category deleted successfully..!"),
			@ApiResponse(responseCode = "404", description = "category code not found"),
			@ApiResponse(responseCode = "500", description = "internal server error"), })
	public String deleteCategory(@RequestParam String catcode) {
		Optional<Category> optionalCategory = categoryRepository.findById(catcode);
		if (optionalCategory.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "catcode not found");
		}
		categoryRepository.delete(optionalCategory.get());
		return "deleted succefully...!";
	}

	@PutMapping("/updateName/{catName}")
	@Operation(description = "upadate categoryname", summary = "update categoryname")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "category updated successfully..!"),
			@ApiResponse(responseCode = "404", description = "category code not found"),
			@ApiResponse(responseCode = "500", description = "internal server error"), })
	public Category updateCategoryName(@RequestParam("catCode") String catCode,
			@PathVariable("catName") String catName) {
		Optional<Category> optionalCategory = categoryRepository.findById(catCode);
		if (optionalCategory.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "catcode not found");
		}
		Category category = optionalCategory.get();
		category.setCatName(catName);
		return categoryRepository.save(category);
	}

	@GetMapping("/getCategoriesAndTotalAmount")
	@Operation(description = "get category and total amount", summary = "get category and total amount")
	public List<Map<String, Object>> getCategoriesAndTotalAmount() {
		return categoryRepository.getCategoriesAndTotalAmount();
	}
}
