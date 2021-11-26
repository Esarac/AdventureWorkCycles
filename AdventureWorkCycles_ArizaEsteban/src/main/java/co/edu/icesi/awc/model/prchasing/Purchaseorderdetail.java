package co.edu.icesi.awc.model.prchasing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the purchaseorderdetail database table.
 * 
 */
@Entity
@NamedQuery(name="Purchaseorderdetail.findAll", query="SELECT p FROM Purchaseorderdetail p")
public class Purchaseorderdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PurchaseorderdetailPK id;

	private Timestamp duedate;

	private Timestamp modifieddate;

	private Integer orderqty;

	private Integer productid;

	private BigDecimal receivedqty;

	private BigDecimal rejectedqty;

	private BigDecimal unitprice;

	//bi-directional many-to-one association to Purchaseorderheader
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name="purchaseorderid")
	private Purchaseorderheader purchaseorderheader;

	public Purchaseorderdetail() {
	}

	public PurchaseorderdetailPK getId() {
		return this.id;
	}

	public void setId(PurchaseorderdetailPK id) {
		this.id = id;
	}

	public Timestamp getDuedate() {
		return this.duedate;
	}

	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Integer getOrderqty() {
		return this.orderqty;
	}

	public void setOrderqty(Integer orderqty) {
		this.orderqty = orderqty;
	}

	public Integer getProductid() {
		return this.productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public BigDecimal getReceivedqty() {
		return this.receivedqty;
	}

	public void setReceivedqty(BigDecimal receivedqty) {
		this.receivedqty = receivedqty;
	}

	public BigDecimal getRejectedqty() {
		return this.rejectedqty;
	}

	public void setRejectedqty(BigDecimal rejectedqty) {
		this.rejectedqty = rejectedqty;
	}

	public BigDecimal getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public Purchaseorderheader getPurchaseorderheader() {
		return this.purchaseorderheader;
	}

	public void setPurchaseorderheader(Purchaseorderheader purchaseorderheader) {
		this.purchaseorderheader = purchaseorderheader;
	}

}