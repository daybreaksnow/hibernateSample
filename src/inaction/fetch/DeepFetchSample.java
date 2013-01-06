package inaction.fetch;

import inaction.association.Item;
import inaction.cascade.Category;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import techscore.sample.DaoSupport;

/**
 * 4.4.6 フェッチ深度
 * 
 */
public class DeepFetchSample {
	public static void main(String[] args) {
		long itemId = 13;
		long categoryId = 9;
		// saveMapping(13, 9);
		loadItem(itemId);
		System.err.println("end");
	}

	private static void loadItem(long itemId) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		Criteria criteria = session.createCriteria(Item.class);
		criteria.setFetchMode("itemCategoryMappings", FetchMode.JOIN);
		// TODO どうやって外部結合深度を設定するか
		criteria.setFetchMode("itemCategoryMappings.item", FetchMode.JOIN);
		criteria.add(Restrictions.eq("itemId", itemId));
		Item item = (Item) criteria.uniqueResult();

		ItemCategoryMapping map = item.getItemCategoryMappings().iterator()
				.next();
		System.out.println(map);

		Category cat = map.getCategory();
		System.out.println(cat);
	}

	private static void saveMapping(long itemId, long categoryId) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		ItemCategoryMapping mapping = new ItemCategoryMapping();
		// TODO ここでIDを設定して保存できないか？
		mapping.setCategory((Category) session.load(Category.class, categoryId));
		mapping.setItem((Item) session.load(Item.class, itemId));

		session.save(mapping);
		tx.commit();
		session.close();
	}

	private static void loadCategory(long id, FetchMode childFetchMode,
			FetchMode parentFetchMode) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		Criteria criteria = session.createCriteria(Category.class);
		criteria.setFetchMode("childCategories", childFetchMode);
		criteria.setFetchMode("parentCategory", parentFetchMode);
		criteria.add(Restrictions.eq("categoryId", id));
		Category cat = (Category) criteria.uniqueResult();

		System.out.println(cat.getClass());
		System.out.println(cat.getName());

		session.close();
	}

}
