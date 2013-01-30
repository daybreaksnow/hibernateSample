package manytomany;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

public class ManyToManySample {

	public static void main(String[] args) {
		saveItem();
		// saveCategory();
		// loadItem(1L);
		// loadCategory(1L);
		// deleteItem(2L);
		// deleteItemByCategory(5L, 6L);
	}

	private static void saveItem() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		ItemManytomany item = new ItemManytomany();
		item.setName("item1");

		CategoryManytomany category = new CategoryManytomany();
		category.setName("cat1");

		item.getCategories().add(category);
		category.getItems().add(item);

		session.save(item);
		tx.commit();
		session.close();
	}

	private static void saveCategory() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		CategoryManytomany category = new CategoryManytomany();
		category.setName("cat2");

		ItemManytomany item = new ItemManytomany();
		item.setName("item2");

		category.getItems().add(item);
		// item.getCategories().add(category);

		session.save(category);
		tx.commit();
		session.close();
	}

	private static void loadItem(Long itemId) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		System.out.println("start item load.");
		ItemManytomany item = (ItemManytomany) session.get(
				ItemManytomany.class, itemId);
		System.out.println("start getCategories.");
		System.out.println(item.getCategories());
		if (!item.getCategories().isEmpty()) {
			System.out.println("start getCategories.getItems");
			System.out.println(item.getCategories().iterator().next()
					.getItems());
		}

		session.close();
	}

	private static void loadCategory(Long categoryId) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		System.out.println("start category load.");
		CategoryManytomany category = (CategoryManytomany) session.get(
				CategoryManytomany.class, categoryId);
		System.out.println("start getItems");
		System.out.println(category.getItems());
		if (!category.getItems().isEmpty()) {
			System.out.println("start getItems.getCategories");
			System.out.println(category.getItems().iterator().next()
					.getCategories());
		}
		session.close();
	}

	private static void deleteItem(Long itemId) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		System.out.println("start item load.");
		ItemManytomany item = (ItemManytomany) session.get(
				ItemManytomany.class, itemId);
		session.delete(item);

		tx.commit();
		session.close();
	}

	private static void deleteItemByCategory(Long categoryId, Long itemId) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		CategoryManytomany category = (CategoryManytomany) session.get(
				CategoryManytomany.class, categoryId);
		for (ItemManytomany item : category.getItems()) {
			if (itemId.equals(item.getItemId())) {
				category.getItems().remove(item);
				break;
			}
		}

		tx.commit();
		session.close();
	}
}
