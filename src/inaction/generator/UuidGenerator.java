package inaction.generator;

// Generated 2013/01/14 1:11:15 by Hibernate Tools 3.4.0.CR1

/**
 * UuidGenerator generated by hbm2java
 */
public class UuidGenerator implements java.io.Serializable {

	private String id;
	private Long version;
	private String value;

	public UuidGenerator() {
	}

	public UuidGenerator(String id) {
		this.id = id;
	}

	public UuidGenerator(String id, String value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
