package fetch;

// Generated 2013/01/14 18:01:45 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * FetchRoot generated by hbm2java
 */
public class FetchRoot implements java.io.Serializable {

	private long id;
	private Integer version;
	private String value;
	private Set fetchSub1s = new HashSet(0);

	public FetchRoot() {
	}

	public FetchRoot(long id) {
		this.id = id;
	}

	public FetchRoot(long id, String value, Set fetchSub1s) {
		this.id = id;
		this.value = value;
		this.fetchSub1s = fetchSub1s;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set getFetchSub1s() {
		return this.fetchSub1s;
	}

	public void setFetchSub1s(Set fetchSub1s) {
		this.fetchSub1s = fetchSub1s;
	}

}
