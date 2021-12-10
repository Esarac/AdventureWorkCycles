package co.edu.icesi.awc.back.model.hr;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the employeepayhistory database table.
 * 
 */
@Entity
@NamedQuery(name="Employeepayhistory.findAll", query="SELECT e FROM Employeepayhistory e")
public class Employeepayhistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmployeepayhistoryPK id;

	private Timestamp modifieddate;

	private Integer payfrequency;

	private BigDecimal rate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name="businessentityid")
	private Employee employee;

	public Employeepayhistory() {
	}

	public EmployeepayhistoryPK getId() {
		return this.id;
	}

	public void setId(EmployeepayhistoryPK id) {
		this.id = id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Integer getPayfrequency() {
		return this.payfrequency;
	}

	public void setPayfrequency(Integer payfrequency) {
		this.payfrequency = payfrequency;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}