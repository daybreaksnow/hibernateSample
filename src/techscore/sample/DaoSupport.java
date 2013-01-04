package techscore.sample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoSupport {
	public Session getSession() {
		Configuration config = new Configuration().configure();
		// XXX 2.2.1 SessionFactoryはアプリケーション全体で使い回すべき
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}
}