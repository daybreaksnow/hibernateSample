package saveupdate;

import inaction.association.Item;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import techscore.sample.DaoSupport;

public class SaveUpdateSample {

	public static void main(String[] args) {
		// saveNotPersistence();
		// updateNotPersistence();
		// savePersistence();
		// updatePersistence();
		// saveEvict();
		updateEvict();
	}

	private static void saveNotPersistence() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = new Item();
		item.setName("new");

		session.save(item);
		tx.commit();
		session.close();
	}

	private static void updateNotPersistence() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = new Item();
		item.setName("new");

		// idをキーにしてupdateが発行されるので、意味がない
		session.update(item);
		tx.commit();
		session.close();
	}

	private static void savePersistence() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = loadAnItem(session);
		item.setName("resave");
		session.save(item);
		tx.commit();
		session.close();
	}

	private static void updatePersistence() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = loadAnItem(session);
		item.setName("reupdate");
		session.update(item);
		tx.commit();
		session.close();
	}

	private static void saveEvict() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = loadAnItem(session);
		session.evict(item);
		item.setName("evict save");
		session.save(item);
		// all-delete-orphenのsetが変更されたと例外が発生する
		tx.commit();
		session.close();
	}

	private static void updateEvict() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = loadAnItem(session);
		session.evict(item);
		item.setName("evict update");
		session.update(item);
		tx.commit();
		session.close();
	}

	private static Item loadAnItem(Session session) {
		return (Item) session.createCriteria(Item.class)
				.addOrder(Order.desc("itemId")).list().get(0);
	}
}
