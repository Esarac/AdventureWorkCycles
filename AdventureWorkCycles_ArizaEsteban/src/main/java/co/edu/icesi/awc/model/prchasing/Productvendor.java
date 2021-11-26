package co.edu.icesi.awc.model.prchasing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the productvendor database table.
 * 
 */
@Entity
@NamedQuery(name="Productvendor.findAll", query="SELECT p FROM Productvendor p")
public class Productvendor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductvendorPK id;

	private Integer averageleadtime;

	private BigDecimal lastreceiptcost;

	private Timestamp lastreceiptdate;

	private Integer maxorderqty;

	private Integer minorderqty;

	private Timestamp modifieddate;

	private Integer onorderqty;

	private BigDecimal standardprice;

	private String unitmeasurecode;

	//bi-directional many-to-one association to Vendor
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name="businessentityid")
	private Vendor vendor;

	public Productvendor() {
	}

	public ProductvendorPK getId() {
		return this.id;
	}

	public void setId(ProductvendorPK id) {
		this.id = id;
	}

	public Integer getAverageleadtime() {
		return this.averageleadtime;
	}

	public void setAverageleadtime(Integer averageleadtime) {
		this.averageleadtime = averageleadtime;
	}

	public BigDecimal getLastreceiptcost() {
		return this.lastreceiptcost;
	}

	public void setLastreceiptcost(BigDecimal lastreceiptcost) {
		this.lastreceiptcost = lastreceiptcost;
	}

	public Timestamp getLastreceiptdate() {
		return this.lastreceiptdate;
	}

	public void setLastreceiptdate(Timestamp lastreceiptdate) {
		this.lastreceiptdate = lastreceiptdate;
	}

	public Integer getMaxorderqty() {
		return this.maxorderqty;
	}

	public void setMaxorderqty(Integer maxorderqty) {
		this.maxorderqty = maxorderqty;
	}

	public Integer getMinorderqty() {
		return this.minorderqty;
	}

	public void setMinorderqty(Integer minorderqty) {
		this.minorderqty = minorderqty;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Integer getOnorderqty() {
		return this.onorderqty;
	}

	public void setOnorderqty(Integer onorderqty) {
		this.onorderqty = onorderqty;
	}

	public BigDecimal getStandardprice() {
		return this.standardprice;
	}

	public void setStandardprice(BigDecimal standardprice) {
		this.standardprice = standardprice;
	}

	public String getUnitmeasurecode() {
		return this.unitmeasurecode;
	}

	public void setUnitmeasurecode(String unitmeasurecode) {
		this.unitmeasurecode = unitmeasurecode;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}