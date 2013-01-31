package manytomany;

// Generated 2013/01/20 19:43:51 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * ItemManytomany generated by hbm2java
 */
public class ItemManytomany implements java.io.Serializable {

	private long itemId;
	private Long version;
	private String name;
	private Set<CategoryManytomany> categories = new HashSet(0);
	private Set categoriesByExtendMap = new HashSet<CategoryManytomany>();

	public ItemManytomany() {
	}

	public ItemManytomany(long itemId) {
		this.itemId = itemId;
	}

	public ItemManytomany(long itemId, String name, Set itemCategoryMappingMms) {
		this.itemId = itemId;
		this.name = name;
		this.categories = itemCategoryMappingMms;
	}

	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CategoryManytomany> getCategories() {
		return this.categories;
	}

	public void setCategories(Set itemCategoryMappingMms) {
		this.categories = itemCategoryMappingMms;
	}

	public Set getCategoriesByExtendMap() {
		return categoriesByExtendMap;
	}

	public void setCategoriesByExtendMap(
			Set categoriesByExtendMap) {
		this.categoriesByExtendMap = categoriesByExtendMap;
	}
}
