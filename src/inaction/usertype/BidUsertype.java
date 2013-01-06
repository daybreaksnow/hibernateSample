package inaction.usertype;

// Generated 2013/01/06 11:47:38 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * BidUsertype generated by hbm2java
 */
public class BidUsertype implements java.io.Serializable {

	private long bidId;
	private Integer version;
	private MonetaryAmount amount;
	private Date createdDate;

	public BidUsertype() {
	}

	public BidUsertype(long bidId) {
		this.bidId = bidId;
	}

	public BidUsertype(long bidId, MonetaryAmount amount, Date createdDate) {
		this.bidId = bidId;
		this.amount = amount;
		this.createdDate = createdDate;
	}

	public long getBidId() {
		return this.bidId;
	}

	public void setBidId(long bidId) {
		this.bidId = bidId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public MonetaryAmount getAmount() {
		return this.amount;
	}

	public void setAmount(MonetaryAmount amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}