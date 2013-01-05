package inaction.cascade;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

/**
 * 4.3.3 hibernateによるカスケード永続化
 * 
 */
public class CascadeSample {
	public static void main(String[] args) {
		saveCategoryWithChild("parent", "child");
		System.err.println("end");
	}

	private static void saveCategory(String name) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Category category = new Category();
		category.setName(name);
		session.save(category);
		tx.commit();
		session.close();
	}

	private static void saveCategoryWithChild(String parentName,
			String childName) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Category parentCategory = new Category();
		parentCategory.setName(parentName);

		Category childCategory = new Category();
		childCategory.setName(childName);

		// 関連の設定
		parentCategory.getChildCategories().add(childCategory);
		// childCategory.setParentCategory(parentCategory);

		session.save(parentCategory);
		tx.commit();
		session.close();
	}
}
