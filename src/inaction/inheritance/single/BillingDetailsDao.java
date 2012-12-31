package inaction.inheritance.single;

import org.hibernate.Session;
import org.hibernate.Transaction;

import techscore.sample.DaoSupport;

/**
 * 3.6.2 クラス階層ごとに1つのテーブル
 * 
 * @author T.IMAIZUMI
 * 
 */
public class BillingDetailsDao extends DaoSupport {
	public static void main(String[] args) {

		BillingDetailsDao dao = new BillingDetailsDao();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		CreditCard creditCard = new CreditCard();
		creditCard.setOwner("hoge");
		creditCard.setExpYear("1987");
		creditCard.setExpMonth("02");

		session.save(creditCard);
		tx.commit();
		session.close();
	}
}
