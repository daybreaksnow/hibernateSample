package transaction.nest;

import inaction.association.Bid;
import inaction.association.Item;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

public class NestTransactionSample {

	public static void main(String[] args) {
		saveItem();
	}

	private static void saveItem() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item itemOuter = new Item();
		session.save(itemOuter);

		saveBid(session);

		Transaction newTran = session.beginTransaction();

		tx.commit();
		session.close();
	}

	private static void saveBid(Session session) {
		Transaction tx = session.beginTransaction();

		Bid bid = new Bid();
		session.save(bid);
		tx.commit();
	}
}
