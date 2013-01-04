package inaction.association;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

public class Main {
	public static void main(String[] args) {
		saveBid();
		saveItem();
		relation();
	}

	private static void relation() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Bid bid = loadBid(session, 1L);
		System.out.println(bid.getItem());
		Item item = loadItem(session, 1L);
		bid.setItem(item);

		session.save(bid);
		tx.commit();
		session.close();
	}

	private static Bid loadBid(Session session, Long id) {
		return (Bid) session.load(Bid.class, id);
	}

	private static Item loadItem(Session session, Long id) {
		return (Item) session.load(Item.class, id);
	}

	private static void saveBid() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();
		Bid bid = new Bid();
		bid.setAmount(100L);
		session.save(bid);
		tx.commit();
		session.close();
	}

	private static void saveItem() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();
		Item item = new Item();
		item.setName("hoge");
		session.save(item);
		session.flush();
		tx.commit();
		session.close();
	}
}
