package inaction.usertype;

import java.math.BigDecimal;
import java.util.Currency;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

public class UserTypeSample {
	public static void main(String[] args) {
		saveBid(BigDecimal.valueOf(160), Currency.getInstance("JPY"));
		saveBid(BigDecimal.valueOf(160), Currency.getInstance("USD"));
		loadBid(1);
		loadBid(2);
	}

	private static void saveBid(BigDecimal amount, Currency currency) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		BidUsertype bid = new BidUsertype();
		bid.setAmount(new MonetaryAmount(amount, currency));
		session.save(bid);

		tx.commit();
		session.close();
	}

	private static void loadBid(long id) {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();

		BidUsertype bid = (BidUsertype) session.get(BidUsertype.class, id);
		System.out.println(bid.getAmount().getValue());

		session.close();
	}
}
