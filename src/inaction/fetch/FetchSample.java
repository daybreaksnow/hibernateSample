package inaction.fetch;

import inaction.cascade.Category;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import techscore.sample.DaoSupport;

/**
 * 4.4.5 フェッチ戦略
 * 
 */
public class FetchSample {
	public static void main(String[] args) {
		loadCategory(7, FetchMode.DEFAULT, FetchMode.DEFAULT);
		loadCategory(8, FetchMode.DEFAULT, FetchMode.DEFAULT);
		loadCategory(9, FetchMode.DEFAULT, FetchMode.DEFAULT);

		loadCategory(7, FetchMode.JOIN, FetchMode.JOIN);
		loadCategory(8, FetchMode.JOIN, FetchMode.JOIN);
		loadCategory(9, FetchMode.JOIN, FetchMode.JOIN);

		loadCategory(7, FetchMode.SELECT, FetchMode.SELECT);
		loadCategory(8, FetchMode.SELECT, FetchMode.SELECT);
		loadCategory(9, FetchMode.SELECT, FetchMode.SELECT);

		System.err.println("end");
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
