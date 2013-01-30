package manytomany;

// Generated 2013/01/20 19:43:51 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * CategoryManytomany generated by hbm2java
 */
public class CategoryManytomany implements java.io.Serializable {

	private long categoryId;
	private Long version;
	private CategoryManytomany categoryManytomany;
	private String name;
	private Set<ItemManytomany> items = new HashSet(0);
	private Set categoryManytomanies = new HashSet(0);
	private Set itemsByExtendMap = new HashSet();

	public CategoryManytomany() {
	}

	public CategoryManytomany(long categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

	public CategoryManytomany(long categoryId,
			CategoryManytomany categoryManytomany, String name,
			Set itemCategoryMappingMms, Set categoryManytomanies) {
		this.categoryId = categoryId;
		this.categoryManytomany = categoryManytomany;
		this.name = name;
		this.items = itemCategoryMappingMms;
		this.categoryManytomanies = categoryManytomanies;
	}

	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public CategoryManytomany getCategoryManytomany() {
		return this.categoryManytomany;
	}

	public void setCategoryManytomany(CategoryManytomany categoryManytomany) {
		this.categoryManytomany = categoryManytomany;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ItemManytomany> getItems() {
		return this.items;
	}

	public void setItems(Set itemCategoryMappingMms) {
		this.items = itemCategoryMappingMms;
	}

	public Set getCategoryManytomanies() {
		return this.categoryManytomanies;
	}

	public void setCategoryManytomanies(Set categoryManytomanies) {
		this.categoryManytomanies = categoryManytomanies;
	}

	public Set getItemsByExtendMap() {
		return itemsByExtendMap;
	}

	public void setItemsByExtendMap(Set itemsByExtendMap) {
		this.itemsByExtendMap = itemsByExtendMap;
	}
}