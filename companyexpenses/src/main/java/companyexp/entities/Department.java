package companyexp.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Table(name = "departments")
@Entity
public class Department {
	@Id
	@Column(name = "deptcode")
	@NotBlank(message = "department code can not be null")
	@Size(max = 5, message = "department code can not be greater than 5")
	private String deptCode;
	
	
	@Column(name = "deptname")
	@NotBlank(message = "department code can not be null")
	@Size(max = 5, message = "department name can not be greater than 30")
	private String deptName;
	@Column(name = "hod")
	private String hod;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "department")
	@JsonIgnore
	private List<Expenditure> expenditures;

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public List<Expenditure> getExpenditures() {
		return expenditures;
	}

	public void setExpenditures(List<Expenditure> expenditures) {
		this.expenditures = expenditures;
	}

}
