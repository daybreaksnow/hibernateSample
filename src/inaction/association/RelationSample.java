package inaction.association;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

public class RelationSample {
	public static void main(String[] args) {
		// saveBid();
		// saveItem();
		// relation();

		// relationBidToItem();
		// relationInterOther();

		// relationItemToBid();

		// deleteItem(19);
		deleteItemSet(13);
		System.err.println("end");
	}

	// cascade-allでitemを消したらbidも消えるかのテスト
	private static void deleteItem(long itemId) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = loadItem(session, itemId);

		session.delete(item);
		tx.commit();
		session.close();
	}

	// cascade-all-delete-orphenでbidが消えるかのテスト
	// NOTE:Bid→Itemもcascade-allだとItemも消えてしまう
	private static void deleteItemSet(long itemId) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = loadItem(session, itemId);
		Set bids = item.getBids();
		bids.clear();
		tx.commit();
		session.close();
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

	//
	private static void relationBidToItem() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Bid bid = new Bid();
		Item item = new Item();
		bid.setItem(item);
		item.getBids().add(bid);

		// NOTE: bid→itemへのcascadeがなく、itemをsaveしないと以下の例外が発生する
		// org.hibernate.TransientObjectException: object references an unsaved
		// transient instance - save the transient instance before flushing:
		// inaction.association.Item
		session.save(bid);
		session.save(item);
		tx.commit();
		session.close();
	}

	// itemのみ保存
	private static void relationItemToBid() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Bid bid = new Bid();
		Item item = new Item();
		// NOTE：ここでrefを入れておかないと、Bidは保存されるが、item_idがセットされない
		bid.setItem(item);
		item.getBids().add(bid);

		// NOTE: bid→itemへのcascadeがなく、itemをsaveしないと以下の例外が発生する
		// org.hibernate.TransientObjectException: object references an unsaved
		// transient instance - save the transient instance before flushing:
		// inaction.association.Item
		session.save(item);
		tx.commit();
		session.close();
	}

	// 関連に不整合をつけて保存
	// Bid→item1,item2→Bid
	private static void relationInterOther() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Bid bid = new Bid();
		Item item1 = new Item();
		item1.setName("item1");
		bid.setItem(item1);

		Item item2 = new Item();
		item2.setName("item2");
		item2.getBids().add(bid);

		// NOTE:item→bidsへのinverseがtrueならばitem1が、falseならばitem2のitem_idがセットされる
		session.save(bid);
		session.save(item1);
		session.save(item2);
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