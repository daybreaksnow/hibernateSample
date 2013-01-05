package inaction.association;

// Generated 2013/01/04 18:39:30 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Bid generated by hbm2java
 */
public class Bid implements java.io.Serializable {

	private long bidId;
	private Item item;
	private Long amount;
	private Long userId;
	private Date createdDate;

	public Bid() {
	}

	public Bid(long bidId) {
		this.bidId = bidId;
	}

	public Bid(long bidId, Item item, Long amount, Long userId, Date createdDate) {
		this.bidId = bidId;
		this.item = item;
		this.amount = amount;
		this.userId = userId;
		this.createdDate = createdDate;
	}

	public long getBidId() {
		return this.bidId;
	}

	public void setBidId(long bidId) {
		this.bidId = bidId;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Bid [bidId=" + bidId + ", amount=" + amount + ", userId="
				+ userId + ", createdDate=" + createdDate + "]";
	}

}
