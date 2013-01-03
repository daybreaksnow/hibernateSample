package inaction.inheritance.single;

import java.util.List;

import org.hibernate.Criteria;
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
		dao.saveBankAccount();
		// クライテリアに渡すクラスを変化させることで、以下の2つのメソッドのように自動で適切なクラスのみを取得できる
		System.out.println("all:" + dao.getAll().size());
		System.out.println("creditCard:" + dao.getAllCreditCard().size());
	}

	private List<BillingDetails> getAll() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(BillingDetails.class);
		return criteria.list();
	}

	private List<CreditCard> getAllCreditCard() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(CreditCard.class);
		return criteria.list();
	}

	private void saveCreditCard() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();

		CreditCard creditCard = new CreditCard();
		creditCard.setOwner("hoge");
		creditCard.setExpYear("1987");
		creditCard.setExpMonth("02");

		session.save(creditCard);
		tx.commit();
		session.close();
	}

	private void saveBankAccount() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();

		BankAccount account = new BankAccount();
		account.setBankName("bankName");
		account.setBankSwift("swift");

		session.save(account);
		tx.commit();
		session.close();
	}
}
