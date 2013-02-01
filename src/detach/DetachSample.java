package detach;

import inaction.association.Bid;
import inaction.association.Item;
import manytomany.CategoryManytomany;

import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

public class DetachSample {

	public static void main(String[] args) {
		// update();
		flush();
		lock();
		refs();
	}

	private static void update() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		// loadだとプロキシが返されるが、プロキシでも分離した後普通に使える
		Item item = (Item) session.load(Item.class, 1L);
		item.setName("1");
		tx.commit();
		session.close();

		System.out.println(item.getName());

		session = dao.getSession();
		tx = session.beginTransaction();
		item.setName("2");
		// もし値が変わってなくとも強制的にupdate文は発行される
		session.update(item);

		tx.commit();
		session.close();
	}

	private static void flush() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		CategoryManytomany cat = new CategoryManytomany();
		cat.setName("cat");
		session.save(cat);
		tx.commit();
		session.close();

		session = dao.getSession();
		tx = session.beginTransaction();
		cat = (CategoryManytomany) session.get(CategoryManytomany.class,
				cat.getCategoryId());
		cat.setName("cat1");
		// version → 1
		session.flush();
		// ここでevictしようがしまいがupdate文は発行されるので、versionは2上がることになる。
		session.evict(cat);
		cat.setName("cat2");
		// version → 2
		session.update(cat);
		session.flush();

		tx.commit();
		session.close();
	}

	private static void lock() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = (Item) session.get(Item.class, 1L);
		item.setName("1");
		tx.commit();
		session.close();

		session = dao.getSession();
		tx = session.beginTransaction();
		// ロック前の変更は反映されない。ロック後にほかの変更があれば反映される→もし更新しないならばセッションに結び付ける意味があるのか？
		item.setName("before lock");
		session.buildLockRequest(LockOptions.READ).lock(item);

		tx.commit();
		session.close();
	}

	private static void refs() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = (Item) session.get(Item.class, 1L);
		item.setName("1");

		Bid bid = new Bid();
		bid.setAmount(1L);
		session.save(bid);

		tx.commit();
		session.close();

		session = dao.getSession();
		tx = session.beginTransaction();
		session.update(item);
		// 永続化しているものに関連付けると自動的に永続化されるようだ
		item.getBids().add(bid);
		bid.setItem(item);
		bid.setItemId(item.getItemId());
		bid.setAmount(2L);

		tx.commit();
		session.close();
	}
}
