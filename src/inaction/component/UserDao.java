package inaction.component;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
	}
}
