package techscore.sample;

import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoSupport {
	public Session getSession() {
		return getSession(null);
	}

	public Session getSession(Interceptor interceptor) {
		Configuration config = new Configuration().configure();
		// XXX 2.2.1 SessionFactoryはアプリケーション全体で使い回すべき
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession(interceptor);
		return session;
	}
}