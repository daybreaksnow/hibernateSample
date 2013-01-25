package inaction.component;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import techscore.sample.DaoSupport;

/**
 * 3.5.2 コンポーネントの利用
 * 
 * @author T.IMAIZUMI
 * 
 */
public class UserDao extends DaoSupport {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		User user = new User();
		user.setUsername("hoge");
		Address home = new Address();
		home.setStreet("street");
		home.setCity("city");

		user.setHomeAddress(home);

		session.saveOrUpdate(user);

		tx.commit();
		session.close();

		listByHomeAddressStreet(dao, "street");
	}

	private static void listByHomeAddressStreet(UserDao dao, String street) {
		Session session2 = dao.getSession();
		Transaction tx2 = session2.beginTransaction();
		Criteria criteria = session2.createCriteria(User.class);
		// コンポーネント型であっても検索条件に設定可能
		criteria.add(Restrictions.eq("homeAddress.street", street));
		System.out.println(criteria.list());
		tx2.commit();
		session2.close();
	}
}
