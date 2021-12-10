package co.edu.icesi.awc.back.model.hr;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the employeedepartmenthistory database table.
 * 
 */
@Entity
@NamedQuery(name="Employeedepartmenthistory.findAll", query="SELECT e FROM Employeedepartmenthistory e")
public class Employeedepartmenthistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmployeedepartmenthistoryPK id;

	@Temporal(TemporalType.DATE)
	private Date enddate;

	private Timestamp modifieddate;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name="departmentid")
	private Department department;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name="businessentityid")
	private Employee employee;

	//bi-directional many-to-one association to Shift
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name="shiftid")
	private Shift shift;

	public Employeedepartmenthistory() {
	}

	public EmployeedepartmenthistoryPK getId() {
		return this.id;
	}

	public void setId(EmployeedepartmenthistoryPK id) {
		this.id = id;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Shift getShift() {
		return this.shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

}