package inaction.lock;

import inaction.association.Item;
import manytomany.ItemManytomany;

import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

public class LockSample {

	public static void main(String[] args) {
		// updateNoVersion();
		// updateHasVersion();
		lock();
		// refs();
	}

	private static void updateNoVersion() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = (Item) session.get(Item.class, 1L);
		item.setName("1");
		tx.commit();
		session.close();

		session = dao.getSession();
		tx = session.beginTransaction();
		Item item2 = (Item) session.get(Item.class, 1L);
		item2.setName("2");
		tx.commit();
		session.close();

		System.out.println(item.getName());

		session = dao.getSession();
		tx = session.beginTransaction();
		// item.setName("2");
		// versionがないと以下のどちらでも古いデータで更新できてしまう
		session.update(item);
		// session.buildLockRequest(LockOptions.READ).lock(item);
		item.setDescription("hoge");

		tx.commit();
		session.close();
	}

	private static void updateHasVersion() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		ItemManytomany item = new ItemManytomany();
		item.setName("1");
		session.save(item);
		tx.commit();
		session.close();

		session = dao.getSession();
		tx = session.beginTransaction();
		ItemManytomany item2 = (ItemManytomany) session.get(
				ItemManytomany.class, item.getItemId());
		item2.setName("2");
		tx.commit();
		session.close();

		System.out.println(item.getName());

		session = dao.getSession();
		tx = session.beginTransaction();
		// versionがあればエラーになる
		session.update(item);
		// session.buildLockRequest(LockOptions.READ).lock(item);

		tx.commit();
		session.close();
	}

	private static void lock() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		ItemManytomany item = new ItemManytomany();
		item.setName("1");
		session.save(item);
		tx.commit();
		session.close();

		Session session2 = dao.getSession();
		Transaction tx2 = session2.beginTransaction();
		ItemManytomany item2 = (ItemManytomany) session2.get(
				ItemManytomany.class, item.getItemId(), LockOptions.UPGRADE);
		item2.setName("lock");

		session = dao.getSession();
		tx = session.beginTransaction();
		// ロックがかかっているのでここで止まる。
		session.buildLockRequest(LockOptions.READ).lock(item);
		item.setName("no lock");
		tx.commit();
		session.close();

		tx2.commit();
		session2.close();
	}

}
