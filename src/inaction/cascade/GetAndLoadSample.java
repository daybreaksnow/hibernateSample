package inaction.cascade;

import org.hibernate.Session;

import techscore.sample.DaoSupport;

/**
 * 4.4.1 hibernateによるカスケード永続化
 * 
 */
public class GetAndLoadSample {
	public static void main(String[] args) {
		loadCategory(1);
		getCategory(1);
		System.err.println("end");
	}

	private static void loadCategory(long id) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		// loadはIDが存在しない場合に例外を投げる。またProxyの可能性がある
		Category cat = (Category) session.load(Category.class, id);
		System.out.println(cat.getClass()); // javassistのクラスである
		System.out.println(cat.getName());

		session.close();
	}

	private static void getCategory(long id) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		// IDが存在しなければnullが帰る。常にProxyは使われない。
		Category cat = (Category) session.get(Category.class, id);
		System.out.println(cat.getClass());
		System.out.println(cat.getName());

		session.close();
	}

}
