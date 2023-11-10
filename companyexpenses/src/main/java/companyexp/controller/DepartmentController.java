package companyexp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

import companyexp.entities.Department;
import companyexp.repository.DepartmentRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("**")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("/departments")
	@Operation(description = "get all departments", summary = "get all departments")
	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}

	@PostMapping("/addDepartment")
	@Operation(description = " add department", summary = "add department")
	public Department addDepartment(@RequestBody Department department) {
		return departmentRepository.save(department);
	}

	@DeleteMapping("/deletedepartment")
	@Operation(description = " delete department", summary = "delete department")
	public String deleteDepartment(@RequestParam String deptcode) {
		Optional<Department> optionalDepartment = departmentRepository.findById(deptcode);
		if (optionalDepartment.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"deptcode not found");
		}
		departmentRepository.delete(optionalDepartment.get());
		return "deleted succefully...!";

	}

	@PutMapping("/updateDepartment/{deptName}")
	@Operation(description = " update department name", summary = "update department")
	public Department updateDepartment(@RequestParam("deptCode") String deptCode,@PathVariable("deptName") String deptName) {
		Optional<Department> optionalDepartment = departmentRepository.findById(deptCode);
		if (optionalDepartment.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"deptcode not found");
		}
		Department department = optionalDepartment.get();
		department.setDeptName(deptName);
		return departmentRepository.save(department);
	}
	
	
	@GetMapping("/getDeaprtmentAndTotalAmount")
	public List<Map<String, Object>> getDeaprtmentAndTotalAmount(){
		return departmentRepository.getDeaprtmentAndTotalAmount();
	}

}
