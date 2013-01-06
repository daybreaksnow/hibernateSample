package inaction.valuecollection;

import inaction.association.Item;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

/**
 * 6.2 バリュー型のコレクションのマッピング<BR>
 * DB上のItem→ItemImageの関連を、<BR>
 * Item.itemImagesにItemImageのfileNameを直接マッピングして、<BR>
 * ItemからItemImageを意識せず利用している。
 * 
 */
public class ValueCollectionSample {
	public static void main(String[] args) {
		// saveImage(1L, "aa.png");
		loadItem(1L);
	}

	private static void loadItem(long id) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		Item item = (Item) session.get(Item.class, id);
		System.out.println(item.getItemImages());
		System.out.println(item.getItemImageMap().get("hoge.png"));
		System.out.println(item.getItemImageMap());

	}

	private static void saveImage(Long itemId, String fileName) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		ItemImage image = new ItemImage();
		image.setFileName(fileName);
		image.setItem((Item) session.load(Item.class, itemId));

		session.save(image);

		tx.commit();
		session.close();
	}
}
