package join;

import inaction.association.Bid;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import techscore.sample.DaoSupport;

public class CriteriaJoinSample {

	public static void main(String[] args) {
		leftJoin();
		aliasJoin();
	}

	private static void leftJoin() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Bid.class);
		criteria.createAlias("item", "aliasItem", Criteria.LEFT_JOIN);
		criteria.add(Restrictions.eq("aliasItem.name", "hoge"));
		List bids = criteria.list();

		System.out.println(bids.size());

		tx.commit();
		session.close();
	}

	private static void aliasJoin() {
		DaoSupport dao = new DaoSupport();
		Session session = dao.getSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Bid.class, "bid");
		Conjunction joinCriterion = Restrictions.conjunction();
		joinCriterion.add(Restrictions.eq("name", "hoge"));
		joinCriterion.add(Restrictions.eq("bid.amount", 1L));

		criteria.createAlias("item", "aliasItem", Criteria.INNER_JOIN,
				joinCriterion);
		List bids = criteria.list();

		System.out.println(bids.size());

		tx.commit();
		session.close();
	}
}
