package interceptor;

import inaction.association.Item;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InterceptorSample {

	public static void main(String[] args) {
		saveItem();
	}

	private static void saveItem() {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		// インターセプタをセット
		Session session = sessionFactory
				.openSession(new CreatedDateSaveInterceptor());
		Transaction tx = session.beginTransaction();

		Item item = new Item();
		item.setName("item1");

		session.save(item);
		tx.commit();

		tx = session.beginTransaction();
		item.setName("item2");
		tx.commit();
		session.close();

	}
}
