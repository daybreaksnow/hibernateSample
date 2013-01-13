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
		// saveUserToAddress();
		saveAddressToUser();
	}

	private static void saveUserToAddress() {
		OneToOneSample dao = new OneToOneSample();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		UserOnetoone user = new UserOnetoone();
		user.setUsername("hoge");
		AddressOnetoone home = new AddressOnetoone();
		home.setStreet("street");
		home.setCity("city");

		UserOnetoone user2 = new UserOnetoone();
		user2.setUsername("user2");
		home.setUser(user2);

		user.setHomeAddress(home);

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

		home.setUser(user);

		// XXX user→addressの参照をセットしないとIDが入らないが、これはそういうものなのか？
		session.saveOrUpdate(home);

		tx.commit();
		session.close();
	}
}
