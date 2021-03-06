package inaction.fetch;

// Generated 2013/01/06 0:56:25 by Hibernate Tools 3.4.0.CR1

import inaction.association.Item;
import inaction.cascade.Category;

import java.util.Date;

/**
 * ItemCategoryMapping generated by hbm2java
 */
public class ItemCategoryMapping implements java.io.Serializable {

	private long mappingId;
	private Item item;
	private Category category;
	private Date createdDate;

	public ItemCategoryMapping() {
	}

	public ItemCategoryMapping(long mappingId, Item item, Category category) {
		this.mappingId = mappingId;
		this.item = item;
		this.category = category;
	}

	public ItemCategoryMapping(long mappingId, Item item, Category category,
			Date createdDate) {
		this.mappingId = mappingId;
		this.item = item;
		this.category = category;
		this.createdDate = createdDate;
	}

	public long getMappingId() {
		return this.mappingId;
	}

	public void setMappingId(long mappingId) {
		this.mappingId = mappingId;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
