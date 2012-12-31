package techscore.sample;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookDao extends DaoSupport {
	public void save(SampleBook book) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();

		session.save(book);

		// transaction.commit();
		session.close();
	}

	public static void main(String[] args) {
		BookDao bookDao = new BookDao();

		SampleBook book = new SampleBook();
		book.setIsbn("A");
		book.setName("ゼロから始めるJava 増補改訂版");
		book.setPrice(1905);
		bookDao.save(book);

		SampleBook book2 = new SampleBook();
		book2.setIsbn("ISBN4-7561-4383-0");
		book2.setName("ゼロから始めるJSP/サーブレット");
		book2.setPrice(2095);
		bookDao.save(book2);
	}
}