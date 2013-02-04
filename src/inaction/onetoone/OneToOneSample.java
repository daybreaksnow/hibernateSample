package inaction.onetoone;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

/**
 * 6.3.1 一対一関連
 * 
 * @author T.IMAIZUMI
 * 
 */
public class OneToOneSample extends DaoSupport {
	public static void main(String[] args) {
		// saveUserToOhterAddresss();
		// saveUserToOneAddresss();
		// saveAddressToUser();
		loadUser(2L);
		loadUser(3L);
	}

	// 自宅住所と請求先住所を同一で保存
	private static void saveUserToOneAddresss() {
		OneToOneSample dao = new OneToOneSample();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		UserOnetoone user = new UserOnetoone();
		user.setUsername("hoge");

		AddressOnetoone home = new AddressOnetoone();
		home.setStreet("street");
		home.setCity("city");

		user.setHomeAddress(home);
		user.setBillingAddress(home);

		session.saveOrUpdate(user);

		tx.commit();
		session.close();
	}

	// 自宅住所と請求先住所を別で保存
	private static void saveUserToOhterAddresss() {
		OneToOneSample dao = new OneToOneSample();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		UserOnetoone user = new UserOnetoone();
		user.setUsername("hoge");

		AddressOnetoone home = new AddressOnetoone();
		home.setStreet("street");
		home.setCity("city");

		AddressOnetoone billing = new AddressOnetoone();
		billing.setStreet("streetBillng");
		billing.setCity("cityBillng");

		user.setHomeAddress(home);
		user.setBillingAddress(billing);

		session.saveOrUpdate(user);

		tx.commit();
		session.close();
	}

	private static void saveAddressToUser() {
		OneToOneSample dao = new OneToOneSample();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		AddressOnetoone home = new AddressOnetoone();
		home.setStreet("street1");
		home.setCity("city1");

		UserOnetoone user = new UserOnetoone();
		user.setUsername("hoge1");

		home.setUserByHomeAddress(user);

		// XXX user→addressの参照をセットしないとIDが入らないが、これはそういうものなのか？
		session.saveOrUpdate(home);

		tx.commit();
		session.close();
	}

	// 自宅住所と請求先住所を同一で保存
	private static void loadUser(Long userId) {
		OneToOneSample dao = new OneToOneSample();
		Session session = dao.getSession();

		UserOnetoone user = (UserOnetoone) session.get(UserOnetoone.class,
				userId);
		AddressOnetoone home = user.getHomeAddress();
		AddressOnetoone billing = user.getBillingAddress();
		System.out.println(home + " " + home.getUserByHomeAddress());
		System.out.println(home + " " + home.getUserByBillingAddress());
		System.out.println(billing + " " + billing.getUserByHomeAddress());
		System.out.println(billing + " " + billing.getUserByBillingAddress());

		session.close();
	}
}
