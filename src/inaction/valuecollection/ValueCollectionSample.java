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
		// saveImage(1L, "aa.png", "aa");
		// saveImageMap(1L, "aa.png", "fileNameA");
		// saveImageBag(1L, "bb.png", "fileNameB");
		loadItem(1L);
		// deleteItem(1L);
		// deleteItemBag(1L);
	}

	private static void loadItem(long id) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		Item item = (Item) session.get(Item.class, id);
		System.out.println(item.getItemImages());
		for (String setVal : item.getItemImages()) {
			System.out.println(setVal);
		}
		System.out.println(item.getItemImageBag().size());
		for (String bagVal : item.getItemImageBag()) {
			System.out.println(bagVal);
		}
		System.out.println(item.getItemImageMap().get("hoge.png"));
		System.out.println(item.getItemImageMap());

	}

	private static void saveImage(Long itemId, String fileName, String imageName) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		ItemImage image = new ItemImage();
		image.setFileName(fileName);
		image.setImageName(imageName);
		image.setItem((Item) session.load(Item.class, itemId));

		session.save(image);

		tx.commit();
		session.close();
	}

	// cascade=allにしてもバリュー型のマッピングは保存されない
	private static void saveImageMap(Long itemId, String key, String fileName) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = (Item) session.get(Item.class, itemId);
		// item.setName("imageMap1");
		item.getItemImageMap().put(key, fileName);
		item.getItemImages().add("hogege");

		session.save(item);

		tx.commit();
		session.close();
	}

	// cascade=noneにしてもidbagは保存される
	private static void saveImageBag(Long itemId, String key, String fileName) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = (Item) session.get(Item.class, itemId);
		// item.setName("imageMap1");
		item.getItemImageBag().add(fileName);
		item.getItemImages().add("hogege");

		session.save(item);

		tx.commit();
		session.close();
	}

	// cascade=allにしてもバリュー型のマッピングは削除されない
	private static void deleteItem(long id) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = (Item) session.get(Item.class, id);
		session.delete(item);
		session.flush();

		tx.commit();
		session.close();
	}

	// cascade=noneにしてもidbagはall-delete-orphen状態となる
	private static void deleteItemBag(long id) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Item item = (Item) session.get(Item.class, id);
		item.getItemImageBag().clear();
		session.flush();

		tx.commit();
		session.close();
	}

}
