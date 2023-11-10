package companyexp.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "expenditures")
@Entity
public class Expenditure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expid;

	@Column(name = "catcode")
	private String catCode;

	@Column(name = "deptcode")
	private String deptCode;

	@Column(name = "paymentcode")
	private String paymentCode;

	@Column(name = "amt")
	private double amt;

	@Column(name = "expdate")
	private LocalDate expdate;

	@Column(name = "authorizedby")
	private String authorizedby;

	@Column(name = "descrip")
	private String description;

	@Column(name = "remarks")
	private String remarks;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "catcode", updatable = false, insertable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "paymentcode", updatable = false, insertable = false)
	private PaymentMode paymentMode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "deptcode", updatable = false, insertable = false)
	private Department department;

	public Expenditure() {

	}

	public Expenditure(int expid, String catCode, String deptCode, String paymentCode, double amt, LocalDate expdate,
			String authorizedby, String description, String remarks, Category category, PaymentMode paymentMode,
			Department department) {
		super();
		this.expid = expid;
		this.catCode = catCode;
		this.deptCode = deptCode;
		this.paymentCode = paymentCode;
		this.amt = amt;
		this.expdate = expdate;
		this.authorizedby = authorizedby;
		this.description = description;
		this.remarks = remarks;
		this.category = category;
		this.paymentMode = paymentMode;
		this.department = department;
	}

	public int getExpid() {
		return expid;
	}

	public void setExpid(int expid) {
		this.expid = expid;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public LocalDate getExpdate() {
		return expdate;
	}

	public void setExpdate(LocalDate expdate) {
		this.expdate = expdate;
	}

	public String getAuthorizedby() {
		return authorizedby;
	}

	public void setAuthorizedby(String authorizedby) {
		this.authorizedby = authorizedby;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Expenditure [expid=" + expid + ", catCode=" + catCode + ", deptCode=" + deptCode + ", paymentCode="
				+ paymentCode + ", amt=" + amt + ", expdate=" + expdate + ", authorizedby=" + authorizedby
				+ ", description=" + description + ", remarks=" + remarks + ", category=" + category + ", paymentMode="
				+ paymentMode + ", department=" + department + "]";
	}

}
