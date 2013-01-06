package inaction.association;

// Generated 2013/01/04 18:39:30 by Hibernate Tools 3.4.0.CR1

import inaction.fetch.ItemCategoryMapping;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Item generated by hbm2java
 */
public class Item implements java.io.Serializable {

	private long itemId;
	private String name;
	private String description;
	private Long initialPrice;
	private Long reservePrice;
	private Date startDate;
	private Date endDate;
	private Date createdDate;
	/** ItemImageのfileNameカラムとマッピングしている */
	private Set<String> itemImages = new HashSet(0);
	private Set itemCategoryMappings = new HashSet(0);
	private Set bids = new HashSet(0);

	public Item() {
	}

	public Item(long itemId) {
		this.itemId = itemId;
	}

	public Item(long itemId, String name, String description,
			Long initialPrice, Long reservePrice, Date startDate, Date endDate,
			Date createdDate, Set bids) {
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.initialPrice = initialPrice;
		this.reservePrice = reservePrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdDate = createdDate;
		this.bids = bids;
	}

	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getInitialPrice() {
		return this.initialPrice;
	}

	public void setInitialPrice(Long initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Long getReservePrice() {
		return this.reservePrice;
	}

	public void setReservePrice(Long reservePrice) {
		this.reservePrice = reservePrice;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<String> getItemImages() {
		return this.itemImages;
	}

	public void setItemImages(Set<String> itemImages) {
		this.itemImages = itemImages;
	}

	public Set<ItemCategoryMapping> getItemCategoryMappings() {
		return this.itemCategoryMappings;
	}

	public void setItemCategoryMappings(Set itemCategoryMappings) {
		this.itemCategoryMappings = itemCategoryMappings;
	}

	public Set getBids() {
		return this.bids;
	}

	public void setBids(Set bids) {
		this.bids = bids;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", description="
				+ description + ", initialPrice=" + initialPrice
				+ ", reservePrice=" + reservePrice + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", createdDate=" + createdDate + "]";
	}

}
