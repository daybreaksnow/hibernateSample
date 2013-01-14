package fetch;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import techscore.sample.DaoSupport;

public class FetchSample {
	public static void main(String[] args) {
		// save();
		loadRoot(2);
		loadSub2(1);
		System.err.println("end");
	}

	private static void loadRoot(long rootId) {
		Configuration config = new Configuration().configure();
		// config.setProperty("hibernate.max_fetch_depth", "4");
		// XXX 2.2.1 SessionFactoryはアプリケーション全体で使い回すべき
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		System.out.println("load root");
		Criteria criteria = session.createCriteria(FetchRoot.class);
		criteria.setFetchMode("fetchSub1s", FetchMode.JOIN);
		criteria.setFetchMode("fetchSub1s.fetchSub2s", FetchMode.JOIN);
		criteria.add(Restrictions.eq("id", rootId));
		FetchRoot root = (FetchRoot) criteria.uniqueResult();

		FetchSub1 sub1 = (FetchSub1) root.getFetchSub1s().iterator().next();
		System.out.println(sub1.getValue());

		FetchSub2 sub2 = (FetchSub2) sub1.getFetchSub2s().iterator().next();
		System.out.println(sub2.getValue());

	}

	private static void loadSub2(long sub2Id) {
		Configuration config = new Configuration().configure();
		// config.setProperty("hibernate.max_fetch_depth", "4");
		// XXX 2.2.1 SessionFactoryはアプリケーション全体で使い回すべき
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(FetchSub2.class);
		criteria.add(Restrictions.eq("id", sub2Id));

		criteria.setFetchMode("fetchSub1", FetchMode.JOIN);
		criteria.setFetchMode("fetchSub1.fetchRoot", FetchMode.JOIN);

		System.out.println("load sub2");
		FetchSub2 sub2 = (FetchSub2) criteria.uniqueResult();

		FetchSub1 sub1 = sub2.getFetchSub1();
		System.out.println(sub1.getValue());

		FetchRoot root = sub1.getFetchRoot();
		System.out.println(root.getValue());

	}

	private static void save() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		FetchRoot root = new FetchRoot();
		root.setValue("root");

		FetchSub1 sub1 = new FetchSub1();
		sub1.setValue("sub1");

		root.getFetchSub1s().add(sub1);
		sub1.setFetchRoot(root);

		FetchSub2 sub2 = new FetchSub2();
		sub2.setValue("sub2");

		sub1.getFetchSub2s().add(sub2);
		sub2.setFetchSub1(sub1);

		session.save(root);
		tx.commit();
		session.close();
	}

}
