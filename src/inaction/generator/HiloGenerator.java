package inaction.generator;

// Generated 2013/01/14 1:11:15 by Hibernate Tools 3.4.0.CR1

/**
 * HiloGenerator generated by hbm2java
 */
public class HiloGenerator implements java.io.Serializable {

	private long id;
	private Long version;
	private String value;

	public HiloGenerator() {
	}

	public HiloGenerator(long id) {
		this.id = id;
	}

	public HiloGenerator(long id, String value) {
		this.id = id;
		this.value = value;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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