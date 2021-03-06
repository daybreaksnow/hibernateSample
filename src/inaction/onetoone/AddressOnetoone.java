package inaction.onetoone;

// Generated 2013/01/13 13:40:52 by Hibernate Tools 3.4.0.CR1

/**
 * AddressOnetoone generated by hbm2java
 */
public class AddressOnetoone implements java.io.Serializable {

	private long addressId;
	private String street;
	private String city;
	private String zipCode;
	private long version;
	private UserOnetoone userByHomeAddress;
	private UserOnetoone userByBillingAddress;

	public AddressOnetoone() {
	}

	public AddressOnetoone(long addressId) {
		this.addressId = addressId;
	}

	public AddressOnetoone(long addressId, String street, String city,
			String zipCode) {
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
	}

	public long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public UserOnetoone getUserByHomeAddress() {
		return userByHomeAddress;
	}

	public void setUserByHomeAddress(UserOnetoone userByHomeAddress) {
		this.userByHomeAddress = userByHomeAddress;
	}

	public UserOnetoone getUserByBillingAddress() {
		return userByBillingAddress;
	}

	public void setUserByBillingAddress(UserOnetoone userByBillingAddress) {
		this.userByBillingAddress = userByBillingAddress;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
